package com.br.cleyton.gymprosystem.exceptions;

public class IllegalCpfException extends Exception{

    public IllegalCpfException(String e) {
        super(e);
    }
    public static void validateCpf (String cpf) throws IllegalCpfException {
        if(cpf.length() != 11) {
            throw new ApiRequestException("Cpf must be 11 characters long");
        }
    }
}
