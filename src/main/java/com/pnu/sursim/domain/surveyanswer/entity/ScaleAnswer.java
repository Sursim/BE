package com.pnu.sursim.domain.surveyanswer.entity;

import com.pnu.sursim.domain.survey.entity.Question;
import com.pnu.sursim.domain.survey.entity.QuestionOption;
import com.pnu.sursim.domain.survey.entity.Scale;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ScaleAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="survey_answer_id")
    private SurveyAnswer surveyAnswer;

    @Enumerated(EnumType.STRING)
    private Scale scale;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Min(1)
    @Max(5)
    private int value;

}