package com.android.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.android.lab1.model.Covid19;

import java.util.ArrayList;

public class SqliteHelper extends SQLiteOpenHelper {
    private static final String name = "covid1.db";
    private static final int version = 1;

    public SqliteHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE covid(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, cauTruc TEXT, date TEXT, " +
                "vacxin TEXT, sl INTEGER)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public ArrayList<Covid19> getAll() {
        ArrayList<Covid19> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.query("covid", null, null, null, null, null, null);

        while (result != null && result.moveToNext()) {
            int id = result.getInt(0);
            String nameCovid = result.getString(1);
            String cauTruc = result.getString(2);
            String ngayXh = result.getString(3);
            String vacxin = result.getString(4);
            int sl = result.getInt(5);

            list.add(new Covid19(id, nameCovid, cauTruc, ngayXh, vacxin, sl));
        }

        return list;
    }

    public ArrayList<Covid19> getByDate(String s1, String s2) {
        ArrayList<Covid19> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String whereClause = "date BETWEEN ? AND ?";
        String[] whereArgs = {s1, s2};
        Cursor result = db.query("covid", null, whereClause, whereArgs, null, null, null);

        while (result != null && result.moveToNext()) {
            int id = result.getInt(0);
            String nameCovid = result.getString(1);
            String cauTruc = result.getString(2);
            String ngayXh = result.getString(3);
            String vacxin = result.getString(4);
            int sl = result.getInt(5);

            list.add(new Covid19(id, nameCovid, cauTruc, ngayXh, vacxin, sl));
        }

        return list;
    }

    public long add(Covid19 nv) {
        ContentValues content = new ContentValues();
        content.put("name", nv.getName());
        content.put("cauTruc", nv.getCauTruc());
        content.put("date", nv.getNgayXuatHien());
        content.put("vacxin", nv.getVacxin());
        content.put("sl", nv.getSoLuong());

        SQLiteDatabase db = getWritableDatabase();
        return db.insert("covid", null, content);
    }

    public int update(Covid19 nv) {
        ContentValues content = new ContentValues();
        content.put("name", nv.getName());
        content.put("cauTruc", nv.getCauTruc());
        content.put("date", nv.getNgayXuatHien());
        content.put("vacxin", nv.getVacxin());
        content.put("sl", nv.getSoLuong());

        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(nv.getId())};

        SQLiteDatabase db = getWritableDatabase();
        return db.update("covid", content, whereClause, whereArgs);
    }

    public int delete(Covid19 nv) {
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(nv.getId())};

        SQLiteDatabase db = getWritableDatabase();
        return db.delete("covid", whereClause, whereArgs);
    }
}