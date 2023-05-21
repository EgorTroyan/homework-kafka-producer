package ru.egortroyan.homeworkkafkaproducer.service;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.egortroyan.homeworkkafkaproducer.config.KafkaProducerConfig;
import ru.egortroyan.homeworkkafkaproducer.dao.LogDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class LoggerServiceImpl implements LoggerService{

    private final KafkaTemplate<String, LogDao> kafkaTemplate;
    @Override
    public void messageLog(LogDao log) {
        String topic = KafkaProducerConfig.KAFKA_TOPIC;
//        LogDao log = null;
//        switch (level) {
//            case ERROR -> log = LogDao.builder().timeStamp(LocalDateTime.now()).level(LogLevel.ERROR).message(message).build();
//            case WARN -> log = LogDao.builder().timeStamp(LocalDateTime.now()).level(LogLevel.WARN).message(message).build();
//            case INFO -> log = LogDao.builder().timeStamp(LocalDateTime.now()).level(LogLevel.INFO).message(message).build();
//            case DEBUG -> log = LogDao.builder().timeStamp(LocalDateTime.now()).level(LogLevel.DEBUG).message(message).build();
//            case TRACE -> LogDao.builder().timeStamp(LocalDateTime.now()).level(LogLevel.TRACE).message(message).build();
//        }
//        if(log != null) {
            CompletableFuture<SendResult<String, LogDao>> future = kafkaTemplate.send(topic, String.valueOf(LocalDateTime.now().getSecond()), log);
            try {
                System.out.println("----------------------------------------------");
                System.out.println("Topic: " + future.get().getProducerRecord().topic());
                System.out.println("Offset: " + future.get().getRecordMetadata().offset());
                System.out.println("Partition: " + future.get().getRecordMetadata().partition());
                System.out.println("Value: " + future.get().getProducerRecord().value());
                System.out.println("----------------------------------------------");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
//        }
    }
}
