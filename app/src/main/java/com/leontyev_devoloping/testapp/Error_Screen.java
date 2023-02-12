package com.leontyev_devoloping.testapp;

import static com.leontyev_devoloping.testapp.Functions.GoToTargetScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class Error_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_screen);
        //Устанавливаем связь с объектом поля TextView
        TextView textView = findViewById(R.id.textView);
        //Отображаем сообщение
        textView.setText(getIntent().getStringExtra("Message"));
        //Отображаем окно с ошибкой и перходим на окно с заглушкой
        new Handler().postDelayed(() -> GoToTargetScreen(this,Game_Screen.class), 3500);
    }
}