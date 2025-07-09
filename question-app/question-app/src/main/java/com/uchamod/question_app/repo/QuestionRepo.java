package com.uchamod.question_app.repo;


import com.uchamod.question_app.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface QuestionRepo extends JpaRepository<Questions, UUID> {
    List<Questions> findByCategory(String category);
    @Query(value = "SELECT q.id FROM questions q where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<UUID> findRandomQuestionsByCategory(String category,Integer numQ);
}
