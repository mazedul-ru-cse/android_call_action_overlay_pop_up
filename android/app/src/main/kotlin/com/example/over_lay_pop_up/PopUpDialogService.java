package com.example.over_lay_pop_up;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class PopUpDialogService extends Service {
    View viewRoot;
    WindowManager windowManager;
    WindowManager.LayoutParams rootParams;

    ViewPager viewPager;

    //Pop up close button
    ImageButton closeBtn;
    int width;

    //CRM button
    CardView followUpBtn,noteBtn,reminderBtn,taskBtn,smsBtn;

    //slide indicators
    View followUpIndicator, noteIndicator,reminderIndicator,taskIndicator,smsIndicator;

    public PopUpDialogService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return START_NOT_STICKY;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        width = metrics.widthPixels;


        if (rootParams == null) {
            int LAYOUT_FLAG;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            } else {
                LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
            }
            rootParams = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    LAYOUT_FLAG,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    PixelFormat.TRANSLUCENT);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("com.example.over_lay_pop_up", "Floating Layout Service", NotificationManager.IMPORTANCE_LOW);
                channel.setLightColor(Color.BLUE);
                channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                assert notificationManager != null;
                notificationManager.createNotificationChannel(channel);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "com.example.over_lay_pop_up");
                Notification notification = builder.setOngoing(true)
                        //.setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Floating Layout Service is Running")
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .setCategory(Notification.CATEGORY_SERVICE)
                        .build();
                startForeground(2, notification);
            }

            if (viewRoot == null) {
                viewRoot = LayoutInflater.from(this).inflate(R.layout.pop_up_dialog_service_layout, null);
                rootParams.gravity = Gravity.CENTER_HORIZONTAL |  Gravity.START;
                rootParams.x = 0;
                rootParams.y = 0;

                windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
                windowManager.addView(viewRoot, rootParams);

                //Initialize the id
                closeBtn = viewRoot.findViewById(R.id.closeDialogBtn);
                viewPager = viewRoot.findViewById(R.id.viewPager);
                followUpBtn = viewRoot.findViewById(R.id.navFollowUpBtn);
                noteBtn = viewRoot.findViewById(R.id.navNoteBtn);
                reminderBtn = viewRoot.findViewById(R.id.navReminderBtn);
                taskBtn = viewRoot.findViewById(R.id.navTaskBtn);
                smsBtn = viewRoot.findViewById(R.id.navSmsBtn);

                //initialize slide indicator
                followUpIndicator = viewRoot.findViewById(R.id.followUpIndicator);
                noteIndicator = viewRoot.findViewById(R.id.noteIndicator);
                reminderIndicator = viewRoot.findViewById(R.id.reminderIndicator);
                taskIndicator = viewRoot.findViewById(R.id.taskIndicator);
                smsIndicator = viewRoot.findViewById(R.id.smsIndicator);

                // Page view adapter
                viewPager.setAdapter(new CustomPagerAdapter(this));
                viewPager.setScrollContainer(false);

                //Action button
                followUpBtn.setOnClickListener(view -> viewPager.setCurrentItem(0));
                noteBtn.setOnClickListener(view -> viewPager.setCurrentItem(1));
                reminderBtn.setOnClickListener(view -> viewPager.setCurrentItem(2));
                taskBtn.setOnClickListener(view -> viewPager.setCurrentItem(3));
                smsBtn.setOnClickListener(view -> viewPager.setCurrentItem(4));


                //set slide indicator
                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                        setIndicator(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });


                viewRoot.findViewById(R.id.root).setOnTouchListener(new View.OnTouchListener() {
                    private int initialX;
                    private int initialY;
                    private int initialTouchX;
                    private int initialTouchY;
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                initialX = rootParams.x;
                                initialY = rootParams.y;

                                initialTouchX = (int) motionEvent.getRawX();
                                initialTouchY = (int) motionEvent.getRawY();
                                return true;
                            case MotionEvent.ACTION_UP:
                                if (motionEvent.getRawX() < width / 2) {
                                    rootParams.x = 0;
                                } else {
                                    rootParams.x = width;
                                }
                                rootParams.y = initialY + (int) (motionEvent.getRawY() - initialTouchY);
                                windowManager.updateViewLayout(viewRoot, rootParams);

                                return true;
                            case MotionEvent.ACTION_MOVE:
                                rootParams.x = initialX + (int) (motionEvent.getRawX() - initialTouchX);
                                rootParams.y = initialY + (int) (motionEvent.getRawY() - initialTouchY);

                                windowManager.updateViewLayout(viewRoot, rootParams);
                                return true;

                            default:
                                Log.d("Movie",(motionEvent.getAction())+"");
                        }
                        return false;
                    }
                });

                closeBtn.setOnClickListener(view -> stopService());
            }
        }
    }

    private void stopService() {
        try {
            stopForeground(true);
            stopSelf();
            windowManager.removeViewImmediate(viewRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //set slide indicator
    private void setIndicator(int indicator) {
       switch (indicator){
           case 0:
               followUpIndicator.setVisibility(View.VISIBLE);
               noteIndicator.setVisibility(View.INVISIBLE);
               reminderIndicator.setVisibility(View.INVISIBLE);
               taskIndicator.setVisibility(View.INVISIBLE);
               smsIndicator.setVisibility(View.INVISIBLE);
               break;

           case 1:
               followUpIndicator.setVisibility(View.INVISIBLE);
               noteIndicator.setVisibility(View.VISIBLE);
               reminderIndicator.setVisibility(View.INVISIBLE);
               taskIndicator.setVisibility(View.INVISIBLE);
               smsIndicator.setVisibility(View.INVISIBLE);
               break;

           case 2:
               followUpIndicator.setVisibility(View.INVISIBLE);
               noteIndicator.setVisibility(View.INVISIBLE);
               reminderIndicator.setVisibility(View.VISIBLE);
               taskIndicator.setVisibility(View.INVISIBLE);
               smsIndicator.setVisibility(View.INVISIBLE);
               break;

           case 3:
               followUpIndicator.setVisibility(View.INVISIBLE);
               noteIndicator.setVisibility(View.INVISIBLE);
               reminderIndicator.setVisibility(View.INVISIBLE);
               taskIndicator.setVisibility(View.VISIBLE);
               smsIndicator.setVisibility(View.INVISIBLE);
               break;

           case 4:
               followUpIndicator.setVisibility(View.INVISIBLE);
               noteIndicator.setVisibility(View.INVISIBLE);
               reminderIndicator.setVisibility(View.INVISIBLE);
               taskIndicator.setVisibility(View.INVISIBLE);
               smsIndicator.setVisibility(View.VISIBLE);
               break;
       }
    }

}