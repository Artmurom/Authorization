package ru.handh.authorization.screen.reset_pass;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.handh.authorization.R;

public class ResetPassActivity extends AppCompatActivity implements ResetPassView {

    @BindView(R.id.layoutInputEmail)
    TextInputLayout mLayoutInputEmail;

    @BindView(R.id.etEmail)
    EditText mEtEmail;

    private ResetPassPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        mPresenter = new ResetPassPresenter(this);

        ButterKnife.bind(this);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
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
    public void showError(int type) {
        switch (type) {
            case EMPTY_EMAIL:
                mLayoutInputEmail.setError(getResources().getString(R.string.auth_empty_email));
                break;
            case UNACCEPTABLE_EMAIL:
                mLayoutInputEmail.setError(getResources().getString(R.string.auth_unacceptable_email));
                break;
        }
    }

    @Override
    public void close() {
        onBackPressed();
    }

    @Override
    public void closeWithResult() {
        setResult(RESULT_OK);
        onBackPressed();
    }

    @OnClick(R.id.btnSend)
    public void onClick(View view) {
        mLayoutInputEmail.setError(null);
        mPresenter.clickReset(mEtEmail.getText().toString());
    }
}
