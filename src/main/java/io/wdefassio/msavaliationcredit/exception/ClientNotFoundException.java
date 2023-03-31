package io.wdefassio.msavaliationcredit.exception;

public class ClientNotFoundException extends Exception{

    public ClientNotFoundException() {
        super("client not found for this cpf");
    }
}
