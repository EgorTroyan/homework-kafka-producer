package ru.egortroyan.homeworkkafkaproducer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.egortroyan.homeworkkafkaproducer.dao.LogDao;
import ru.egortroyan.homeworkkafkaproducer.service.LogLevel;
import ru.egortroyan.homeworkkafkaproducer.service.LoggerService;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class SimpleController {
    private final LoggerService loggerService;

    @GetMapping("/")
    public LogDao getRequest() {
        LogDao logDao = LogDao.builder()
                .timeStamp(LocalDateTime.now())
                .level(LogLevel.INFO)
                .message("GET request")
                .build();
        loggerService.messageLog(logDao);
        return logDao;
    }
}
