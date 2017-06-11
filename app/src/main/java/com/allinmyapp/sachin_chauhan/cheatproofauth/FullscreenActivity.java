package com.allinmyapp.sachin_chauhan.cheatproofauth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class FullscreenActivity extends Activity {

    public static int SCREENHEIGHT =0;
    private AbsoluteLayout absoluteLayout;
    private Map<Integer,Point> map;
    private Context context;
    private static int attemptcounter = 0;

    ArrayList<Point> points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        map = new HashMap<>();
        setContentView(R.layout.activity_fullscreen);
        context = getApplicationContext();

        absoluteLayout = (AbsoluteLayout)findViewById(R.id.fullscreen_content);
        absoluteLayout.setOnTouchListener(onTouchListener);

        createPasswordView(getApplicationContext());
    }

    private void createPasswordView(Context context){

        ArrayList<Integer> drawables;
        points = new ArrayList<>();
        drawables = new ArrayList<>();
        drawables.add(R.drawable.pic_1);
        drawables.add(R.drawable.pic_2);
        drawables.add(R.drawable.pic_3);
        drawables.add(R.drawable.pic_4);
        drawables.add(R.drawable.pic_5);
        drawables.add(R.drawable.pic_6);
        drawables.add(R.drawable.pic_7);
        drawables.add(R.drawable.pic_8);
        drawables.add(R.drawable.pic_9);
        drawables.add(R.drawable.pic_10);
        drawables.add(R.drawable.pic_12);
        drawables.add(R.drawable.pic_13);
        drawables.add(R.drawable.pic_14);
        drawables.add(R.drawable.pic_15);

        map.clear();
        map.put(R.drawable.pic_1,null);
        map.put(R.drawable.pic_2,null);
        map.put(R.drawable.pic_3,null);

        absoluteLayout.removeAllViews();
        Random rnd = new Random();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        SCREENHEIGHT = height;
        for (int dw : drawables) {


            ImageView imgView = new ImageView(context);

            AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT, 0, 0);

            params.x = rnd.nextInt(width);
            params.y = rnd.nextInt(height);
            if(map.containsKey(dw)==true) {
                Point pt = new Point();
                pt.set(params.x, SCREENHEIGHT - params.y);
                map.put(dw, pt);
                points.add(pt);
            }
            imgView.setVisibility(View.VISIBLE);
            absoluteLayout.addView(imgView, params);
            imgView.setImageBitmap(BitmapFactory.decodeResource(getResources(),
                    dw));
        }
        for (Point pt : points)
            Log.i("Fullscreen", "createPasswordView  "+ pt.toString());

    }
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            float x,y;
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                x= motionEvent.getRawX();
                y = motionEvent.getRawY();
                Log.i("FullScreen", "onTouch x =" + x);
                Log.i("FullScreenk", "onTouch y =" + y);
                manageAttempts(points,new Point((int)x,SCREENHEIGHT - (int)y));

            }
            return true;
        }
    };

    final void manageAttempts(ArrayList<Point> points, Point touchpoint) {

        if (new ConvexHull().isWithinHull(points, touchpoint) == true) {
            Toast.makeText(context, "Access Granted", Toast.LENGTH_SHORT).show();
            attemptcounter++;
            if (attemptcounter == 1) {
                Intent intent = new Intent(context, SettingsActivity.class);
                startActivity(intent);
                finish();
            }

        } else {
            Toast.makeText(context, "Access Denied", Toast.LENGTH_SHORT).show();
            attemptcounter = 0;
        }
        createPasswordView(getApplicationContext());
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        final int keycode = event.getKeyCode();
        final int action = event.getAction();
        if ((keycode == KeyEvent.KEYCODE_MENU || keycode == KeyEvent.KEYCODE_HOME) && (action == KeyEvent.ACTION_UP)) {
            return false; // consume the key press
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
    }
}
