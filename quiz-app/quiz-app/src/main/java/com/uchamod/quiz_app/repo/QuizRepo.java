package com.uchamod.quiz_app.repo;


import com.uchamod.quiz_app.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuizRepo extends JpaRepository<Quiz,UUID> {

}
