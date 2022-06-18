package com.bill.controller;

import com.bill.dto.BillRequestDto;
import com.bill.dto.BillResponseDto;
import com.bill.entity.Bill;
import com.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/{id}")
    public BillResponseDto getBillById(@PathVariable Long id) {
        return new BillResponseDto(billService.getBillById(id));
    }

    @PostMapping("/")
    public Long createBill(BillRequestDto billRequestDto) {
        return billService.createBill(billRequestDto.getAccountId(), billRequestDto.getAmount(), billRequestDto.getIsDefault(),
                billRequestDto.getOverdraftEnabled());
    }

    @PutMapping("/{id}")
    public BillResponseDto updateBill(@PathVariable Long id, @RequestBody BillRequestDto billRequestDto) {
        return new BillResponseDto(billService.updateBill(id, billRequestDto.getAccountId(), billRequestDto.getAmount(),
                billRequestDto.getIsDefault(), billRequestDto.getOverdraftEnabled()));
    }

    @DeleteMapping("/{id}")
    public BillResponseDto deleteBill(@PathVariable Long id) {
        return new BillResponseDto(billService.deleteBillById(id));
    }
}
