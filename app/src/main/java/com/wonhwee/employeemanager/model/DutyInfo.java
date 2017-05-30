package com.wonhwee.employeemanager.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class DutyInfo implements Parcelable {
    private final String mDutyId;
    private final String mDesc;
    private boolean mIsComplete = false;

    public DutyInfo(String moduleId, String title) {
        this(moduleId, title, false);
    }

    public DutyInfo(String dutyId, String desc, boolean isComplete) {
        mDutyId = dutyId;
        mDesc = desc;
        mIsComplete = isComplete;
    }

    protected DutyInfo(Parcel in) {
        mDutyId = in.readString();
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

    public String getDutyId() {
        return mDutyId;
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

        return mDutyId.equals(that.mDutyId);
    }

    @Override
    public int hashCode() {
        return mDutyId.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDutyId);
        dest.writeString(mDesc);
        dest.writeByte((byte) (mIsComplete ? 1 : 0));
    }
}
