package com.example.memo;

import java.util.ArrayList;
import java.util.List;

import com.example.memo.actionbar.MainActionbar;
import com.example.memo.adapter.NoteInfoAdapter;
import com.example.memo.entity.Note;
import com.example.memo.entity.SerEntity;
import com.example.memo.service.DbService;
import com.example.memo.utlis.ListViewCompat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;


public class AddActivity extends Activity implements OnClickListener,OnItemClickListener{
	private MainActionbar bar;
	private TextView tImg;
	private DbService ds;
	private List<Note> listNote = new ArrayList<Note>();
	private ListViewCompat lv;
	private NoteInfoAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.soso);
		initView();
		listNote = ds.loadNote();
		adapter.setList(listNote);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		listNote = ds.loadNote();
		adapter.setList(listNote);
		lv.setAdapter(adapter);
	}
	private void initView(){
		ds = DbService.getInstance(this);
		listNote = new ArrayList<Note>();

		lv = (ListViewCompat) findViewById(R.id.main_listview);
		adapter = new NoteInfoAdapter(AddActivity.this);

		bar = new MainActionbar(AddActivity.this);
		View view = bar.setActionBar(R.layout.add_main);

		tImg = (TextView) view.findViewById(R.id.tv_img_add_main);
		tImg.setOnClickListener(l);

	}

	View.OnClickListener l = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			View ve = LayoutInflater.from(AddActivity.this).inflate(R.layout.alert, null);
			final EditText vue = (EditText) ve.findViewById(R.id.edit_alert);
			new AlertDialog.Builder(AddActivity.this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle("添加笔记")
			.setView(ve)
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent inte = new Intent(AddActivity.this, EditActivity.class);
					Note nte = new Note();
					nte.setName(vue.getText().toString());
					inte.putExtra("name", nte);
					startActivity(inte);
				}
			})
			.create().show();
		}
	};
	Note nt;
	
	//跳转 
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		nt = listNote.get(arg2);
		SerEntity set = new SerEntity();
		set.setId(nt.getId());
		set.setName(nt.getName());
		set.setVlue(nt.getVlue());
		set.setDate(nt.getDate());
		Intent intent = new Intent(AddActivity.this, ShareActivity.class);
		intent.putExtra("set",set);
		startActivity(intent);
	}

	//侧滑删除
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.holder){
			ds.deleteCityInfo(1);
			listNote = ds.loadNote();
			adapter.setList(listNote);
			lv.setAdapter(adapter);
		}
	}
}

