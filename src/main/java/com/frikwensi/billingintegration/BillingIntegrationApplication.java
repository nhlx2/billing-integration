package com.frikwensi.billingintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BillingIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingIntegrationApplication.class, args);
	}

}
