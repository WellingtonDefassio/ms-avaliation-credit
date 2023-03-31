package io.wdefassio.msavaliationcredit.clients;

import io.wdefassio.msavaliationcredit.domain.ClientCards;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-credit-card", path = "/cards")
public interface CreditCardResourceClient {
    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCards>> clientCards(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    ResponseEntity<List<ClientCards>> getAllCardsAvailableByIncome(@RequestParam("income") Long income);


}
