package ru.handh.authorization.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.handh.authorization.R;
import ru.handh.authorization.screen.auth.AuthActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //noinspection ConstantConditions
        getSupportActionBar().setTitle(R.string.main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnAuth)
    public void onClick(View view) {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }
}
