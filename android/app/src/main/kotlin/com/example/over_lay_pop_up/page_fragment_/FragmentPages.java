package com.example.over_lay_pop_up.page_fragment_;

import com.example.over_lay_pop_up.R;

public enum FragmentPages {

    FollowUp( R.layout.fragment_call_status),
    Note(R.layout.fragment_notes),
    Reminder(R.layout.fragment_reminder);
//    Task(R.layout.fragment_task),
//    SMS(R.layout.fragment_s_m_s);

    private int mLayoutResId;

    FragmentPages(int layoutResId) {
        mLayoutResId = layoutResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }


}