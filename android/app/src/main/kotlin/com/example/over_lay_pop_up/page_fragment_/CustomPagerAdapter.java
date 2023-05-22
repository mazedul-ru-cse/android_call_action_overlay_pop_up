package com.example.over_lay_pop_up.page_fragment_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.example.over_lay_pop_up.utils_.CallStatus;
import com.example.over_lay_pop_up.utils_.Note;
import com.example.over_lay_pop_up.utils_.Reminder;

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;

    public CustomPagerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        FragmentPages modelObject = FragmentPages.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(modelObject.getLayoutResId(), collection, false);

        switch (position){

            case 0:
                CallStatus callStatus = new CallStatus(mContext,layout);
                callStatus.onCreate();
                break;

            case 1:
                Note note = new Note(mContext,layout);
                note.onCreate();
                break;

            case 2:

                Reminder reminder = new Reminder(mContext,layout);
                reminder.onCreate();
                break;
        }

        collection.addView(layout);
        return layout;
    }


    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return FragmentPages.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
