package com.example.mobileproject;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ThRoulette extends AppCompatActivity {
    NyomDB helper;
    SQLiteDatabase db;
    TextView tv;
    Cursor cursor;
    ArrayList <String> menu_list;
    String []list;


    private CircleManager circleManager;
    private RelativeLayout layoutRoulette;

    private Button btnDrawRoulette5;
    private Button btnDrawRoulette6;
    private Button btnRotate;
    private TextView tvResult;

    private ArrayList<String> STRINGS;
    private float initAngle = 0.0f;
    private int num_roulette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roullete);


        helper = new NyomDB( this, "mydb" );
        db = helper.getWritableDatabase();
        helper.addCreate(db);

        btnRotate = findViewById(R.id.btnRotate);   //spin
        layoutRoulette = findViewById(R.id.layoutRoulette);

        num_roulette = 6;
        STRINGS = setRandom(num_roulette);
        circleManager = new CircleManager(ThRoulette.this, num_roulette);
        layoutRoulette.addView(circleManager);

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {          //스핀 이벤트
                rotateLayout(layoutRoulette, num_roulette);
            }
        });
    }


    public void rotateLayout(final RelativeLayout layout, final int num) {
        final float fromAngle = getRandom(360) + 3600 + initAngle;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getResult(fromAngle, num); // start when animation complete
            }
        }, 3000);

        RotateAnimation rotateAnimation = new RotateAnimation(initAngle, fromAngle,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.anim.accelerate_decelerate_interpolator));
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillEnabled(true);
        rotateAnimation.setFillAfter(true);
        layout.startAnimation(rotateAnimation);
    }

    // Set numbers on roulette to random
    public ArrayList<String> setRandom(int num_roulette) {            //원판안에 숫자를 대입하는 메서드
        ArrayList<String> strings = new ArrayList<>();

        cursor= db.rawQuery("SELECT * FROM menu", null);
        int clength = cursor.getCount();
        list = new String[clength];
        cursor.moveToFirst();
        Log.i("STR","???"+String.valueOf(clength));
        for(int i=0; i<clength; i++) {
            list[i] = cursor.getString(1);
            cursor.moveToNext();
        }

        menu_list = new ArrayList<>();
        menu_list.addAll(Arrays.asList(list));

        for (int i = 0; i < num_roulette; i++) {
            Collections.shuffle(menu_list);
            String rand = menu_list.get(i);
            strings.add(String.valueOf(rand));
        }

        return strings;
    }

    // get Angle to random
    private int getRandom(int maxNumber) {
        double r = Math.random();
        return (int)(r * maxNumber);
    }

    private void getResult(float angle, int num_roulette) {
        String text = "";
        angle = angle % 360;

        Log.d("roulette", "getResult : " + angle);

        if (num_roulette == 6) {
            if (angle > 330 || angle <= 30) { // 22
                text = STRINGS.get(4);
                buildAlert(text);
            } else if (angle > 30 && angle <= 90) { // 11
                text = STRINGS.get(3);
                buildAlert(text);
            } else if (angle > 90 && angle <= 150) { // 333
                text = STRINGS.get(2);
                buildAlert(text);
            } else if (angle > 150 && angle <= 210) { // 222
                text = STRINGS.get(1);
                buildAlert(text);
            } else if (angle > 210 && angle <= 270) { // 111
                text = STRINGS.get(0);
                buildAlert(text);
            } else if (angle > 270 && angle <= 330) { // 3
                text = STRINGS.get(5);
                buildAlert(text);
            }
        }

    }

    // if you want use AlertDialog then use this
    private void buildAlert(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("오늘의 음식")
                .setMessage("오늘의 추천은 " + text + " !!!!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        layoutRoulette.setRotation(360 - initAngle);
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public class CircleManager extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private int[] COLORS = {Color.RED, Color.YELLOW, Color.GREEN,Color.BLUE,Color.MAGENTA, Color.CYAN};
        private int num;

        public CircleManager(Context context, int num) {
            super(context);
            this.num = num;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int width = layoutRoulette.getWidth();
            int height = layoutRoulette.getHeight();
            int sweepAngle = 360 / num;

            RectF rectF = new RectF(0, 0, width, height);
            Rect rect = new Rect(0, 0, width, height);

            int centerX = (rect.left + rect.right) / 2;
            int centerY = (rect.top + rect.bottom) / 2;
            int radius = (rect.right - rect.left) / 2;

            int temp = 0;

            for (int i = 0; i < num; i++) {
                paint.setColor(COLORS[i]);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setAntiAlias(true);
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawArc(rectF, temp, sweepAngle, true, paint);

                float medianAngle = (temp + (sweepAngle / 2f)) * (float) Math.PI / 180f;

                paint.setColor(Color.BLACK);
                paint.setTextSize(45);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);

                float arcCenterX = (float) (centerX + (radius * Math.cos(medianAngle))); // Arc's center X
                float arcCenterY = (float) (centerY + (radius * Math.sin(medianAngle))); // Arc's center Y

                // put text at middle of Arc's center point and Circle's center point
                float textX = (centerX + arcCenterX) / 2;
                float textY = (centerY + arcCenterY) / 2;

                canvas.drawText(STRINGS.get(i), textX, textY, paint);
                temp += sweepAngle;
            }
        }
    }
}