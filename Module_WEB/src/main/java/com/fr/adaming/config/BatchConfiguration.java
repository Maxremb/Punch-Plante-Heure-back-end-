package com.fr.adaming.config;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

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
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fr.adaming.dto.MeteoXlsDto;
import com.fr.adaming.entity.Meteo;

import lombok.extern.slf4j.Slf4j;

/**
 * Config de la recuperation de meteo à partir d'une source externe
 * 
 * @author Gregoire
 * @since 0.0.1
 *
 */
@Configuration
@EnableBatchProcessing
@EnableScheduling
@Slf4j
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

	@Value("classpath:/Meteo.csv")
	private Resource inputResource;

	/**
	 * Méthode reader du batch permettant de récupérer les données d'un fichier
	 * 
	 * @return Un meteo XLS dto à convertir en données java
	 */
	@Bean
	public FlatFileItemReader<MeteoXlsDto> reader() {
		log.info("Batch config : méthode reader appelée");
		DefaultFieldSetFactory fieldSetFactory = new DefaultFieldSetFactory();
		fieldSetFactory.setNumberFormat(NumberFormat.getInstance(Locale.FRANCE));

		return new FlatFileItemReaderBuilder<MeteoXlsDto>().name("meteoItemReader").linesToSkip(1)
				.resource(inputResource).delimited().delimiter(";").fieldSetFactory(fieldSetFactory)
				.names(new String[] { "station", "nom", "longitude", "latitude", "altitude", "date", "rr", "tn", "tx",
						"fxi", "dxi", "fxy", "dxy", "inst", "eptmon" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<MeteoXlsDto>() {
					{
						setTargetType(MeteoXlsDto.class);

					}
				}).build();
	}

	/**
	 * Méthode step 1 du batch effectuant le job de récupération de données météos
	 * 
	 * @return
	 */
	@Bean
	public Step step1() {
		log.info("Batch config : méthode step 1 appelée");
		return stepBuilderFactory.get("step1").<MeteoXlsDto, Meteo>chunk(10).faultTolerant()
				.skip(ValidationException.class).skip(FlatFileParseException.class).skip(ItemStreamException.class)
				.skipLimit(9).reader(reader()).processor(processor).writer(writer).build();
	}

//	@Scheduled(cron = " 0 0 0 ? * * ")
	/**
	 * Méthode planifié dans le temps permettant l'envoi d'email aux utilisateurs
	 * concernés par l'arrossage de leur jardin
	 * 
	 * @throws Exception
	 */
	@Scheduled(initialDelay = 30 * 1000, fixedDelay = 24 * 60 * 60 * 1000)
	public void scheduleFixedDelayTask() throws Exception {
		log.info("Batch config : méthode planifié de récupération météo appelée");
		System.out.println("job lancé" + new Date());
		JobParameters param = new JobParametersBuilder().addString("JobId", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		Job job = jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).flow(step1()).end()
				.build();

		JobExecution execution = jobLauncher.run(job, param);
		System.out.println("Job finish with status :" + execution.getStatus());
	}

}
