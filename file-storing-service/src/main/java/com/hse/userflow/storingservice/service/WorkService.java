package com.hse.userflow.storingservice.service;

import com.hse.userflow.dto.work.WorkCreateDto;
import com.hse.userflow.dto.work.WorkDto;

public interface WorkService {
    WorkDto addWord(WorkCreateDto newWork);
    void deleteWork(Integer workId);
}
