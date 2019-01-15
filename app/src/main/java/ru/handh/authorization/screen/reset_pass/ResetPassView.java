package ru.handh.authorization.screen.reset_pass;

public interface ResetPassView {
    int EMPTY_EMAIL = 0;
    int UNACCEPTABLE_EMAIL = 1;

    void showError(int type);

    void close();

    void closeWithResult();
}
