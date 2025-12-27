package com.hse.userflow.storingservice.controller;

import com.hse.userflow.dto.work.WorkCreateDto;
import com.hse.userflow.dto.work.WorkDto;
import com.hse.userflow.storingservice.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/works")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService workService;

    @PostMapping
    public WorkDto addWork(@RequestBody WorkCreateDto newWork) {
        return workService.addWord(newWork);
    }

    @DeleteMapping("/{workId}")
    public void deleteWork(@PathVariable Integer workId) {
        workService.deleteWork(workId);
    }

}
