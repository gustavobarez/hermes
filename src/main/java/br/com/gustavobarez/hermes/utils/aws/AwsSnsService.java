package br.com.gustavobarez.hermes.utils.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
public class AwsSnsService {

    private final SnsClient snsClient;

    private final String topicArn;

    public AwsSnsService(SnsClient snsClient, @Value("${aws.sns.topic-arn}") String topicArn) {
        this.snsClient = snsClient;
        this.topicArn = topicArn;
    }

    public void publishMessage(String eventType, Object entity) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            String entityJson = mapper.writeValueAsString(entity);

            String message = String.format("{\"event\":\"%s\",\"data\":%s}", eventType, entityJson);

            PublishRequest request = PublishRequest.builder()
                    .topicArn(topicArn)
                    .subject(eventType)
                    .message(message)
                    .build();

            snsClient.publish(request);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao publicar mensagem", e);
        }
    }

}
