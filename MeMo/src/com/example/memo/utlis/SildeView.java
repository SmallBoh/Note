package com.example.memo.utlis;



import com.example.memo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

public class SildeView extends LinearLayout{
	private Context mContext;
	private LinearLayout mViewContent;
	private Scroller mScroller;
	private OnSlideListener mOnSlideListener;
	private int mHolderWidth = 120;

	private int mLastX = 0;
	private int mlastY = 0;
	private static final int TAN = 2;


	public SildeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}

	public SildeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		initView();
	}


	private void initView(){
		mContext = getContext();
		mScroller = new Scroller(mContext);

		setOrientation(LinearLayout.HORIZONTAL);//布局水平
		View.inflate(mContext, R.layout.silde_view_merge, this);
		mViewContent = (LinearLayout) findViewById(R.id.view_content);
		mHolderWidth = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
				mHolderWidth, getResources().getDisplayMetrics()));
	}

	public void setContextView(View view){
		mViewContent.addView(view);
	}
	public void setButtonText(CharSequence text){
		TextView tx =(TextView) findViewById(R.id.delete);
		tx.setText(text);
	}
	public void shrink(){
		if(getScrollX() != 0){
			this.smoothScrollTo(0, 0);
		}
	}
	public void OnRequireTouchEvent(MotionEvent event){

		int x = (int) event.getX();
		int y = (int) event.getY();
		int screollX = getScrollX();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (!mScroller.isFinished()) {
				mScroller.abortAnimation();
			}
			if (mOnSlideListener != null) {
				mOnSlideListener.onSlide(this,OnSlideListener.SLIDE_STATUS_START_SCROLL);
			}
			break;

		case MotionEvent.ACTION_MOVE:
			int deltaX = x - mLastX;
			int deltaY = y - mlastY;
			if (Math.abs(deltaX) < Math.abs(deltaY) * TAN) {
				break;
			}

			int newScrollX = screollX - deltaX;
			if (deltaX != 0) {
				if (newScrollX < 0) {
					newScrollX = 0;
				} else if (newScrollX > mHolderWidth) {
					newScrollX = mHolderWidth;
				}
				this.scrollTo(newScrollX, 0);
			}
			break;
		case MotionEvent.ACTION_UP:

			break;
		}
		mLastX = x;
		mlastY = y;
	}



	private void smoothScrollTo(int destX,int destY){
		// 缓慢滚动到指定位置
		int scrollX = getScrollX();
		int delta = destX - scrollX;
		mScroller.startScroll(scrollX, 0, delta, 0,Math.abs(delta)*3);
		invalidate();
	}


	public void setOnSlideListener(OnSlideListener onSlideListener) {
		mOnSlideListener = onSlideListener;
	}

	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		super.computeScroll();
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
	}
}
