package com.example.daytoday.dao;

import com.example.daytoday.models.User;
import com.example.daytoday.models.Work;
import com.example.daytoday.repos.UserMongoRepo;
import com.example.daytoday.repos.WorkMongoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository("workRepo")
public class workDataService implements WorkDao {

    private final WorkMongoRepo workRepo;
    private final UserMongoRepo userRepo;
    @Override
    public Work createWork(String number, Work work) {
        if(userRepo.findUserByPhoneNumber(number).isPresent()) {
            work.setClientNumber(number);
            return workRepo.insert(work);
        }
        else
            return null;
    }

    @Override
    public List<Work> fetchAllWorks() {
        return workRepo.findAll();
    }

    @Override
    public Optional<Work> fetchWork(String id) {
        return workRepo.findById(id);
    }

    @Override
    public int deleteWork(String id) {
        final int[] workDeleted = {0};

        workRepo.findById(id)
                .ifPresent( u -> {
                    workRepo.deleteById(id);
                    workDeleted[0] = 1;
                });

        return workDeleted[0];
    }

    @Override
    public Optional<Work> updateWork(String id, Map<Object, Object> fields) {
        Optional<Work> updateWork = workRepo.findById(id);
        updateWork.ifPresent(work -> {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Work.class, (String) key);
                assert field != null;
                field.setAccessible(true);
                ReflectionUtils.setField(field, work, value);
            });
            workRepo.save(work);
        });
        return updateWork;
    }

    @Override
    public int applyTo(String id, String number) {
        final int[] workUpdated = {0};
        workRepo.findById(id)
            .ifPresent(
                work -> {
                    work.appendWorkRequestPool(number);
                    workRepo.save(work);
                    workUpdated[0] = 1;
                }
            );
        return workUpdated[0];
    }

    @Override
    public int acceptWorker(String id, String number) {
        final int[] workAccepted = {0};

        Optional<Work> work = workRepo.findById(id);
        Optional<User> user = userRepo.findUserByPhoneNumber(number);

        if(user.isPresent() && work.isPresent() && user.get().getIsWorker()) {
            user.get().addWorkId(id);
            work.get().setIsAvailable(false);
            work.get().setWorkerNumber(number);
            workAccepted[0] = 1;
            workRepo.save(work.get());
            userRepo.save(user.get());
        }

        return workAccepted[0];
    }

    @Override
    public List<Work> getWorkByUserNumber(String number) {
        Optional<User> user = userRepo.findUserByPhoneNumber(number);
        if(user.isPresent()) {
            return workRepo.findWorksByClientNumber(number);
        } {
            return new ArrayList<>();
        }
    }
}
