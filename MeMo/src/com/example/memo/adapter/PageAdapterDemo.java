package com.example.memo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class PageAdapterDemo extends PagerAdapter{
	
	private List<View> list = new ArrayList<View>();
	
	public List<View> getList() {
		return list;
	}

	public void setList(List<View> list) {
		this.list = list;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	public Object instantiateItem(View container, int position) {
		((ViewPager)container).addView(list.get(position));


		return list.get(position);
	}
	public void destroyItem(View container, int position, Object object) {
		((ViewPager)container).removeView(list.get(position));
	}
}
