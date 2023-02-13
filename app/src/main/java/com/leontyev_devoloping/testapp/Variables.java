package com.leontyev_devoloping.testapp;

import android.content.SharedPreferences;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class Variables {

    //Объявляем переменную типа SharedPreferences для хранения url
    public static SharedPreferences CONFIG;
    //Объявляем переменную типа FirebaseRemoteConfig для обращения к удаленной конфигурации
    public static FirebaseRemoteConfig REMOTE_CONFIG;
    //Объявляем переменную типа SharedPreferences.Editor для вызова редактора конфигурационного файла
    public static SharedPreferences.Editor CONFIG_EDITOR;
    //Объявляем флаг статуса сети
    public static String NETWORK_STATUS;
}
