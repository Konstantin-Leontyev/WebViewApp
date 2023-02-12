package com.leontyev_devoloping.testapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Game_Screen extends AppCompatActivity {
    private TextView tvResult;
    private ImageView rul;
    private Random random;
    private int degrees = 0;
    private static final float FACTOR = 4.86f;
    private final String[] numbers = {"32 RED","15 BLACK","19 RED","4 BLACK",
            "21 RED","2 BLACK","25 RED","17 BLACK", "34 RED",
            "6 BLACK","27 RED","13 BLACK","36 RED","11 BLACK","30 RED",
            "8 BLACK","23 RED","10 BLACK","5 RED","24 BLACK","16 RED","33 BLACK",
            "1 RED","20 BLACK","14 RED","31 BLACK","9 RED","22 BLACK","18 RED",
            "29 BLACK","7 RED","28 BLACK","12 RED","35 BLACK","3 RED","26 BLACK","0"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        init();
    }
    public void onClickStart(View view)
    {
        int old_deegre = degrees % 360;
        degrees = random.nextInt(3600) + 720;
        RotateAnimation rotate = new RotateAnimation(old_deegre, degrees,RotateAnimation.RELATIVE_TO_SELF,
                0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(3600);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tvResult.setText("");
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                tvResult.setText(getResult(360 - (degrees % 360)));
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        rul.startAnimation(rotate);
    }
    private void init()
    {
        tvResult = findViewById(R.id.tvResult);
        rul = findViewById(R.id.rul);
        random = new Random();

    }
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

        return text;
    }
}
