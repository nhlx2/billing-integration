package com.frikwensi.billingintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;


@SpringBootApplication
@EnableScheduling
public class BillingIntegrationApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(BillingIntegrationApplication.class);

    @Autowired
    private BillRepository repository;
    
    public static void main(String[] args) {
	SpringApplication.run(BillingIntegrationApplication.class, args);
    }

    @Override
    public void run(String...args) {
	log.info("StartApplication...");
	repository.save(new Bill("Paid123", "ABSAZAJJ","PAID", "ZAROUT", 100, new Date()));
	repository.save(new Bill("Paid124", "ABSAZAJJ", "PAID", "ZAROUT", 200, new Date()));
	repository.save(new Bill("Paid125", "SBZAZAJJ", "PAID","USDOUT", 100, new Date()));
	    
	System.out.println("\nfindAll()");
	repository.findAll().forEach(x -> System.out.println(x));
    }
}
