package com.example.daytoday.dao;

import com.example.daytoday.models.Work;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface WorkDao {
    Work createWork(Work work);

//    List<Work> fetchAllWorks();
//
//    int deleteWork(String id);
//
//    Optional<Work> fetchWork(String id);
//
//    Optional<Work> updateWork(String id, Map<Object, Object> fields);
}
