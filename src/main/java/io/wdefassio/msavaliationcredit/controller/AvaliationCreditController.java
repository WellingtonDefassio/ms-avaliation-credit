package io.wdefassio.msavaliationcredit.controller;

import io.wdefassio.msavaliationcredit.domain.ClientSituation;
import io.wdefassio.msavaliationcredit.domain.DataAvaliation;
import io.wdefassio.msavaliationcredit.domain.ReturnAvaliationClient;
import io.wdefassio.msavaliationcredit.dto.Protocol;
import io.wdefassio.msavaliationcredit.dto.SolicitationCardData;
import io.wdefassio.msavaliationcredit.service.AvaliationCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        try {
            ClientSituation clientSituation = avaliationCreditService.obtainSituation(cpf);
            return ResponseEntity.ok(clientSituation);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ReturnAvaliationClient> avaliation(@RequestBody DataAvaliation data) {
        try {
            ReturnAvaliationClient returnAvaliationClient = avaliationCreditService.makeAvaliation(data.getCpf(), data.getIncome());
            return ResponseEntity.ok(returnAvaliationClient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("solicitation")
    public ResponseEntity<Protocol> solicitationCard(@RequestBody SolicitationCardData data) {
        Protocol protocol = avaliationCreditService.solicitationMakeCard(data);
       return ResponseEntity.ok(protocol);
    }


}
