package skot92.hu.unideb.hu.kiadaskezelo.ui.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import skot92.hu.unideb.hu.kiadaskezelo.R;

/**
 * Created by skot9 on 2015. 11. 13..
 */
public class MainDiagramsFragment extends FragmentActivity {

    private String tabtitles[] = new String[] { "Tab1", "Tab2", "Tab3" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.notifyDataSetChanged();
        pager.setAdapter(myPagerAdapter);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0: return FirstFragment.newInstance("FirstFragment, Instance 1");
                case 1: return SecondFragment.newInstance("SecondFragment, Instance 1");
                default: return FirstFragment.newInstance("FirstFragment, Default");
            }
        }

        @Override
        public int getCount() {
            return  2;
            //return getCount();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabtitles[position];
        }


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
