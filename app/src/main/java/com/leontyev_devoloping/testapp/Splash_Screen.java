package com.leontyev_devoloping.testapp;

import static com.leontyev_devoloping.testapp.Functions.GetUrl;
import static com.leontyev_devoloping.testapp.Functions.GoToTargetScreen;
import static com.leontyev_devoloping.testapp.Functions.isEmulator;
import static com.leontyev_devoloping.testapp.Functions.isOnline;
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
        //Задаем отложенное выполнение задачи
        new Handler().postDelayed(() -> {
            //Проверяем есть ли интернет
            if(!isOnline(this)) {
                //Если нет выводим окно уведомления
                GoToTargetScreen(this, "Для продолжения необходимо подключение к сети");
                //Если есть
            } else {
                //Вызываем метод получения url
                GetUrl(this);
                //Если строка не содержит адреса и устройство не эмулятор переходим в браузер и открываем указанный адрес
                if (CONFIG.getString("url", "").isEmpty() || isEmulator())
                    GoToTargetScreen(this, Game_Screen.class);
                    //Иначе переходим на экран заглушки
                else {
                    GoToTargetScreen(this, Web_Screen.class);
                }
            }
        }, 2000);
    }
}