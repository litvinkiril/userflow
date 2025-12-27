package com.hse.userflow.storingservice.repository;

import com.hse.userflow.storingservice.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Integer> {
}
