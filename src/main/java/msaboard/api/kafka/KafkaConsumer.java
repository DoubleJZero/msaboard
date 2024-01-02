package msaboard.api.kafka;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msaboard.api.dto.BoardDto;
import msaboard.api.service.BoardService;
import msacore.constant.DateFormatPattern;
import msacore.dto.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * KafkaConsumer
 *
 * <p>상세 설명</p>
 *
 * <p>코드 히스토리 (필요시 변경사항 기록)</p>
 *
 * @author jandb
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final BoardService boardService;

    @KafkaListener(topics = {"user-service-topic"}, groupId = "${kafka.consumer.group-id}")
    public void kafkaListener(Message<BoardDto> kafkaMessage) throws JsonProcessingException {

        ObjectMapper objectMapper = getObjectMapper();
        String body = objectMapper.writeValueAsString(kafkaMessage.getPayload());

        //결재 DTO 반환
        BoardDto boardDto = objectMapper.readValue(body, BoardDto.class);

        log.debug("############################################################");
        log.debug("kafka listener :: boardDto.getRgstId() :: {} ", boardDto.getRgstId());
        log.debug("############################################################");

        System.out.println(boardDto.getRgstId());
        //boardService.deleteBoardByRgstId(boardDto.getRgstId());
    }

    /**
     * ObjectMapper 반환
     * @return ObjectMapper
     */
    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<>() {
            @Override
            public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                return LocalDateTime.parse(p.getValueAsString(), DateTimeFormatter.ofPattern(DateFormatPattern.yyyy_MM_dd_HH_mm_ss));
            }
        });
        objectMapper.registerModule(javaTimeModule);

        /*
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Enum.class, new StdDeserializer<>(Enum.class) {
            @Override
            public Enum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                ObjectCodec objectCodec = p.getCodec();
                JsonNode jsonNode = objectCodec.readTree(p);
                String name = jsonNode.get("name").asText();
                String currentName = p.getParsingContext().getCurrentName();

                if ("userTopic".equals(currentName)) {
                    return Topic.valueOf(name);
                }

                return null;
            }
        });

        objectMapper.registerModule(simpleModule);
        */
        return objectMapper;
    }
}
