package com.example.over_lay_pop_up;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.viewpager.widget.PagerAdapter;

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;

    public CustomPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ModelObject modelObject = ModelObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(modelObject.getLayoutResId(), collection, false);

        switch (position){

            case 0:
                followUpHandler(layout);
                break;
        }

        collection.addView(layout);
        return layout;
    }

    private void followUpHandler(ViewGroup layout) {

        Spinner callStatusSpinner,assignToSpinner,followCategorySpinner;

        //Call status
        callStatusSpinner = layout.findViewById(R.id.callStatusSpinner);
        assignToSpinner = layout.findViewById(R.id.assignToSpinner);
        followCategorySpinner = layout.findViewById(R.id.followCategorySpinner);

        //Set call status spinner data
        ArrayAdapter<CharSequence> callStatusSpinnerAdapter = ArrayAdapter.createFromResource(mContext, R.array.callStatus, R.layout.spinner_item);
        callStatusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        callStatusSpinner.setAdapter(callStatusSpinnerAdapter);

        //Set assignTo spinner data
        ArrayAdapter<CharSequence> assignToSpinnerAdapter = ArrayAdapter.createFromResource(mContext, R.array.assignTo, R.layout.spinner_item);
        assignToSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        assignToSpinner.setAdapter(assignToSpinnerAdapter);


        //Set follow category spinner data
        ArrayAdapter<CharSequence> followCategorySpinnerAdapter = ArrayAdapter.createFromResource(mContext, R.array.followUpCategory, R.layout.spinner_item);
        followCategorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        followCategorySpinner.setAdapter(followCategorySpinnerAdapter);



    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return ModelObject.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
