package com.uchamod.question_app.service;


import com.uchamod.question_app.model.QuestionWrapper;
import com.uchamod.question_app.model.Questions;
import com.uchamod.question_app.model.QuizResponse;
import com.uchamod.question_app.repo.QuestionRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepo questionRepo;


    public ResponseEntity<List<Questions>> getAllQuestions() {
          try{
              System.out.println(questionRepo.findAll());
              return new ResponseEntity<>(questionRepo.findAll(),HttpStatus.ACCEPTED);
          }catch (Exception e){
              e.fillInStackTrace();
          }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Questions question) {
        try{
            questionRepo.save(question);
            return new ResponseEntity<>("succsuss", HttpStatus.CREATED);
        }catch (Exception e){
            e.fillInStackTrace();
        }

        return new ResponseEntity<>("cannot add question", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> deleteById(UUID uuid) {
        try{
            if(questionRepo.existsById(uuid)){
                questionRepo.deleteById(uuid);
            }
            return new ResponseEntity<>("succsuss", HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(Questions question){
        try{
           if(questionRepo.existsById(question.getId())){
               questionRepo.save(question);
           }
            return new ResponseEntity<>("succsuss", HttpStatus.CREATED);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return new ResponseEntity<>("cannot updated", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<UUID>> generateQuiz(String category, Integer numQ) {
        List<UUID> qusetions=questionRepo.findRandomQuestionsByCategory(category,numQ);
        return new ResponseEntity<>(qusetions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> generateQuiz(List<UUID> questionIds) {
        List<QuestionWrapper> wrapper = new ArrayList<>();
        try{
            for(UUID id : questionIds){
                Questions question=  questionRepo.findById(id).get();
                QuestionWrapper questionWrapper=new QuestionWrapper(question.getId(),question.getRight_answer(),question.getCategory(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
                wrapper.add(questionWrapper);
            }
            return new ResponseEntity<>(wrapper,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(wrapper,HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Integer> getScoreByResponse(List<QuizResponse> responses) {
        try{
            int score=0;
            for(QuizResponse response : responses){
                Questions questions=questionRepo.findById(response.getId()).get();
                if(questions.getRight_answer().equals(response.getRight_answer())){
                        score++;
                }
            }
            return new ResponseEntity<>(score,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
        }
    }
}
