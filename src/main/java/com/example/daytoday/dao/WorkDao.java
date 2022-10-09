package com.example.daytoday.dao;

import com.example.daytoday.models.Work;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface WorkDao {
    Work createWork(String number, Work work);

    List<Work> fetchAllWorks();

    int deleteWork(String id);

    Optional<Work> fetchWork(String id);

    Optional<Work> updateWork(String id, Map<Object, Object> fields);

    int applyTo(String id, String number);

    int acceptWorker(String id, String number);

    List<Work> getWorkByUserNumber(String number);

}
