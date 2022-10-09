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

    @GetMapping("/fetchWorks")
    public List<Work> fetchWorks() {
        return workService.fetchAllWorks(null);
    }

    @GetMapping("/fetchWorks/{workerNumber}")
    public List<Work> fetchAllWorks(@PathVariable("workerNumber") String number) {
        return workService.fetchAllWorks(number);
    }

    @GetMapping("/{id}")
    public Optional<Work> getSingleWork(@PathVariable("id") String id) {
        return workService.getWork(id);
    }

    @GetMapping("/{id}/requestPool")
    public List<String> getWorkRequestPool(@PathVariable("id") String id) {
        return workService.getAllRequests(id);
    }

    @PostMapping("/{clientNumber}")
    public final Work createNewWork(@PathVariable("clientNumber") String number, @RequestBody Work work) {
        return workService.newWork(number, work);
    }

    @PostMapping("/{workId}/{workerNumber}")
    public final int applyForWork(@PathVariable("workId") String id, @PathVariable("workerNumber") String workerNumber) {
        return workService.applyTo(id, workerNumber);
    }

    @PostMapping ("/{id}/requestPool/{workerNumber}")
    public final int acceptWorker(@PathVariable("id") String id, @PathVariable("workerNumber") String number) {
        return workService.acceptWorker(id, number);
    }

    @PatchMapping("/{id}")
    public Optional<Work> updateWork(@PathVariable("id") String id, @RequestBody Map<Object, Object> fields) {
        return workService.updateWork(id, fields);
    }

    @DeleteMapping("/{id}")
    public final int DeleteWork(@PathVariable("id") String id) {
        return workService.deleteWork(id);
    }
}
