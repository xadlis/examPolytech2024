package com.intech.hero.api;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TeamDto {

    private long idTeam;

    @NotNull
    private String name;

    private List<MemberDto> members;

}
