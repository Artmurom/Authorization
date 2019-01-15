package ru.handh.authorization.screen.reset_pass;

import ru.handh.authorization.screen.auth.AuthView;
import ru.handh.authorization.screen.utils.TextUtils;

class ResetPassPresenter {

    private final ResetPassView mView;

    ResetPassPresenter(ResetPassView view) {
        mView = view;
    }

    void clickReset(String email) {
        if (TextUtils.isEmpty(email)) {
            mView.showError(AuthView.EMPTY_EMAIL);
        } else if (!TextUtils.validateEmail(email)) {
            mView.showError(AuthView.UNACCEPTABLE_EMAIL);
        } else {
            mView.closeWithResult();
        }
    }

    void backClick() {
        mView.close();
    }

}
