package com.example.daytoday.services;

import com.example.daytoday.dao.UserDao;
import com.example.daytoday.dao.WorkDao;
import com.example.daytoday.models.Filter;
import com.example.daytoday.models.User;
import com.example.daytoday.models.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public final List<Work> fetchAllWorks(String number, Filter filter) {
        List<Work> works = workDao.fetchAllWorks();

        if(filter != null) {
            works = works.stream()
                    .filter(work -> (work.getPrice() > filter.getMinPrice() && work.getPrice() < filter.getMaxPrice()) )
                    .collect(Collectors.toList());

            if(filter.getRequiredSkills().size() > 0) {
                works = works.stream()
                        .filter(work -> {
                            if(work.getRequiredSkills().size() > 0) {
                                final Boolean[] include = {false};
                                filter.getRequiredSkills().forEach(skill -> {
                                    if(work.getRequiredSkills().contains(skill)) {
                                        include[0] = true;
                                    }
                                });
                                return include[0];
                            }
                            return true;
                        }).collect(Collectors.toList());
            }

        }

        if(number != null) {

            works = works.stream().filter(Work::getIsAvailable).collect(Collectors.toList());

            Optional<User> user = userDao.getSingleUser(number);

            List<Work> finalWorks = works;
            user.ifPresent(
                    u -> {
                        double lat1 = Math.toRadians(u.getCords().get(0));
                        double long1 = Math.toRadians(u.getCords().get(1));
                        finalWorks.forEach(work -> {

                            double lat2 = Math.toRadians(work.getCoords().get(0));
                            double long2 = Math.toRadians(work.getCoords().get(1));

                            double dLat = lat2 - lat1;
                            double dLong = long2 - long1;

                            double d = Math.pow(Math.sin(dLat/2), 2) + Math.cos(lat1)*Math.cos(lat2)*Math.pow(Math.sin(dLong/2), 2);

                            d = 2 * 6371 * Math.asin(Math.sqrt(d));

                            work.setDistance(d);

                        });
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