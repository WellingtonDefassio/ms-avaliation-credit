package io.wdefassio.msavaliationcredit.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReturnAvaliationClient {

    private List<ClientCards> cards;


}
