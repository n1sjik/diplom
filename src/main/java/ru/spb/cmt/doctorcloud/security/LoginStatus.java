package ru.spb.cmt.doctorcloud.security;

public enum LoginStatus {

    SUCCESS(""),
    INVALID_USER("Пользователь не найден"),
    INVALID_PASSWORD("Неверный пароль");

    private final String message;

    LoginStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
