package com.leontyev_devoloping.testapp;

import static com.leontyev_devoloping.testapp.Functions.GetUrl;
import static com.leontyev_devoloping.testapp.Functions.GoToTargetScreen;
import static com.leontyev_devoloping.testapp.Functions.ShowErrorWindow;
import static com.leontyev_devoloping.testapp.Functions.isEmulator;
import static com.leontyev_devoloping.testapp.Functions.isOffline;
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


        //Использую для проверки браузера
        //Подлежат удалению основной код ниже
        GetUrl(this);
        GoToTargetScreen(this, Web_Screen.class);



/*        //Задаем отложенное выполнение задачи
        new Handler().postDelayed(() -> {
            //Проверяем содержи ли файл данные
            //Если нет, запрашиваем url
            if(CONFIG.getString("url", "").isEmpty()) {
                //Вызываем метод получения url
                GetUrl(this);
                //Повторно проверяем на пустую строку, эмулятор, (добавить проверку интеренета)
                if(CONFIG.getString("url", "").isEmpty() || isEmulator() || isOffline(this)) {
                    //Переходим на экран с заглушкой
                    if(isOffline(this)) ShowErrorWindow(this, "Для продолжения необходимо подключиться к сети");
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