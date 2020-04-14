package com.fr.adaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe responsable du lancement de l'API
 * 
 * @since 0.0.1-SNAPSHOT
 *
 */
@SpringBootApplication
public class ModuleWebApplication {

	/**
	 * Méthode main responsable du lancement de l'API
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ModuleWebApplication.class, args);
	}

}
