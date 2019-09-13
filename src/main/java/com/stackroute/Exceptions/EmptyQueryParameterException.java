package com.stackroute.Exceptions;

public class EmptyQueryParameterException extends Exception{
        private static final String message = "Empty Query parameters. Please give at least one query param.";

        public EmptyQueryParameterException() {
            super(message);
        }
    }

