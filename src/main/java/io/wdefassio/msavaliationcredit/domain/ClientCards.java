package io.wdefassio.msavaliationcredit.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ClientCards {
    private Long id;
    private String name;
    private String flag;
    private BigDecimal baseLimit;

}
