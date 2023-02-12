package com.leontyev_devoloping.testapp;

import static com.leontyev_devoloping.testapp.Functions.GetUrl;
import static com.leontyev_devoloping.testapp.Functions.GoToTargetScreen;
import static com.leontyev_devoloping.testapp.Functions.checkIsEmu;
import static com.leontyev_devoloping.testapp.Variables.CONFIG;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        //Проверяем наличие файла коныигурации если его нет будет создан файл SharedPreferences с именем Config
        CONFIG = getSharedPreferences("Config", MODE_PRIVATE);
        GetUrl(this);
        GoToTargetScreen(this, Web_Screen.class);

/*        //Задаем отложенное выполнение задачи
        new Handler().postDelayed(() -> {
            //Проверяем содержи ли файл данные
            //Если нет, запрашиваем url
            if(CONFIG.getString("url", "").isEmpty()) {
                //Вызываем метод получения url
                GetUrl(this);
                //Проверяем на пустую строку, эмулятор, (добавить проверку интеренета)
                if(CONFIG.getString("url", "").isEmpty() || checkIsEmu() || ) {
                    //Переходим на экран с заглушкой
                    GoToTargetScreen(this, Game_Screen.class);
                } else {
                    //Переходим на Web_Screen и открываем указанный адрес
                    GoToTargetScreen(this, Web_Screen.class);
                }
            }
            //Если да, переходим на Web_Screen и открываем указанный адрес
            else {
                GoToTargetScreen(this, Web_Screen.class);
            }
        }, 1000);*/
    }
}