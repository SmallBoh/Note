package com.example.memo.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.example.memo.utlis.SildeView;

public class SerEntity implements Parcelable{

	private Integer id;
	private String name;
	private String date;
	private String Vlue;


	public SerEntity() {

	}

	public SerEntity(Integer id, String name, String date, String Vlue) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.Vlue = Vlue;
	}
	public SerEntity(Parcel source){
		this.id = source.readInt();
		this.name = source.readString();
		this.Vlue = source.readString();
		this.date = source.readString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVlue() {
		return Vlue;
	}

	public void setVlue(String Vlue) {
		this.Vlue = Vlue;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", name=" + name + ", date=" + date
				+ ", Vlue=" + Vlue + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(Vlue);
		dest.writeString(date);
	}


	public static final Parcelable.Creator<SerEntity> CREATOR = new Creator<SerEntity>(){

		@Override
		public SerEntity createFromParcel(Parcel arg0) {
			// TODO Auto-generated method stub
			return new SerEntity(arg0);
		}

		@Override
		public SerEntity[] newArray(int arg0) {
			// TODO Auto-generated method stub
			return new SerEntity[arg0];
		}

	};

}
