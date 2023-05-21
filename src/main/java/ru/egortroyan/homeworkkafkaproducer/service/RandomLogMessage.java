package ru.egortroyan.homeworkkafkaproducer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.egortroyan.homeworkkafkaproducer.dao.LogDao;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class RandomLogMessage {
    private static final Random random =new Random();
    private static final int ERROR_PROBABILITY = 5;
    private static final int WARN_PROBABILITY = 15;

    private final LoggerService loggerService;

    @Scheduled(initialDelay = 10000, fixedDelay = 1000)
    private void messageProducer(){
        int r = random.nextInt(100);
        if(r < ERROR_PROBABILITY) {
            loggerService.messageLog(LogDao.builder()
                            .timeStamp(LocalDateTime.now())
                            .level(LogLevel.ERROR)
                            .message("Все пропало")
                            .build());
            return;
        }
        if(r < WARN_PROBABILITY) {
            loggerService.messageLog(LogDao.builder()
                    .timeStamp(LocalDateTime.now())
                    .level(LogLevel.WARN)
                    .message("Почти упало")
                    .build());
            return;
        }
        loggerService.messageLog(LogDao.builder()
                .timeStamp(LocalDateTime.now())
                .level(LogLevel.INFO)
                .message("Все супер!")
                .build());
    }
}
