package com.frikwensi.billingintegration;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Date;

public interface UsageRepository extends  CrudRepository<Usage, Date> {
    List<Bill> findByDate(Date date);
}
