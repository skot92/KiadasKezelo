package skot92.hu.unideb.hu.kiadaskezelo.ui.fragments.charts.in;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import skot92.hu.unideb.hu.kiadaskezelo.R;

/**
 * Created by skot9 on 2015. 11. 13..
 */
public class InComeDiagrams extends FragmentActivity {

    private String tabtitles[] = new String[] { "Dátum szerint", "Név szerint"};

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
                case 0: return GroupByDateDiagram.newInstance("GroupByDateDiagram, Instance 1");
                case 1: return GroupByNameDiagram.newInstance("GroupByNameDiagram, Instance 2");
                default: return GroupByNameDiagram.newInstance("GroupByDateDiagram, Default");
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
