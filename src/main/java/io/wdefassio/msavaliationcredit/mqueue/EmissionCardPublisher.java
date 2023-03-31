package io.wdefassio.msavaliationcredit.mqueue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.wdefassio.msavaliationcredit.dto.SolicitationCardData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmissionCardPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;


    public void EmissionCardSolicitation(SolicitationCardData solicitationCardData) {
        String json = convertToJson(solicitationCardData);
        rabbitTemplate.convertAndSend(queue.getName(), json);
    }

    private String convertToJson(SolicitationCardData data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
          return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }




}
