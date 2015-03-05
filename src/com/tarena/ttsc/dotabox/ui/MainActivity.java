package com.tarena.ttsc.dotabox.ui;

import com.tarena.ttsc.dotabox.R;
import com.tarena.ttsc.dotabox.adapter.TabSelectAdapter;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener {

	private TabHost mTabHost;
	private ViewPager mViewPager;
	private RadioGroup mRadioGroup;
	private TabSelectAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViewWidget();

		addTab();

		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}

	}

	private void addTab() {
		
		mAdapter.addTab(mTabHost.newTabSpec("hero").setIndicator("Hero"),
				Fragment_Hero.class, null);
		mAdapter.addTab(mTabHost.newTabSpec("prop").setIndicator("Prop"),
				Fragment_Prop.class, null);
		mAdapter.addTab(mTabHost.newTabSpec("emul").setIndicator("Emul"),
				Fragment_Emul.class, null);
	}

	/**
	 * 初始化组建
	 */
	private void initViewWidget() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		mViewPager = (ViewPager) findViewById(R.id.main_viewPager);

		mRadioGroup = (RadioGroup) findViewById(R.id.main_radioGroup);
		mRadioGroup.setOnCheckedChangeListener(this);

		mAdapter = new TabSelectAdapter(this, mTabHost, mViewPager, mRadioGroup);
	}

	/**
	 * tab 选中事件
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.main_hero:
			mTabHost.setCurrentTabByTag("hero");
			break;
		case R.id.main_prop:
			mTabHost.setCurrentTabByTag("prop");

			break;
		case R.id.main_emulator:
			mTabHost.setCurrentTabByTag("emul");

			break;

		default:
			break;
		}
	}

}
