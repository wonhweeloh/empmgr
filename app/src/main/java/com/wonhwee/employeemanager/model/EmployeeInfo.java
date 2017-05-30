package com.wonhwee.employeemanager.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class EmployeeInfo implements Parcelable {
    private PositionInfo mPositionInfo;
    private String mName;
    private String mEmail;

    public EmployeeInfo(PositionInfo positionInfo, String title, String text) {
        mPositionInfo = positionInfo;
        mName = title;
        mEmail = text;
    }

    protected EmployeeInfo(Parcel in) {
        mPositionInfo = in.readParcelable(PositionInfo.class.getClassLoader());
        mName = in.readString();
        mEmail = in.readString();
    }

    public static final Creator<EmployeeInfo> CREATOR = new Creator<EmployeeInfo>() {
        @Override
        public EmployeeInfo createFromParcel(Parcel in) {
            return new EmployeeInfo(in);
        }

        @Override
        public EmployeeInfo[] newArray(int size) {
            return new EmployeeInfo[size];
        }
    };

    public PositionInfo getPositionInfo() {
        return mPositionInfo;
    }

    public void setPositionInfo(PositionInfo course) {
        mPositionInfo = course;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    private String getCompareKey() {
        return mPositionInfo.getPositionId() + "|" + mName + "|" + mEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeInfo that = (EmployeeInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mPositionInfo, 0);
dest.writeString(mName);
        dest.writeString(mEmail);
    }
}
