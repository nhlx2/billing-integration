package com.frikwensi.billingintegration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.stream.StreamSupport;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.stream.IntStream;
import java.time.Month;
import java.util.stream.Collectors;
import java.util.Optional;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ScheduledTasks {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMDD");
    
    @Autowired
    private BillRepository billings;

    @Autowired
    private UsageRepository usages;


    //@Scheduled(cron =  "*/15 * * * * *")  //tesing cron
    @Scheduled(cron = "0 0 7 1-4 * *")  //Just about final cron
    public void checkForFirstDayOfBusiness() {
	System.out.println("Cron launched!");
	final Set<LocalDate> holidays = new LinkedHashSet<>();
	holidays.add(IsoChronology.INSTANCE.date(2020, 01, 01));
	holidays.add(IsoChronology.INSTANCE.date(2020, 03, 21));
	holidays.add(IsoChronology.INSTANCE.date(2020, 04, 10));
	holidays.add(IsoChronology.INSTANCE.date(2020, 04, 13));
	holidays.add(IsoChronology.INSTANCE.date(2020, 05, 01));
	holidays.add(IsoChronology.INSTANCE.date(2020, 06, 16));
	holidays.add(IsoChronology.INSTANCE.date(2020, 8, 9));
	holidays.add(IsoChronology.INSTANCE.date(2020, 8, 10));
	holidays.add(IsoChronology.INSTANCE.date(2020, 9, 24));
	holidays.add(IsoChronology.INSTANCE.date(2020, 12, 16));
	holidays.add(IsoChronology.INSTANCE.date(2020, 12, 25));
	holidays.add(IsoChronology.INSTANCE.date(2020, 12, 26));

	int month = IsoChronology.INSTANCE.dateNow().getMonth().getValue();
	int year = IsoChronology.INSTANCE.dateNow().getYear();

	List<LocalDate> firstfourdays = new ArrayList<>();

	for (int day = 1; day < 5; day++) {
	    LocalDate d = IsoChronology.INSTANCE.date(year, month, day);
	    firstfourdays.add(d);
	}

	Optional<LocalDate> firstbusinessday = firstfourdays
	    .stream()
	    .filter(d -> !holidays.contains(d)) //remove Holidays
	    .filter(d -> d.getDayOfWeek().getValue() != 6)  //remove Saturday
	    .filter(d -> d.getDayOfWeek().getValue() != 7)  //remove Sunday
	    .findFirst(); //take the first of what's left
	if (firstbusinessday.isPresent() && (IsoChronology.INSTANCE.dateNow().equals(firstbusinessday.get()))){
	    System.out.println("Attempting to writeBillingFile");
	    writeBillingFile();

	}
    }

    public void writeBillingFile() {
	Date currentDate = new Date();
	Calendar c = Calendar.getInstance();
	c.setTime(currentDate);
	c.add(Calendar.MONTH, -1);
	usages.save(new Usage(new Date()));
	System.out.println("\nBillingFile Contents below:");
	billings.findAll().
	    forEach(x -> System.out.println(x + String.valueOf(StreamSupport.stream(usages.findAll().spliterator(), false).filter(d -> d.getDate().after(c.getTime())).count())));
    }
}

