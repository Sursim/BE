package com.pnu.sursim.domain.survey.dto;

import com.pnu.sursim.domain.survey.entity.Question;
import com.pnu.sursim.domain.survey.entity.QuestionType;
import com.pnu.sursim.global.exception.CustomException;
import com.pnu.sursim.global.exception.ErrorCode;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class QuestionList {
    private final HashMap<Long, Question> questionHashMap;

    public QuestionList(List<Question> questionList) {
        this.questionHashMap = questionList.stream()
                .collect(Collectors.toMap(
                        Question::getId,                    //키는 id로
                        question -> question,               //값은 오브젝트 자체
                        (existing, replacement) -> {throw new CustomException(ErrorCode.INCORRECT_QUESTION);}, // 중복된 키가 있을 경우 예외 발생
                        HashMap::new   // 결과를 HashMap으로 수집
                ));
    }

    // 문항 ID로 존재 여부 확인 메서드
    public boolean existsById(Long questionId) {
        return questionHashMap.containsKey(questionId);
    }

    // 문항 ID로 문항을 반환하는 메서드
    public Question getQuestionById(Long questionId) {
        return Optional.ofNullable(questionHashMap.get(questionId))
                .orElseThrow(() -> new CustomException(ErrorCode.QUESTION_NOT_FOUND));
    }

    public QuestionType getQuestionTypeById(Long questionId) {
        Question question = Optional.ofNullable(questionHashMap.get(questionId))
                .orElseThrow(() -> new CustomException(ErrorCode.QUESTION_NOT_FOUND));

        return question.getQuestionType();
    }


}
