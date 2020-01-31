package com.frikwensi.billingintegration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMDD");
    
    @Autowired
    private BillRepository billings;

    @Autowired
    private UsageRepository usages;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
	log.info("The time is now {}", timeFormat.format(new Date()));
    }

    @Scheduled(fixedRate = 10000)
    public void writeBillingFile() {
	Date currentDate = new Date();
	Calendar c = Calendar.getInstance();
	c.setTime(currentDate);
	c.add(Calendar.MONTH, -1);
	usages.save(new Usage(new Date()));
	System.out.println("\nfindAll()");
	billings.findAll().
	    forEach(x -> System.out.println(x + String.valueOf(StreamSupport.stream(usages.findAll().spliterator(), false).filter(d -> d.getDate().after(c.getTime())).count())));
    }
}

