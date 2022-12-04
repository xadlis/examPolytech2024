package com.intech.hero.errors.codes;

public enum ErrorCodes {

    HERO_NOT_FOUND("Superhero with id %s not found"),
    HERO_WRONG_FORMAT("Superhero format is invalid");

    private final String message;

    ErrorCodes(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
