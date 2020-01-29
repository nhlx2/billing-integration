package com.frikwensi.billingintegration;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, String> {
    List<Bill> findByTransactionReference(String transactionReference);
}
