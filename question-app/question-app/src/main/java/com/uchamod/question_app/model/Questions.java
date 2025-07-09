package com.uchamod.question_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String question_name;
    private String category;
    private String score;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String right_answer;
}
