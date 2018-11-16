package com.techgap.droolsaverage.droolsaverage;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan (basePackages = {
		"com.techgap.droolsaverage.controller",
		"com.techgap.droolsaverage.model",
		"com.techgap.droolsaverage.util",
		"com.techgap.droolsaverage.config",
		"com.techgap.droolsaverage.exception"
})
public class Main{
	
	 @SuppressWarnings("unused")
	private static ConfigurableApplicationContext ctx;
	@SuppressWarnings("unused")
	private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB
	
    public static void main(String[] argv) {
    	
    	SpringApplication.run(Main.class, argv);
    }
}
