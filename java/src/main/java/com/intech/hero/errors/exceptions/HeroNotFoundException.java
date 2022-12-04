package com.intech.hero.errors.exceptions;

import lombok.Data;

import java.util.NoSuchElementException;

@Data
public class HeroNotFoundException extends NoSuchElementException{

    private long relatedId;

    public HeroNotFoundException(long id){
        this.relatedId = id;
    }
}
