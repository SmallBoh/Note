package com.example.memo.utlis;

import com.example.memo.entity.Note;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
/**
 * 自定义组件
 * @author 波波
 *
 */
public class ListViewCompat extends ListView{


	private SildeView mFocusedItemView;
	public ListViewCompat(Context context) {
		super(context);
	}

	public ListViewCompat(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListViewCompat(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void shrinkListItem(int position) {
		View item = getChildAt(position);

		if (item != null) {
			try {
				((SildeView) item).shrink();
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			int x = (int) event.getX();
			int y = (int) event.getY();
			int position = pointToPosition(x, y);
			if (position != INVALID_POSITION) {
				Note data = (Note) getItemAtPosition(position);
 				mFocusedItemView = data.slideView;
			}
		}
		break;
		}
		if (mFocusedItemView != null) {
			
			mFocusedItemView.OnRequireTouchEvent(event);
		}
		return super.onTouchEvent(event);
	}
}
