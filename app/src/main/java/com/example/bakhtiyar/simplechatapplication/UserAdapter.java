package com.example.bakhtiyar.simplechatapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 12/19/2016.
 */
public class UserAdapter extends BaseAdapter {

    TextView textView;

    ArrayList<UserInfo> userinfo;

    Context context;

    LayoutInflater inflater;

    public UserAdapter(ArrayList<UserInfo> userinfo, Context context) {
        this.userinfo = userinfo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userinfo.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.from(context).inflate(R.layout.user_info,viewGroup,false);

        textView = (TextView) view.findViewById(R.id.user);

        textView.setText(userinfo.get(i).getName());


        return view;
    }
}
