package com.bezkoder.spring.thymeleaf.pagingsorting.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.thymeleaf.pagingsorting.entity.Tutorial;
import org.springframework.data.repository.history.RevisionRepository;

@Repository
@Transactional
public interface TutorialRepository extends JpaRepository<Tutorial, Integer>, RevisionRepository<Tutorial, Integer, Integer> {

    Page<Tutorial> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("UPDATE Tutorial t SET t.published = :published WHERE t.id = :id")
    @Modifying
    public void updatePublishedStatus(Integer id, boolean published);
}
