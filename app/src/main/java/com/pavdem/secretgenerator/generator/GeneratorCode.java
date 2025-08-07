package com.pavdem.secretgenerator.generator;

import java.security.SecureRandom;

public class GeneratorCode {
    // Переменные для хранения настроек
    boolean useLetters = true; // Включение латинских букв
    boolean useLowercase = true; // Включение строчных букв
    int passwordLength = 8; // Длина пароля
    boolean useSymbols = true; // Включение спецсимволов

    // Метод генерации пароля с учетом всех настроек
    public String generatePassword(int length, boolean useLetters, boolean useLowercase, boolean useSymbols) {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()_+-=[]{}|;:,.<>?";

        String charPool = numbers;  // Начнем с чисел

        if (useLetters) {
            charPool += useLowercase ? lowerCaseLetters : upperCaseLetters;
        }

        if (useSymbols) {
            charPool += symbols; // Добавим спецсимволы, если они включены
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(charPool.charAt(random.nextInt(charPool.length())));
        }
        return password.toString();
    }
}
