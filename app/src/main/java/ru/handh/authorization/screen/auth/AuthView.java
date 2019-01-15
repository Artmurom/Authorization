package ru.handh.authorization.screen.auth;

public interface AuthView {
    int EMPTY_EMAIL = 0;
    int UNACCEPTABLE_EMAIL = 1;
    int EMPTY_PASS = 2;
    int UNACCEPTABLE_PASS = 3;
    int ERROR_CONNECTION = 4;

    void openRegistrationScreen();

    void openResetPassScreen();

    void showError(int type);

    void showWeather(String weather);

    void showMessageRegistrationSuccess();

    void showMessageResetPassSuccess();

    void close();
}
