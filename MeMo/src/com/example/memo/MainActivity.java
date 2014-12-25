package com.example.memo;

import com.example.memo.actionbar.MainActionbar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim);
		MainActionbar bar = new MainActionbar(this);
		bar.setActionBar(R.layout.add_main);
		
		ImageView img = (ImageView) findViewById(R.id.img_anim);
		Animation anim = new AlphaAnimation(1,0);
		anim.setDuration(5*1000);
		img.setAnimation(anim);
		
		anim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				Intent inte = new Intent(MainActivity.this, AddActivity.class);
				startActivity(inte);
				finish();
			}
		});
	}
}
