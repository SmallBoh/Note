package com.example.memo.service;

import java.util.List;

import com.example.memo.entity.DaoSession;
import com.example.memo.entity.Note;
import com.example.memo.entity.NoteDao;
import com.example.memo.entity.NoteDao.Properties;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;

import android.content.Context;



public class DbService {
	private DaoSession mDaoSession;
	private NoteDao mNoteDao;
	private static Context mContext;
	private static DbService mInstance;

	public DbService(){

	}
	public static DbService getInstance(Context context){
		if(mInstance == null){
			mInstance = new DbService();
			if(mContext == null){
				mContext = context.getApplicationContext();
			}
			mInstance.mDaoSession = BaseApplication.getDaoSession(context);
			mInstance.mNoteDao = mInstance.mDaoSession.getNoteDao();
		}

		return mInstance;
	}
	//单个数据
	public long saveNote(Note note){
		return mNoteDao.insert(note);
	}
	public List<Note> loadNote(){
		//查询所有数据 
		System.out.println("======="+mNoteDao.loadAll() );
		return mNoteDao.loadAll();
	}
	public Note loadNote(String name){

		return (Note) mNoteDao.queryRaw("NAME", name);
	}

	//删除数据
	public void deleteNote(){
		mNoteDao.deleteAll();
		//删除一条数据   mNoteDao.delete(note);
	}

	public void deleteCityInfo(int cityId)

	{ mNoteDao.deleteAll();
	QueryBuilder<Note> qb = mNoteDao.queryBuilder();

	DeleteQuery<Note> bd = qb.where(Properties.Id.eq(cityId)).buildDelete();

	bd.executeDeleteWithoutDetachingEntities();

	}

	public List<Note> loadNote(String where,String...paams){
		return mNoteDao.queryRaw(where, paams);
	}
	public void update(Note entity){

		mNoteDao.update(entity);
	}
}
