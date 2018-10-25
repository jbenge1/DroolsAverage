package com.techgap.DroolsAverage.DroolsAverage;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@EnableConfigurationProperties(StorageProperties.class)
public class Main{
	
	 @SuppressWarnings("unused")
	private static ConfigurableApplicationContext ctx;
	@SuppressWarnings("unused")
	private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB
	
    public static void main(String[] argv) {
    	
    	ctx = SpringApplication.run(Main.class, argv);
    }
}
