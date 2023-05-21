package ru.egortroyan.homeworkkafkaproducer.service;

import ru.egortroyan.homeworkkafkaproducer.dao.LogDao;

public interface LoggerService {
    void messageLog(LogDao log);
}
