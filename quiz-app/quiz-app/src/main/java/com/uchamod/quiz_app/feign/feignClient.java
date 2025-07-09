package com.uchamod.quiz_app.feign;

import com.uchamod.quiz_app.model.QuestionWrapper;
import com.uchamod.quiz_app.model.QuizResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient("QUESTION-SERVICE")
public interface feignClient {
    //generate quiz
    @GetMapping("api/questions/generateQuestions")
    public ResponseEntity<List<UUID>> generateQuiz(@RequestParam String category, @RequestParam Integer numQ);
    //get questions
    @PostMapping("api/questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<UUID> questionIds);

    //get marks
    @PostMapping("api/questions/getScore")
    public ResponseEntity<Integer> getScoreByResponse(@RequestBody List<QuizResponse> responses);
}
