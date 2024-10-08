package com.pnu.sursim.domain.survey.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.pnu.sursim.domain.survey.entity.RewardType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@SuperBuilder
public class SurveyWithRewardResponse extends SpecSurveyResponse {
    private RewardResponse reward;

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record RewardResponse(String title, RewardType rewardType, int count, String rewardImg) {
    }
}
