package com.uchamod.quiz_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {

   private String category;
   private String title;
   private Integer numQ;
}
