package com.uchamod.quiz_app.controller;


import com.uchamod.quiz_app.model.QuestionWrapper;
import com.uchamod.quiz_app.model.QuizResponse;
import com.uchamod.quiz_app.model.QuizDto;
import com.uchamod.quiz_app.service.QuizServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("api/quiz")
public class quizController {

    private final QuizServices quizService;


    @PostMapping("/createQuiz")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizdto){
       return quizService.createQuiz(quizdto.getCategory(),quizdto.getNumQ(),quizdto.getTitle());
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable UUID id){
        return quizService.getQuiz(id);
    }

    @PostMapping("/submitQuiz/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable UUID id,@RequestBody List<QuizResponse> quizResponses){
        return quizService.submitQuiz(id,quizResponses);
    }
}
