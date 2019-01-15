package ru.handh.authorization.screen.auth;


import android.annotation.SuppressLint;

import ru.handh.authorization.repository.RepositoryProvider;
import ru.handh.authorization.screen.utils.TextUtils;

class AuthPresenter {

    private final AuthView mView;

    AuthPresenter(AuthView view) {
        mView = view;
    }

    @SuppressLint("CheckResult")
    private void loadWeather() {
        RepositoryProvider.provideRepository()
                .getWeather()
                .subscribe(mView::showWeather, throwable -> mView.showError(AuthView.ERROR_CONNECTION));
    }

    void clickAuth(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            mView.showError(AuthView.EMPTY_EMAIL);
        } else if (!TextUtils.validateEmail(email)) {
            mView.showError(AuthView.UNACCEPTABLE_EMAIL);
        } else if (TextUtils.isEmpty(password)) {
            mView.showError(AuthView.EMPTY_PASS);
        } else if (!TextUtils.validatePass(password)) {
            mView.showError(AuthView.UNACCEPTABLE_PASS);
        } else {
            loadWeather();
        }
    }

    void clickForgotPass() {
        mView.openResetPassScreen();
    }

    void registrationCloseWithResult() {
        mView.showMessageRegistrationSuccess();
    }

    void resetPassCloseWithResult() {
        mView.showMessageResetPassSuccess();
    }

    void makeClick() {
        mView.openRegistrationScreen();
    }

    void backClick() {
        mView.close();
    }

}
