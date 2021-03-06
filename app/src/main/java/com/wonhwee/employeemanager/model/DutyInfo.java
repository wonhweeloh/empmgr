package com.wonhwee.employeemanager.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class DutyInfo implements Parcelable {
    private final String mDutyCode;
    private final String mDesc;
    private boolean mIsComplete = false;

    public DutyInfo(String moduleId, String title) {
        this(moduleId, title, false);
    }

    public DutyInfo(String dutyCode, String desc, boolean isComplete) {
        mDutyCode = dutyCode;
        mDesc = desc;
        mIsComplete = isComplete;
    }

    protected DutyInfo(Parcel in) {
        mDutyCode = in.readString();
        mDesc = in.readString();
        mIsComplete = in.readByte() != 0;
    }

    public static final Creator<DutyInfo> CREATOR = new Creator<DutyInfo>() {
        @Override
        public DutyInfo createFromParcel(Parcel in) {
            return new DutyInfo(in);
        }

        @Override
        public DutyInfo[] newArray(int size) {
            return new DutyInfo[size];
        }
    };

    public String getDutyCode() {
        return mDutyCode;
    }

    public String getDesc() {
        return mDesc;
    }

    public boolean isComplete() {
        return mIsComplete;
    }

    public void setComplete(boolean complete) {
        mIsComplete = complete;
    }

    @Override
    public String toString() {
        return mDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DutyInfo that = (DutyInfo) o;

        return mDutyCode.equals(that.mDutyCode);
    }

    @Override
    public int hashCode() {
        return mDutyCode.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDutyCode);
        dest.writeString(mDesc);
        dest.writeByte((byte) (mIsComplete ? 1 : 0));
    }
}
