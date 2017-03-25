package com.example.bakhtiyar.simplechatapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Bakhtiyar on 12/19/2016.
 */
public class Fragment extends FragmentPagerAdapter {
    public Fragment(FragmentManager fm) {
        super(fm);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "You Friends Chat List :)";
            case 1:
                return "Your Image";
            default:
                return null;

        }


    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch (position){

            case 0:
                return new SendMessage();

            case 1:

                return new ReadFragment();

            default:
                return null;





        }




    }

    @Override
    public int getCount() {
        return 2;
    }
}
