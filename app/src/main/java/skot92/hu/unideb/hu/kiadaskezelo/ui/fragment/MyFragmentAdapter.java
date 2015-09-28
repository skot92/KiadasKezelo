package skot92.hu.unideb.hu.kiadaskezelo.ui.fragment;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentAdapter extends FragmentPagerAdapter {

	public static final int NUM_ITEMS = 2;
	
	private ArrayList<Fragment> fragments;
	
	public MyFragmentAdapter(FragmentManager fm) {
		super(fm);
		
		fragments = new ArrayList<Fragment>();
		fragments.add(new FragmentNewExpenese());
		fragments.add(new FragmentAllExpense());
	}

	@Override
	public Fragment getItem(int pos) {
		return fragments.get(pos);
	}

	@Override
	public int getCount() {
		return NUM_ITEMS;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return FragmentNewExpenese.TAG;
		case 1:
			return FragmentAllExpense.TAG;
		default:
			return "unknown";
		}
	}
}
