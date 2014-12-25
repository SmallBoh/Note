package com.example.memo.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.memo.R;
import com.example.memo.entity.Note;
import com.example.memo.utlis.OnSlideListener;
import com.example.memo.utlis.SildeView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class NoteInfoAdapter extends BaseAdapter implements OnSlideListener{
	private Context context;
	private List<Note> list;
	LayoutInflater from;
	private SildeView mLastSlideViewWithStatusOn;
	public NoteInfoAdapter(Context context) {
		super();
		this.context = context;
		list = new ArrayList<Note>();
		from = LayoutInflater.from(context);
		 mLastSlideViewWithStatusOn = new SildeView(context);
	}

	public List<Note> getList() {
		return list;
	}

	public void setList(List<Note> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return getList().size();
	}

	@Override
	public Note getItem(int arg0) {
		// TODO Auto-generated method stub
		return getList().get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder vh = null;
		SildeView sildeView = (SildeView) v;
		if(sildeView == null){
			sildeView = new SildeView(context);
			sildeView.setContextView(from.inflate(R.layout.main_item, null));
			vh = new ViewHolder(sildeView);
			sildeView.setOnSlideListener(this);
			sildeView.setTag(vh);
		}else{ 
			vh = (ViewHolder) sildeView.getTag();
		}
		Note note = getItem(arg0);

		note.slideView = sildeView;
		note.slideView.shrink();
		vh.deleteHolder.setOnClickListener((OnClickListener) context);
		if(note.getName() != null){
			vh.name.setText(note.getName());
		}else{
			vh.name.setText("Null");
		}
		if(note.getDate() != null){
			vh.data.setText(note.getDate());
		}else{
			vh.data.setText("null");
		}
		return sildeView;
	}
	private static class ViewHolder {
		public TextView name;
		public TextView data;
		public ViewGroup deleteHolder;

		ViewHolder(View view) {
			name = (TextView) view.findViewById(R.id.item_name);
			data = (TextView) view.findViewById(R.id.item_date);
			deleteHolder = (ViewGroup)view.findViewById(R.id.holder);
		}
	}
	@Override
	public void onSlide(View view, int status) {
		// TODO Auto-generated method stub
		if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
			mLastSlideViewWithStatusOn.shrink();
		}
		if (status == SLIDE_STATUS_ON) {
			mLastSlideViewWithStatusOn = (SildeView) view;
		}
	}
}
