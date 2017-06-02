package com.wonhwee.employeemanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wonhwee.employeemanager.model.EmpMgrContract.DutyEntry;
import com.wonhwee.employeemanager.model.EmpMgrContract.PositionEntry;
import com.wonhwee.employeemanager.model.EmpMgrContract.EmployeeEntry;

/**
 * Created by wloh on 6/2/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "empmgr.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_POSITION_CREATE=
            "CREATE TABLE " + PositionEntry.TABLE_NAME + " (" +
                    PositionEntry._ID + " INTEGER PRIMARY KEY, " +
                    PositionEntry.COLUMN_POSITIONCODE + " TEXT, " +
                    PositionEntry.COLUMN_TITLE + " TEXT " +
                    ")";
    private static final String TABLE_DUTY_CREATE =
            "CREATE TABLE " + DutyEntry.TABLE_NAME + " (" +
                    DutyEntry._ID + " INTEGER PRIMARY KEY, " +
                    DutyEntry.COLUMN_DUTYID + " TEXT, " +
                    DutyEntry.COLUMN_DESC + " TEXT, " +
                    DutyEntry.COLUMN_ISCOMPLETE + " INTEGER, " +
                    " FOREIGN KEY("+ DutyEntry.COLUMN_POSITIONID + ") REFERENCES " +
                    DutyEntry.TABLE_NAME +
                    "(" + PositionEntry._ID +") " + ")";
    private static final String TABLE_EMPLOYEE_CREATE =
            "CREATE TABLE " + EmployeeEntry.TABLE_NAME + " (" +
                    EmployeeEntry._ID + " INTEGER PRIMARY KEY, " +
                    EmployeeEntry.COLUMN_NAME + " TEXT, " +
                    EmployeeEntry.COLUMN_POSITIONID + " TEXT, " +
                    " FOREIGN KEY("+ EmployeeEntry.COLUMN_POSITIONID + ") REFERENCES " +
                    PositionEntry.TABLE_NAME +
                    "(" + PositionEntry._ID +") " + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_POSITION_CREATE);
        db.execSQL(TABLE_DUTY_CREATE);
        db.execSQL(TABLE_EMPLOYEE_CREATE);
        ContentValues values = new ContentValues();
        values.put (PositionEntry.COLUMN_POSITIONCODE, "CEO");
        values.put (PositionEntry.COLUMN_TITLE, "Chief Executive Officer");
        long idCat = db.insert(PositionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put(DutyEntry.COLUMN_POSITIONID, String.valueOf(idCat));
        values.put(DutyEntry.COLUMN_DUTYID, "APRV YR BUGT");
        values.put(DutyEntry.COLUMN_DESC, "Approve Yearly Budget");
        values.put(DutyEntry.COLUMN_ISCOMPLETE, "0");
        long idTodo = db.insert(DutyEntry.TABLE_NAME, null, values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DutyEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PositionEntry.TABLE_NAME);
        onCreate(db);
    }
}
