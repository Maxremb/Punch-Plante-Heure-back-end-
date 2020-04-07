package com.fr.adaming.config;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fr.adaming.entity.Meteo;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public JobLauncher jobLauncher;
    
    @Autowired 
    public MeteoItemProcessor processor;
    
    @Autowired
    public MeteoWriter writer;


	@Value("classpath:/meteo.csv")
	private Resource inputResource;

	@Bean
	public FlatFileItemReader<Meteo> reader() {
		return new FlatFileItemReaderBuilder<Meteo>().name("meteoItemReader").linesToSkip(1)
				.resource(inputResource).delimited().names(new String[] { "nom", "prenom", "email", "cni", "adresse", "cp", "ville", "sexe", "num" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Meteo>() {
					{
						setTargetType(Meteo.class);

					}
				}).build();
	}

//	@Bean
//	public JobExecutionListener listener() {
//		return new JobCompletionNotificationListener();
//	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Meteo, Meteo>chunk(10).faultTolerant()
				.skip(ValidationException.class).skip(FlatFileParseException.class).skip(ItemStreamException.class)
				.skipLimit(9).reader(reader()).processor(processor).writer(writer).build();
	}

	@Scheduled(cron = " 0 0 0 ? * * "  )
	public void scheduleFixedDelayTask() throws Exception {

		System.out.println("job lancé" + new Date());
		JobParameters param = new JobParametersBuilder().addString("JobId", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		
		Job job = jobBuilderFactory
				.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
		
		JobExecution execution = jobLauncher.run(job,  param);
		System.out.println("Job finish with status :" + execution.getStatus());

	}

}
