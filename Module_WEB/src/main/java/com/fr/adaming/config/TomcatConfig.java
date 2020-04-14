package com.fr.adaming.config;


import org.apache.catalina.Context;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class TomcatConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>{

	@Override
	public void customize(TomcatServletWebServerFactory factory) {

		TomcatContextCustomizer custom = new TomcatContextCustomizer() {
			
			@Override
			public void customize(Context context) {
				
				context.setUseHttpOnly(false);
				
			}
		};
		
		factory.addContextCustomizers(custom);
		
	}


}
