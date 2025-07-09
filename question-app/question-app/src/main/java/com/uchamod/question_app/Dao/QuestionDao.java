package com.uchamod.question_app.Dao;


import com.uchamod.question_app.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions,Integer> {

    List<Questions> findByCategory(String category);
}
