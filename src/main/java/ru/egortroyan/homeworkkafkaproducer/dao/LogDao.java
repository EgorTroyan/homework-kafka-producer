package ru.egortroyan.homeworkkafkaproducer.dao;

import lombok.*;
import ru.egortroyan.homeworkkafkaproducer.service.LogLevel;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LogDao {
    private LocalDateTime timeStamp;
    private LogLevel level;
    private String message;
}
