package com.example.daytoday.api;

import com.example.daytoday.models.Work;
import com.example.daytoday.services.WorkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/works")
@AllArgsConstructor
public class workControllers {
    public final WorkService workService;

//    @GetMapping
//    public List<Work> fetchAllWorks() {
//        return workService.fetchAllWorks();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Work> getSingleWork(@PathVariable("id") String id) {
//        return workService.getWork(id);
//    }

    @PostMapping
    public final Work createNewWork(@RequestBody Work work) {
        return workService.newWork(work);
    }

//    @PatchMapping("/{id}")
//    public Optional<Work> updateWork(@PathVariable("id") String id, @RequestBody Map<Object, Object> fields) {
//        return workService.updateWork(id, fields);
//    }
//
//    @DeleteMapping("/{id}")
//    public final int DeleteWork(@PathVariable("id") String id) {
//        return workService.deleteWork(id);
//    }
}
