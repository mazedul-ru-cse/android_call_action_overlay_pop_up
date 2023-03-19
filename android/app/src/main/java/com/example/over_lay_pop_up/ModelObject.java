package com.example.over_lay_pop_up;

public enum ModelObject {

    FollowUp( R.layout.fragment_follow_up),
    Note(R.layout.fragment_notes),
    Reminder(R.layout.fragment_reminder),
    Task(R.layout.fragment_task),
    SMS(R.layout.fragment_s_m_s);

    private int mLayoutResId;

    ModelObject( int layoutResId) {
        mLayoutResId = layoutResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }


}