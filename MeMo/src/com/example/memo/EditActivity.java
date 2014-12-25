package com.example.memo;

import java.util.Calendar;

import com.example.memo.entity.Note;
import com.example.memo.service.DbService;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class EditActivity extends Activity {
	private TextView name;
	private Button btn_return;
	private Button btn_save;
	private EditText edit;
	private Note nt;
	private DbService ds;
	private static int ID = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_note);
		initView();
		Intent inte = getIntent();
		nt = (Note) inte.getSerializableExtra("name");
		name.setText(nt.getName());
		nt.setId(ID);
	}
	
	private void initView(){
		name = (TextView) findViewById(R.id.edit_note_title);
		btn_return = (Button) findViewById(R.id.but_return);
		btn_save = (Button) findViewById(R.id.but_save);
		edit = (EditText) findViewById(R.id.edit_value);

		ds = DbService.getInstance(this);


		btn_return.setOnClickListener(l);
		btn_save.setOnClickListener(l);
	}
	View.OnClickListener l = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.but_return:

				finish();

				break;

			case R.id.but_save:
				
				nt.setVlue(edit.getText().toString());
				
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int	day = calendar.get(Calendar.DAY_OF_MONTH);
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minu = calendar.get(Calendar.MINUTE);
				
				nt.setDate(year+ "年" + month +"月"+day+"日"+hour+"时"+minu + "分");
				
				System.out.println("fssdsd"+nt.toString());
				ds.saveNote(nt);
				finish();
				break;
			}
		}
	};
}
