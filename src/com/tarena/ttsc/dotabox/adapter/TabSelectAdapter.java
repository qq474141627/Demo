package com.tarena.ttsc.dotabox.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

/**
 * µº∫Ω¿∏   ≈‰∆˜
 * 
 * @author Tarena
 */
public class TabSelectAdapter extends FragmentPagerAdapter implements
		OnPageChangeListener, OnTabChangeListener {

	private FragmentActivity mContext;
	private TabHost mTabHost;
	private ViewPager mViewPager;
	private RadioGroup mRadioGroup;

	private ArrayList<TabInfo> mTabInfos = new ArrayList<TabSelectAdapter.TabInfo>();

	public TabSelectAdapter(FragmentActivity mContext, TabHost mTabHost,
			ViewPager mViewPager, RadioGroup mRadioGroup) {
		super(mContext.getSupportFragmentManager());
		this.mContext = mContext;
		this.mTabHost = mTabHost;
		this.mViewPager = mViewPager;
		this.mRadioGroup = mRadioGroup;

		this.mTabHost.setOnTabChangedListener(this);
		this.mViewPager.setOnPageChangeListener(this);
		this.mViewPager.setAdapter(this);
	}

	/**
	 * ÃÌº”µº∫ΩœÓ
	 * 
	 * @param spec
	 * @param clss
	 * @param args
	 */
	public void addTab(TabHost.TabSpec spec, Class<?> clss, Bundle args) {

		spec.setContent(new DummyTabFactory(mContext));
		String tag = spec.getTag();

		TabInfo info = new TabInfo(tag, clss, args);
		mTabInfos.add(info);
		mTabHost.addTab(spec);

		notifyDataSetChanged();
	}


	final class TabInfo {
		private final String tag;
		private final Class<?> clss;
		private final Bundle args;

		public TabInfo(String tag, Class<?> clss, Bundle args) {
			this.tag = tag;
			this.clss = clss;
			this.args = args;
		}
	}

	class DummyTabFactory implements TabHost.TabContentFactory {
		private final Context mContext;

		public DummyTabFactory(Context mContext) {
			this.mContext = mContext;
		}

		@Override
		public View createTabContent(String tag) {

			View view = new View(mContext);
			view.setMinimumHeight(0);
			view.setMinimumWidth(0);
			return view;
		}

	}

	@Override
	public Fragment getItem(int arg0) {

		TabInfo tabInfo = mTabInfos.get(arg0);
		return Fragment.instantiate(mContext, tabInfo.clss.getName(),
				tabInfo.args);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTabInfos.size();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {

		for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
			RadioButton radioBtn = (RadioButton) mRadioGroup.getChildAt(i);
			if (i == arg0)
				radioBtn.setChecked(true);
			else
				radioBtn.setChecked(false);
		}

	}

	@Override
	public void onTabChanged(String arg0) {
		int currentTab = mTabHost.getCurrentTab();
		mViewPager.setCurrentItem(currentTab);
	}

}
