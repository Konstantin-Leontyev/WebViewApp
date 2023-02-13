package com.leontyev_devoloping.testapp;

import static com.leontyev_devoloping.testapp.Variables.CONFIG;
import static android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
public class Web_Screen extends AppCompatActivity {
    WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_screen);
        webView = findViewById(R.id.webView);
        //Создаем браузер внутри приложения
        webView.setWebViewClient(new WebViewClient());
        //Задаем настройки браузера
        WebSettings webSettings = webView.getSettings();
        //Влючаем поддержку JavaScript
        webSettings.setJavaScriptEnabled(true);
        //Разрешаем автоматическое открывание окон
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //Включаем поддержку полноэкранного режима
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        //Отключаем зум
        webSettings.setSupportZoom(false);
        //Разрешаем обращение к файлам
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        //Проверка на старые версии (в решениеие проблемы не помогла)
        webSettings.setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW);
        //Разрешаем использование cookie
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        //Открываем старницу
        String url = CONFIG.getString("url", " ");
        webView.loadUrl(url);
    }
    //Метод обработки нажатия кнопки назад
    public void onBackPressed(){
        if(webView.canGoBack()) webView.goBack();
    }
}