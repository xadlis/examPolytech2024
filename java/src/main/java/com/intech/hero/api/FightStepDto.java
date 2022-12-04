package com.intech.hero.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FightStepDto {

    private int step;

    private double fighter1Damages;

    private double fighter2Damages;

}
