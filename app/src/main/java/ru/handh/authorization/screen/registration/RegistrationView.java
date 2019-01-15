package ru.handh.authorization.screen.registration;

public interface RegistrationView {
    int EMPTY_EMAIL = 0;
    int UNACCEPTABLE_EMAIL = 1;
    int EMPTY_PASS = 2;
    int UNACCEPTABLE_PASS = 3;

    void showError(int type);

    void close();

    void closeWithResult();
}
