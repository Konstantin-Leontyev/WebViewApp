package com.leontyev_devoloping.testapp;

import static com.leontyev_devoloping.testapp.Variables.CONFIG;
import static com.leontyev_devoloping.testapp.Variables.CONFIG_EDITOR;
import static com.leontyev_devoloping.testapp.Variables.REMOTE_CONFIG;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.Locale;

public class Functions {

    //Реализация метода запроса url
    public static void GetUrl(Activity activity) {
        try {
            //Инициализируем объект удаленной конфигурации и задаем ему таймаут обновления
            REMOTE_CONFIG = FirebaseRemoteConfig.getInstance();
            //Инициализируем объект свойств удаленной коныигурации
            FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(500)
                    .build();
            //Присваиваем свойства объекту удаленной конфигурации
            REMOTE_CONFIG.setConfigSettingsAsync(configSettings);
            //Получаем данные конфигурации и активируем их
            REMOTE_CONFIG.fetchAndActivate()
                    .addOnCompleteListener(activity, task -> {
                        if (task.isSuccessful()) {
                            //Вызываем редактор
                            CONFIG_EDITOR = CONFIG.edit();
                            //Записыфаем url в файл
                            CONFIG_EDITOR.putString("url", REMOTE_CONFIG.getString("url"));
                            //Подтверждаем запись
                            CONFIG_EDITOR.apply();
                        }
                    });
            //Отлавливаем исключения которые может выбросить Firebase
        } catch ( IllegalStateException firebaseRemoteConfigException) {
            //Выводим всплывающее окно уведомления об ошибке
            System.out.println(firebaseRemoteConfigException.getMessage());
        }
    }

    //Универсальный метод перехода между активностями
    public static void GoToTargetScreen(Activity currentScreen, Class targetScreen) {
        //Переходим на целевую активность
        Intent intent = new Intent(currentScreen, targetScreen);
        //Запускаем целевую активность
        currentScreen.startActivity(intent);
        //Завершаем текущую активность
        currentScreen.finish();
    }

    public static Boolean checkIsEmu() {
        if (BuildConfig.DEBUG) return false; // when developer use this build on emulator
        String phoneModel = Build.MODEL;
        String buildProduct = Build.PRODUCT;
        String buildHardware = Build.HARDWARE;
        String brand = Build.BRAND;
        boolean result = (Build.FINGERPRINT.startsWith("generic")
                || phoneModel.contains("google_sdk")
                || phoneModel.toLowerCase(Locale.getDefault()).contains("droid4x") || phoneModel.contains("Emulator")
                || phoneModel.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || buildHardware.equals("goldfish")
                || brand.contains("google")
                || buildHardware.equals("vbox86")
                || buildProduct.equals("sdk")
                || buildProduct.equals("sdk_x86")
                || buildProduct.equals("vbox86p")
                || Build.BOARD.toLowerCase(Locale.getDefault()).contains("nox")
                || Build.BOOTLOADER.toLowerCase(Locale.getDefault()).contains("nox") || buildHardware.toLowerCase(Locale.getDefault()).contains("nox")
                || buildProduct.toLowerCase(Locale.getDefault()).contains("nox"));
        if (result) return true;
        result = brand.startsWith("generic") && Build.DEVICE.startsWith("generic");
        if (result) return true;
        result = "google_sdk".equals(buildProduct);
        return result;
    }
}




