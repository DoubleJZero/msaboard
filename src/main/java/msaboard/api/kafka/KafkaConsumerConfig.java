package msaboard.api.kafka;

import msacore.dto.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@EnableKafka
@ConditionalOnProperty("kafka.bootstrap-servers")
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.consumer.group-id}")
    private String groupId;

    @Bean
    @ConditionalOnMissingBean
    public ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory() {
        return KafkaFactoryBuilder.listenerContainerFactory(Message.class, bootstrapServers, groupId);
    }
}
