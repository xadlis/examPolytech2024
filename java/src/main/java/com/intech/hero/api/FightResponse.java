package com.intech.hero.api;

import lombok.Data;

import java.util.List;

@Data
public class FightResponse {

    private List<MemberDto> fighters;

    private List<FightStepDto> steps;

    private MemberDto winner;

}
