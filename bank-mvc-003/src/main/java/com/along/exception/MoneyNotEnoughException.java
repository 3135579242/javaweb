package com.along.exception;

public class MoneyNotEnoughException extends Exception {

    public MoneyNotEnoughException() {
    }

    public MoneyNotEnoughException(String message) {
        super(message);
    }
}
