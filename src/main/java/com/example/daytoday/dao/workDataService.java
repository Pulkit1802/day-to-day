package com.example.daytoday.dao;

import com.example.daytoday.models.User;
import com.example.daytoday.models.Work;
import com.example.daytoday.models.Work;
import com.example.daytoday.repos.WorkMongoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Repository("workRepo")
public class workDataService implements WorkDao {
    
    private final WorkMongoRepo workRepo;
    @Override
    public Work createWork(Work work) {
        return workRepo.insert(work);
    }

//    @Override
//    public List<Work> fetchAllWorks() {
//        return workRepo.findAll();
//    }
//
//    @Override
//    public int deleteWork(String id) {
//        final int[] workDeleted = {0};
//
//        workRepo.findById(id)
//                .ifPresent( u -> {
//                    workRepo.deleteById(id);
//                    workDeleted[0] = 1;
//                });
//
//        return workDeleted[0];
//    }
//
//    @Override
//    public Optional<Work> fetchWork(String id) {
//        return workRepo.findById(id);
//    }
//
//    @Override
//    public Optional<Work> updateWork(String id, Map<Object, Object> fields) {
//        Optional<Work> updateWork = workRepo.findById(id);
//        updateWork.ifPresent(work -> {
//            fields.forEach((key, value) -> {
//                Field field = ReflectionUtils.findField(Work.class, (String) key);
//                assert field != null;
//                field.setAccessible(true);
//                ReflectionUtils.setField(field, work, value);
//            });
//            deleteWork(id);
//            workRepo.insert(work);
//        });
//        return updateWork;
//    }
}
