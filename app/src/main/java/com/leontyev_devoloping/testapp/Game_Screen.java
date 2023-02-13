package com.leontyev_devoloping.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Game_Screen extends AppCompatActivity {
    //Объявляем поля разметки
    private TextView result, winner;
    private Button red, black, zero;
    private ImageView roulette;
    private Random random;
    //Переменная для хранения угла смещения
    private int degrees = 0;
    //Переменна хранящяя размер поля рулетки
    private static final float FACTOR = 4.86f;
    //Переменная для хранения выбора игрока
    private String choice = "";
    //Масив хранящий занчения полей рулекти
    private final String[] numbers = {"32 КРАСНОЕ","15 ЧЁРНОЕ","19 КРАСНОЕ","4 ЧЁРНОЕ",
            "21 КРАСНОЕ","2 ЧЁРНОЕ","25 КРАСНОЕ","17 ЧЁРНОЕ", "34 КРАСНОЕ",
            "6 ЧЁРНОЕ","27 КРАСНОЕ","13 ЧЁРНОЕ","36 КРАСНОЕ","11 ЧЁРНОЕ","30 КРАСНОЕ",
            "8 ЧЁРНОЕ","23 КРАСНОЕ","10 ЧЁРНОЕ","5 КРАСНОЕ","24 ЧЁРНОЕ","16 КРАСНОЕ","33 ЧЁРНОЕ",
            "1 КРАСНОЕ","20 ЧЁРНОЕ","14 КРАСНОЕ","31 ЧЁРНОЕ","9 КРАСНОЕ","22 ЧЁРНОЕ","18 КРАСНОЕ",
            "29 ЧЁРНОЕ","7 КРАСНОЕ","28 ЧЁРНОЕ","12 КРАСНОЕ","35 ЧЁРНОЕ","3 КРАСНОЕ","26 ЧЁРНОЕ","0 ЗЕРО"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        //Устанавливаем связь с полями разметки
        result = findViewById(R.id.tvResult);
        winner = findViewById(R.id.winner);
        red = findViewById(R.id.red);
        black = findViewById(R.id.black);
        zero = findViewById(R.id.zero);
        roulette = findViewById(R.id.rul);
        random = new Random();
    }
    //Устанавливаем слушатель на красное
    public void setRed(View view){
        choice = "КРАСНОЕ";
        rotateRoulette();
    }
    //Устанавливаем слушатель на черное
    public void setBlack(View view){
        choice = "ЧЁРНОЕ";
        rotateRoulette();
    }
    //Устанавливаем слушатель на зеро
    public void setZero(View view){
        choice = "ЗЕРО";
        rotateRoulette();
    }
    //Зовите Якубовича, мы вращяем барабан
    public void rotateRoulette()
    {
        //Скрываем кнопки
        red.setVisibility(View.INVISIBLE);
        black.setVisibility(View.INVISIBLE);
        zero.setVisibility(View.INVISIBLE);
        int old_degree = degrees % 360;
        degrees = random.nextInt(3600) + 720;
        RotateAnimation rotate = new RotateAnimation(old_degree, degrees,RotateAnimation.RELATIVE_TO_SELF,
                0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(3600);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                result.setText("");
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                result.setText(getResult(360 - (degrees % 360)));
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        //Запускаем анимацию
        roulette.startAnimation(rotate);
    }
    //Метод определения победителя
    private String getResult(int degrees)
    {
        String text = "";
        int factor_x = 1;
        int factor_y = 3;
        for(int i = 0;i < 37; i++){
            if(degrees >= (FACTOR * factor_x) && degrees < (FACTOR * factor_y))
            {
                text = numbers[i];
            }
            factor_x += 2;
            factor_y += 2;
        }
        if(degrees >= (FACTOR * 73) && degrees < 360 || degrees >= 0 && degrees < (FACTOR * 1)) text = numbers[numbers.length - 1];
        if(text.contains(choice)) winner.setText("Победа игрока!");
        else winner.setText("Победа казино!");
        newGame();
        return text;
    }
    //Метод подготовки к новой игре
    private void newGame (){
        new Handler().postDelayed(() -> {
            red.setVisibility(View.VISIBLE);
            black.setVisibility(View.VISIBLE);
            zero.setVisibility(View.VISIBLE);
            winner.setText("");
            result.setText(R.string.bet);
        }, 1000);
    }
}
