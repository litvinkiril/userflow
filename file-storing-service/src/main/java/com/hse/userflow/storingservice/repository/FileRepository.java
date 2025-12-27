package com.hse.userflow.storingservice.repository;

import com.hse.userflow.storingservice.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FileRepository extends JpaRepository<File, Integer> {


    @Query("""
            SELECT f FROM File f
            JOIN FETCH f.student
            JOIN FETCH f.work
            WHERE f.uploadedAt < :uploadedAt
            AND f.work.id = :workId
            """)
    List<File> findAllEarlierReports (@Param("workId") Integer workId, @Param("uploadedAt")LocalDateTime uploadedAt);
}
