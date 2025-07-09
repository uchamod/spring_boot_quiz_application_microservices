package com.uchamod.quiz_app.service;



import com.uchamod.quiz_app.feign.feignClient;
import com.uchamod.quiz_app.model.QuestionWrapper;
import com.uchamod.quiz_app.model.Quiz;
import com.uchamod.quiz_app.model.QuizResponse;
import com.uchamod.quiz_app.repo.QuizRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class QuizServices {

    private final QuizRepo quizRepo;


    private final feignClient feignclient;

    public ResponseEntity<String> createQuiz(String category,Integer numQ,String title) {
      try{
          List<UUID> questions = feignclient.generateQuiz(category,numQ).getBody();
          Quiz quiz=new Quiz();
          quiz.setTitle(title);
          quiz.setQuestionsIds(questions);
          quizRepo.save(quiz);
          return new ResponseEntity<>("quiz is cretaed",HttpStatus.CREATED);
      }catch (Exception e){
         System.out.println(e);
      }
        return new ResponseEntity<>("quiz is not cretaed",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(UUID id) {
        List<QuestionWrapper> questionWrappers=new ArrayList<>();

        try{
            Quiz quiz=  quizRepo.findById(id).get();
            List<UUID> questionIds=quiz.getQuestionsIds();
            questionWrappers= feignclient.getQuestions(questionIds).getBody();


            return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> submitQuiz(UUID id, List<QuizResponse> quizResponses) {
        try{
            Integer score= feignclient.getScoreByResponse(quizResponses).getBody();
            return new ResponseEntity<>(score,HttpStatus.OK);
       }catch (Exception e){

        }
        return new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
    }
}
