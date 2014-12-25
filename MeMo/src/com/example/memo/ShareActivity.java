package com.example.memo;

import com.example.memo.actionbar.MainActionbar;
import com.example.memo.entity.SerEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class ShareActivity extends Activity{
	private TextView mTIitle;
	private TextView mData;
	private TextView mMatter;
	private SerEntity set;
	private Handler h = new Handler(){
		public void handleMessage(Message msg) {
			SerEntity h = (SerEntity) msg.obj;
			mTIitle.setText(h.getName());
			mData.setText(h.getDate());
			mMatter.setText(h.getVlue());
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_info_layout);
		InitWindowTitle();

		Intent intent = getIntent();
		set = intent.getParcelableExtra("set");
		System.out.println("///////////"+set.toString());
		InitView();
		InItThread(set);
	}

	private void InitWindowTitle(){
		MainActionbar bar = new MainActionbar(ShareActivity.this);
		View actionBar = bar.setActionBar(R.layout.share_info_title);
		TextView txt = (TextView) actionBar.findViewById(R.id.share_info_title_black);
		txt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					finalize();
					finish();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	
	}

	private void InitView(){

		mTIitle = (TextView) findViewById(R.id.share_info_layout_title);
		mData = (TextView) findViewById(R.id.share_info_layout_date);
		mMatter = (TextView) findViewById(R.id.share_info_layout_matter);
	}
	private void InItThread(final SerEntity st){
		new Thread(new Runnable() {
			@Override
			public void run() {
				Message msg = h.obtainMessage(1, st);
				h.sendMessage(msg);
			}
		}).start();
	}
}
