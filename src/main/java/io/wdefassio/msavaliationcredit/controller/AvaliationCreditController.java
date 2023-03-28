package io.wdefassio.msavaliationcredit.controller;

import io.wdefassio.msavaliationcredit.domain.ClientSituation;
import io.wdefassio.msavaliationcredit.service.AvaliationCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliations-credit")
@RequiredArgsConstructor
public class AvaliationCreditController {

    private final AvaliationCreditService avaliationCreditService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "situation", params = "cpf")
    public ResponseEntity<ClientSituation> consultClientSituation(@RequestParam("cpf") String cpf) {
        ClientSituation clientSituation = avaliationCreditService.obtainSituation(cpf);
        return null;
    }


}
