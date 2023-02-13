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
 /*       new Handler().postDelayed(() -> {
            //На устройстве сохранена ссылка?
            if (CONFIG.getString("url", "").isEmpty()) {
                //Подключаемся к уадленному файлу
                GetUrl(this);
                //Если строка пуста, устройство эмулятор или нет интернета переходим в браузер и открываем указанный адрес
                if (CONFIG.getString("url", "").isEmpty() || isEmulator() || !isOnline(this)) {
                    //Переходим на экран заглушки
                    GoToTargetScreen(this, Game_Screen.class);
                } else {
                    //Иначе нет переходим в браузер и открываем указанный адрес
                    GoToTargetScreen(this, Web_Screen.class);
                }
                //Проверяем есть ли интернет
            } else if(isOnline(this)) {
                //Если да переходим в браузер и открываем указанный адрес
                GoToTargetScreen(this, Web_Screen.class);
                } else {
                    //Если нет выводим окно уведомления
                    GoToTargetScreen(this, "Для продолжения необходимо подключение к сети");
                }
        }, 2000);*/

        //Задаем отложенное выполнение задачи
        new Handler().postDelayed(() -> {
            //Проверяем есть ли интернет
            if(!isOnline(this)) {
                //Если нет выводим окно уведомления
                GoToTargetScreen(this, "Для продолжения необходимо подключение к сети");
            } else {
                //Иначе вызываем метод получения url
                GetUrl(this);
                //Если строка не содержит адреса или эмулятор
                if (CONFIG.getString("url", "").isEmpty() || isEmulator()) {
                    //Переходим на экран с заглушкой
                    GoToTargetScreen(this, Game_Screen.class);
                } else {
                    //Если содержит адрес переходим на Web_Screen и открываем указанный адрес
                    GoToTargetScreen(this, Web_Screen.class);
                }
            }
        }, 2000);
    }
}