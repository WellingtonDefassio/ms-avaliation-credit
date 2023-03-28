package io.wdefassio.msavaliationcredit.domain;

import lombok.Data;

import java.util.List;

@Data
public class ClientSituation {

    private ClientData client;
    private List<ClientCards> cards;

}
