package com.uchamod.question_app.controller;


import com.uchamod.question_app.model.QuestionWrapper;
import com.uchamod.question_app.model.Questions;
import com.uchamod.question_app.model.QuizResponse;
import com.uchamod.question_app.service.QuestionService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("api/questions")
public class questionController {

    private final QuestionService questionService;

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions(){
        return questionService.getAllQuestions();
    }


    @GetMapping("/getQuestionByCategory/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/deleteById/{uuid}")
    public ResponseEntity<String> deleteById(@PathVariable UUID uuid){
        return  questionService.deleteById(uuid);
   }

   @PutMapping("/updateQuestion")
    public ResponseEntity<String> updateQuestion(@RequestBody Questions question){
       return  questionService.updateQuestion(question);
   }
   //generate quiz
    @GetMapping("/generateQuestions")
    public ResponseEntity<List<UUID>> generateQuiz(@RequestParam String category,@RequestParam Integer numQ){
        return questionService.generateQuiz(category,numQ);
    }
  //get questions
    @PostMapping ("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<UUID> questionIds){
        return questionService.generateQuiz(questionIds);
    }
//get marks
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScoreByResponse(@RequestBody List<QuizResponse> responses){
        return questionService.getScoreByResponse(responses);
    }
}
