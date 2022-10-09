package com.example.daytoday.services;

import com.example.daytoday.dao.UserDao;
import com.example.daytoday.dao.WorkDao;
import com.example.daytoday.models.User;
import com.example.daytoday.models.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkService {
    private final WorkDao workDao;

    private final UserDao userDao;

    @Autowired
    public WorkService(@Qualifier("workRepo") WorkDao workDao, @Qualifier("userRepo") UserDao userDao) {
        this.userDao = userDao;
        this.workDao = workDao;
    }

    public final Work newWork(String number, Work work) {
        return workDao.createWork(number, work);
    }

    public final List<Work> fetchAllWorks(String number) {
        List<Work> works = workDao.fetchAllWorks();

        if(number != null) {
            Optional<User> user = userDao.getSingleUser(number);

            user.ifPresent(
                    u -> {
                        works.forEach(work -> work.setDistance(
                            2*6713*Math.asin(Math.sqrt(
                                (
                                    Math.sin((work.getCoords().get(0) - u.getCords().get(0))/2)*Math.sin((work.getCoords().get(0) - u.getCords().get(0))/2)
                                        + Math.cos(work.getCoords().get(0))*Math.cos(u.getCords().get(0))
                                        * Math.sin((work.getCoords().get(1) - u.getCords().get(1))/2)*Math.sin((work.getCoords().get(1) - u.getCords().get(1))/2)
                                )
                            ) )
                        ));
                    }
            );

            works.sort(Comparator.comparingDouble(Work::getDistance));
        }

        return works;
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

    public final int acceptWorker(String id, String number) {
        return workDao.acceptWorker(id, number);
    }

}