package skot92.hu.unideb.hu.kiadaskezelo.ui.activity;

import android.os.Bundle;


import android.support.v4.app.FragmentActivity;
import android.widget.Button;

import skot92.hu.unideb.hu.kiadaskezelo.ui.fragment.MyFragmentAdapter;
import skot92.hu.unideb.hu.kiadaskezelo.R;

import android.support.v4.view.ViewPager;


public class MainActivity extends FragmentActivity {

    private Button btnAllList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyFragmentAdapter adapter =
                new MyFragmentAdapter(getSupportFragmentManager());

        ViewPager p = (ViewPager) findViewById(R.id.pager);
        p.setAdapter(adapter);

        p.setCurrentItem(MyFragmentAdapter.NUM_ITEMS - 1);

    }


}
