package com.example.memo.actionbar;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class MainActionbar {
	private Context context;
	public MainActionbar(Context context){
		this.context = context;
	}
	public View setActionBar(int layoutId){
		ActionBar actionbar = ((Activity) context).getActionBar();
		View v = null ;
		if(actionbar != null){
			actionbar.setDisplayShowHomeEnabled(false);
			actionbar.setDisplayShowCustomEnabled(true);
			LayoutInflater inflater = (LayoutInflater) ((Activity) context).getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			v = inflater.inflate(layoutId, null);

			ActionBar.LayoutParams layout = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			actionbar.setCustomView(v,layout);
		}
		return v;
	}

}
