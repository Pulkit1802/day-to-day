package com.example.daytoday.services;

import com.example.daytoday.dao.WorkDao;
import com.example.daytoday.models.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WorkService {
    private final WorkDao workDao;

    @Autowired
    public WorkService(@Qualifier("workRepo") WorkDao workDao) {
        this.workDao = workDao;
    }

    public final Work newWork(String number, Work work) {
        return workDao.createWork(number, work);
    }

    public final List<Work> fetchAllWorks() {
        return workDao.fetchAllWorks();
    }

    public final Optional<Work> getWork(String id) {
        return workDao.fetchWork(id);
    }

    public final int deleteWork(String id) {
        return workDao.deleteWork(id);
    }

    public final Optional<Work> updateWork(String id, Map<Object, Object> fields) {
        return workDao.updateWork(id, fields);
    }

    public final int applyTo(String id, String number) {
        return workDao.applyTo(id, number);
    }

    public final List<String> getAllRequests(String id) {
        Optional<Work> work = workDao.fetchWork(id);
        if(work.isPresent()) {
            return work.get().getWorkRequestPool();
        } else {
            return new ArrayList<>();
        }
    }

}
