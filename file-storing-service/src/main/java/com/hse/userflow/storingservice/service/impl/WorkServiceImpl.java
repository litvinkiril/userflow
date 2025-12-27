package com.hse.userflow.storingservice.service.impl;

import com.hse.userflow.dto.work.WorkCreateDto;
import com.hse.userflow.dto.work.WorkDto;
import com.hse.userflow.storingservice.exception.NotFoundException;
import com.hse.userflow.storingservice.model.Work;
import com.hse.userflow.storingservice.repository.WorkRepository;
import com.hse.userflow.storingservice.service.WorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hse.userflow.storingservice.mapper.WorkMapper.toDto;
import static com.hse.userflow.storingservice.mapper.WorkMapper.toEntity;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;


    @Override
    @Transactional
    public WorkDto addWord(WorkCreateDto newWork) {
        Work work = workRepository.save(toEntity(newWork));
        log.debug("работа{} успешно добавлена", work);
        return toDto(work);
    }

    @Override
    @Transactional
    public void deleteWork(Integer workId) {
        Work work = getWorkById(workId);
        workRepository.deleteById(workId);
        log.debug("работа с id{} успешно удалена", workId);
    }

    private Work getWorkById(Integer workId) {
        return workRepository.findById(workId).orElseThrow(() ->
                new NotFoundException("работа с id " + workId + " не найдена"));

    }
}
