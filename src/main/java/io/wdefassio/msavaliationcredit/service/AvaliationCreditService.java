package io.wdefassio.msavaliationcredit.service;

import feign.FeignException;
import io.wdefassio.msavaliationcredit.clients.ClientResourceClient;
import io.wdefassio.msavaliationcredit.clients.CreditCardResourceClient;
import io.wdefassio.msavaliationcredit.domain.ClientCards;
import io.wdefassio.msavaliationcredit.domain.ClientData;
import io.wdefassio.msavaliationcredit.domain.ClientSituation;
import io.wdefassio.msavaliationcredit.domain.ReturnAvaliationClient;
import io.wdefassio.msavaliationcredit.dto.Protocol;
import io.wdefassio.msavaliationcredit.dto.SolicitationCardData;
import io.wdefassio.msavaliationcredit.exception.ClientNotFoundException;
import io.wdefassio.msavaliationcredit.exception.MicroServiceCommunicationException;
import io.wdefassio.msavaliationcredit.mqueue.EmissionCardPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliationCreditService {

    private final ClientResourceClient resourceClient;
    private final CreditCardResourceClient resourceCard;
    private final EmissionCardPublisher emissionCardPublisher;

    public ClientSituation obtainSituation(String cpf) throws ClientNotFoundException, MicroServiceCommunicationException {
        try {
            ResponseEntity<ClientData> responseEntity = resourceClient.getByCpf(cpf);
            ClientData clientData = responseEntity.getBody();
            ResponseEntity<List<ClientCards>> listResponseEntity = resourceCard.clientCards(cpf);
            List<ClientCards> cards = listResponseEntity.getBody();

            return ClientSituation.builder()
                    .client(clientData)
                    .cards(cards)
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientNotFoundException();
            } else {
                throw new MicroServiceCommunicationException();
            }

        }
    }

    public ReturnAvaliationClient makeAvaliation(String cpf, Long income) throws MicroServiceCommunicationException, ClientNotFoundException {
        try {
            ResponseEntity<ClientData> responseEntity = resourceClient.getByCpf(cpf);
            ClientData clientData = responseEntity.getBody();
            ResponseEntity<List<ClientCards>> responseEntityCard = resourceCard.getAllCardsAvailableByIncome(income);
            List<ClientCards> clientCards = responseEntityCard.getBody();
            List<ClientCards> collect = clientCards.stream().map(c -> ClientCards.builder()
                    .id(c.getId())
                    .name(c.getName())
                    .flag(c.getFlag())
                    .baseLimit(c.getBaseLimit().multiply(BigDecimal.valueOf(1.5))).build()).collect(Collectors.toList());

            return ReturnAvaliationClient.builder()
                    .cards(collect)
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientNotFoundException();
            } else {
                throw new MicroServiceCommunicationException();
            }
        }

    }

    public Protocol solicitationMakeCard(SolicitationCardData data) {

        emissionCardPublisher.EmissionCardSolicitation(data);
        String protocol = UUID.randomUUID().toString();

        return new Protocol(protocol);
    }
}
