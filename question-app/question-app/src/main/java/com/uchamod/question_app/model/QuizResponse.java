package com.uchamod.question_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class QuizResponse {
    private UUID id;
    private String right_answer;
}
