package com.wonhwee.employeemanager.model;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by wloh on 6/2/17.
 */

public final class EmpMgrContract {
    //URI section
    public static final String CONTENT_AUTHORITY = "com.wonhwee.employeemanager.empmgrprovider";
    public static final String PATH_POSITION ="position";
    public static final String PATH_DUTY ="duty";
    public static final String PATH_EMPLOYEE ="employee";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public String concatContent(String path){
        return "content://" + path;
    }

    public static final class PositionEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_POSITION);

        // Table name
        public static final String TABLE_NAME = "position";
        //column (field) names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_POSITIONCODE = "positioncode";
        public static final String COLUMN_TITLE = "title";
    }

    public static final class DutyEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_DUTY);

        // Table name
        public static final String TABLE_NAME = "duty";
        //column names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DUTYID = "dutyid";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_ISCOMPLETE = "iscomplete";
        public static final String COLUMN_POSITIONID = "positionid";
    }

    public static final class EmployeeEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_EMPLOYEE);

        // Table name
        public static final String TABLE_NAME = "employee";
        //column (field) names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_POSITIONID = "positionid";
    }
}
