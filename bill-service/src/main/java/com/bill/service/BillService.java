package com.bill.service;

import com.bill.entity.Bill;
import com.bill.exception.BillNotFoundException;
import com.bill.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class BillService {
    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElseThrow(() -> new BillNotFoundException("Bill with id " + id + " not found"));
    }

    public Long createBill(Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnabled) {
        Bill bill = new Bill(accountId, amount, isDefault, OffsetDateTime.now(), overdraftEnabled);
        return billRepository.save(bill).getId();
    }

    public Bill updateBill(Long billId, Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnabled) {
        Bill bill = new Bill();
        bill.setId(billId);
        bill.setAccountId(accountId);
        bill.setIsDefault(isDefault);
        bill.setAmount(amount);
        bill.setOverdraftEnabled(overdraftEnabled);
        return billRepository.save(bill);
    }

    public Bill deleteBillById(Long billId) {
        Bill bill = getBillById(billId);
        billRepository.deleteById(billId);
        return bill;

    }
 }
