package ru.handh.authorization.screen.registration;

import ru.handh.authorization.screen.auth.AuthView;
import ru.handh.authorization.screen.utils.TextUtils;

class RegistrationPresenter {

    private final RegistrationView mView;

    RegistrationPresenter(RegistrationView view) {
        mView = view;
    }

    void clickRegister(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            mView.showError(AuthView.EMPTY_EMAIL);
        } else if (!TextUtils.validateEmail(email)) {
            mView.showError(AuthView.UNACCEPTABLE_EMAIL);
        } else if (TextUtils.isEmpty(password)) {
            mView.showError(AuthView.EMPTY_PASS);
        } else if (!TextUtils.validatePass(password)) {
            mView.showError(AuthView.UNACCEPTABLE_PASS);
        } else {
            mView.closeWithResult();
        }
    }

    void backClick() {
        mView.close();
    }

}
