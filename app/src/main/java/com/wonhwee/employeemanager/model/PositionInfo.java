package com.wonhwee.employeemanager.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public final class PositionInfo implements Parcelable {
    private final String mPositionCode;
    private final String mTitle;
    private final List<DutyInfo> mDuties;

    public PositionInfo(String positionCode, String title, List<DutyInfo> duties) {
        mPositionCode = positionCode;
        mTitle = title;
        mDuties = duties;
    }

    protected PositionInfo(Parcel in) {
        mPositionCode = in.readString();
        mTitle = in.readString();
        mDuties = in.createTypedArrayList(DutyInfo.CREATOR);
    }

    public static final Creator<PositionInfo> CREATOR = new Creator<PositionInfo>() {
        @Override
        public PositionInfo createFromParcel(Parcel in) {
            return new PositionInfo(in);
        }

        @Override
        public PositionInfo[] newArray(int size) {
            return new PositionInfo[size];
        }
    };

    public String getPositionCode() {
        return mPositionCode;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<DutyInfo> getDuties() {
        return mDuties;
    }

    public boolean[] getDutiesCompletionStatus() {
        boolean[] status = new boolean[mDuties.size()];

        for(int i = 0; i < mDuties.size(); i++)
            status[i] = mDuties.get(i).isComplete();

        return status;
    }

    public void setDutiesCompletionStatus(boolean[] status) {
        for(int i = 0; i < mDuties.size(); i++)
            mDuties.get(i).setComplete(status[i]);
    }

    public DutyInfo getDuty(String dutyId) {
        for(DutyInfo dutyInfo: mDuties) {
            if(dutyId.equals(dutyInfo.getDutyCode()))
                return dutyInfo;
        }
        return null;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionInfo that = (PositionInfo) o;

        return mPositionCode.equals(that.mPositionCode);

    }

    @Override
    public int hashCode() {
        return mPositionCode.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPositionCode);
        dest.writeString(mTitle);
        dest.writeTypedList(mDuties);
    }
}
