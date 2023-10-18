package com.br.cleyton.gymprosystem.errors;

import com.br.cleyton.gymprosystem.model.instructor.InstructorModel;

public class IllegalCpfException extends Exception{

    public IllegalCpfException(String e) {
        super(e);
    }
    public static void validateCpf (String cpf) throws IllegalCpfException {
        if(cpf.length() != 11) {
            throw new IllegalCpfException("Cpf must be 11 characters long");
        }
    }
}
