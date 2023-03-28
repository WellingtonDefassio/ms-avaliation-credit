package io.wdefassio.msavaliationcredit.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientCards {
    private String name;
    private String flag;
    private BigDecimal approvedLimit;

}
