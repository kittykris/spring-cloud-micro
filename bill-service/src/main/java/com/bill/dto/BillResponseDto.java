package com.bill.dto;

import com.bill.entity.Bill;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
public class BillResponseDto {
    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private Boolean isDefault;
    private OffsetDateTime creationTime;
    private Boolean overdraftEnabled;

    public BillResponseDto(Bill bill) {
        this.id = bill.getId();
        this.accountId = bill.getAccountId();
        this.amount = bill.getAmount();
        this.creationTime = bill.getCreationTime();
        this.isDefault = bill.getIsDefault();
        this.overdraftEnabled = bill.getOverdraftEnabled();
    }
}
