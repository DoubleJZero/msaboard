package msaboard.api.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KafkaFactoryBuilder {

    public static <T> ConcurrentKafkaListenerContainerFactory<String, T> listenerContainerFactory(Class<T> messageType, String bootstrapServers, String groupId) {
        return listenerContainerFactory(consumerFactory(messageType, bootstrapServers, groupId));
    }

    public static <T> ConcurrentKafkaListenerContainerFactory<String, T> listenerContainerFactory(ConsumerFactory<String, T> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    public static <T> ConsumerFactory<String, T> consumerFactory(Class<T> messageType, String bootstrapServers, String groupId) {
        return consumerFactory(messageType, bootstrapServers, groupId, "*");
    }

    public static <T> ConsumerFactory<String, T> consumerFactory(Class<T> messageType, String bootstrapServers, String groupId, String trustedPackages) {
        JsonDeserializer<T> deserializer = new JsonDeserializer<>(messageType);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.setUseTypeMapperForKey(true);
        Arrays.asList(trustedPackages.split(",")).forEach(deserializer::addTrustedPackages);
        return kafkaConsumerFactory(consumerConfigs(bootstrapServers, groupId, trustedPackages), new StringDeserializer(), deserializer);
    }

    public static DefaultKafkaConsumerFactory kafkaConsumerFactory(Map<String, Object> configurations, StringDeserializer stringDeserializer, JsonDeserializer jsonDeserializer) {
        return new DefaultKafkaConsumerFactory<>(configurations, stringDeserializer, jsonDeserializer);
    }

    public static Map<String, Object> consumerConfigs(String bootstrapServers, String groupId, String trustedPacakges) {
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Arrays.asList(bootstrapServers.split(",")));
        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //configurations.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 300000); // 5분
        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configurations.put(JsonDeserializer.TRUSTED_PACKAGES, trustedPacakges);
        return configurations;
    }
}
