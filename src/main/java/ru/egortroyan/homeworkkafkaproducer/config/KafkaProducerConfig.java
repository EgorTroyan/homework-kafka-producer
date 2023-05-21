package ru.egortroyan.homeworkkafkaproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.egortroyan.homeworkkafkaproducer.dao.LogDao;

import java.util.HashMap;

@Configuration
public class KafkaProducerConfig {

    public static final String KAFKA_TOPIC = "topic-logging";
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String address;

    @Bean
    public NewTopic logTopic() {
        return TopicBuilder.name(KAFKA_TOPIC)
                .partitions(4)
                .replicas(1)
                .build();
    }

    @Bean
    public ProducerFactory<String, LogDao> producerFactory() {
        var config = new HashMap<String, Object>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, LogDao> stringKafkaTemplate(ProducerFactory<String, LogDao> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
