package ru.handh.authorization.screen.auth;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.handh.authorization.R;
import ru.handh.authorization.screen.registration.RegistrationActivity;
import ru.handh.authorization.screen.reset_pass.ResetPassActivity;

public class AuthActivity extends AppCompatActivity implements AuthView {

    private static final int RC_REGISTRATION = 1000;
    private static final int RC_RESET = 1001;

    @BindView(R.id.coordinator)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.toolbarShadow)
    View mToolbarShadow;

    @BindView(R.id.layoutInputEmail)
    TextInputLayout mLayoutInputEmail;

    @BindView(R.id.layoutInputPass)
    TextInputLayout mLayoutInputPass;

    @BindView(R.id.etEmail)
    EditText mEtEmail;

    @BindView(R.id.etPass)
    EditText mEtPass;

    @BindView(R.id.btnLogIn)
    Button mBtnLogIn;

    private AuthPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mPresenter = new AuthPresenter(this);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarShadow.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mPresenter.backClick();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openRegistrationScreen() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivityForResult(intent, RC_REGISTRATION);
    }

    @Override
    public void openResetPassScreen() {
        Intent intent = new Intent(this, ResetPassActivity.class);
        startActivityForResult(intent, RC_RESET);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RC_REGISTRATION:
                    mPresenter.registrationCloseWithResult();
                    break;
                case RC_RESET:
                    mPresenter.resetPassCloseWithResult();
                    break;
            }
        }
    }

    @Override
    public void showError(int type) {
        switch (type) {
            case EMPTY_EMAIL:
                mLayoutInputEmail.setError(getResources().getString(R.string.auth_empty_email));
                break;
            case UNACCEPTABLE_EMAIL:
                mLayoutInputEmail.setError(getResources().getString(R.string.auth_unacceptable_email));
                break;
            case EMPTY_PASS:
                mLayoutInputPass.setError(getResources().getString(R.string.auth_empty_pass));
                break;
            case UNACCEPTABLE_PASS:
                mLayoutInputPass.setError(getResources().getString(R.string.auth_unacceptable_pass));
                break;
            case ERROR_CONNECTION:
                Snackbar.make(mCoordinatorLayout, R.string.auth_connection_error, Snackbar.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void showWeather(String weather) {
        Snackbar.make(mCoordinatorLayout, String.format(getString(R.string.auth_weather), weather), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showMessageRegistrationSuccess() {
        Snackbar.make(mCoordinatorLayout, R.string.auth_registration_success, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showMessageResetPassSuccess() {
        Snackbar.make(mCoordinatorLayout, R.string.auth_reset_pass_success, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void close() {
        onBackPressed();
    }


    @OnClick({R.id.btnLogIn, R.id.tvMake, R.id.ibForgotPass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogIn: {
                clearLayoutInputError();
                mPresenter.clickAuth(mEtEmail.getText().toString(), mEtPass.getText().toString());
            }
            break;
            case R.id.tvMake: {
                mPresenter.makeClick();
            }
            break;
            case R.id.ibForgotPass: {
                mPresenter.clickForgotPass();
            }
            break;
        }
    }

    private void clearLayoutInputError() {
        mLayoutInputEmail.setError(null);
        mLayoutInputPass.setError(null);
    }


}
