package com.wonhwee.employeemanager.dao;

import com.wonhwee.employeemanager.model.DutyInfo;
import com.wonhwee.employeemanager.model.EmployeeInfo;
import com.wonhwee.employeemanager.model.PositionInfo;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance = null;

    private List<PositionInfo> mPosition = new ArrayList<>();
    private List<EmployeeInfo> mEmployee = new ArrayList<>();

    public static DataManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManager();
            ourInstance.initializePositions();
            ourInstance.initializeEmployees();
        }
        return ourInstance;
    }

    public String getCurrentUserName() {
        return "Wonhwee Loh";
    }

    public String getCurrentUserEmail() {
        return "wohweeloh@gmail.com";
    }

    public List<EmployeeInfo> getEmployee() {
        return mEmployee;
    }

    public int createNewEmployee() {
        EmployeeInfo employeeInfo = new EmployeeInfo(null, null, null);
        mEmployee.add(employeeInfo);
        return mEmployee.size() - 1;
    }

    public int findEmployee(EmployeeInfo employeeInfo) {
        for(int index = 0; index < mEmployee.size(); index++) {
            if(employeeInfo.equals(mEmployee.get(index)))
                return index;
        }

        return -1;
    }

    public void removeEmployee(int index) {
        mEmployee.remove(index);
    }

    public List<PositionInfo> getPositions() {
        return mPosition;
    }

    public PositionInfo getPositionInfo(String id) {
        for (PositionInfo positionInfo : mPosition) {
            if (id.equals(positionInfo.getPositionId()))
                return positionInfo;
        }
        return null;
    }

    public List<EmployeeInfo> getEmployees(PositionInfo positionInfo) {
        ArrayList<EmployeeInfo> employees = new ArrayList<>();
        for(EmployeeInfo employeeInfo: mEmployee) {
            if(positionInfo.equals(employeeInfo.getPositionInfo()))
                employees.add(employeeInfo);
        }
        return employees;
    }

    public int getEmployeeCount(PositionInfo positionInfo) {
        int count = 0;
        for(EmployeeInfo employeeInfo: mEmployee) {
            if(positionInfo.equals(employeeInfo.getPositionInfo()))
                count++;
        }
        return count;
    }

    private DataManager() {
    }

    private void initializePositions() {
        mPosition.add(initializePosition1());
        mPosition.add(initializePosition2());
        mPosition.add(initializePosition3());
        mPosition.add(initializePosition4());
    }

    public void initializeEmployees() {
        final DataManager dm = getInstance();

        PositionInfo position = dm.getPositionInfo("CMO");
        position.getDuty("CMO_m01").setComplete(true);
        position.getDuty("CMO_m02").setComplete(true);
        position.getDuty("CMO_m03").setComplete(true);

        mEmployee.add(new EmployeeInfo(position, "Howard Sigman", "wonhweeloh@gmail.com"));
        mEmployee.add(new EmployeeInfo(position, "Berry White", "amapola21c@yahoo.ca"));

        position = dm.getPositionInfo("CTO");
        position.getDuty("CTO_m01").setComplete(true);
        position.getDuty("CTO_m02").setComplete(true);
        mEmployee.add(new EmployeeInfo(position, "Dustin Hoffman", "wonhweeloh@gmail.com"));
        mEmployee.add(new EmployeeInfo(position, "Bill Sigman", "wonhwee_loh@yahoo.ca"));

        position = dm.getPositionInfo("CFO");
        position.getDuty("CFO_m01").setComplete(true);
        position.getDuty("CFO_m02").setComplete(true);
        position.getDuty("CFO_m03").setComplete(true);
        position.getDuty("CFO_m04").setComplete(true);
        position.getDuty("CFO_m05").setComplete(true);
        position.getDuty("CFO_m06").setComplete(true);
        position.getDuty("CFO_m07").setComplete(true);
        mEmployee.add(new EmployeeInfo(position, "Kirk Douglas", "wonhweeloh@gmail.com"));
        mEmployee.add(new EmployeeInfo(position, "Marteen Sheen", "wonhwee@hotmail.com"));

        position = dm.getPositionInfo("CEO");
        position.getDuty("CEO_m01").setComplete(true);
        position.getDuty("CEO_m02").setComplete(true);
        position.getDuty("CEO_m03").setComplete(true);
        mEmployee.add(new EmployeeInfo(position, "Bill Carpenters", "wonhweeloh@gmail.com"));
        mEmployee.add(new EmployeeInfo(position, "Billy Joel", "wonhwee_loh@hotmail.com"));
    }

    private PositionInfo initializePosition1() {
        List<DutyInfo> duties = new ArrayList<>();
        duties.add(new DutyInfo("CMO_m01", "Doing my job"));
        duties.add(new DutyInfo("CMO_m02", "Doing my job"));
        duties.add(new DutyInfo("CMO_m03", "Doing my job"));
        duties.add(new DutyInfo("CMO_m04", "Doing my job"));
        duties.add(new DutyInfo("CMO_m05", "Doing my job"));

        return new PositionInfo("CMO", "CMO: Chief Marketing Officer", duties);
    }

    private PositionInfo initializePosition2() {
        List<DutyInfo> duties = new ArrayList<>();
        duties.add(new DutyInfo("CTO_m01", "Doing my job"));
        duties.add(new DutyInfo("CTO_m02", "Doing my job"));
        duties.add(new DutyInfo("CTO_m03", "Doing my job"));
        duties.add(new DutyInfo("CTO_m04", "Doing my job"));

        return new PositionInfo("CTO", "CTO: Chief Technology Officer", duties);
    }

    private PositionInfo initializePosition3() {
        List<DutyInfo> duties = new ArrayList<>();
        duties.add(new DutyInfo("CFO_m01", "Doing my job"));
        duties.add(new DutyInfo("CFO_m02", "Doing my job"));
        duties.add(new DutyInfo("CFO_m03", "Doing my job"));
        duties.add(new DutyInfo("CFO_m04", "Doing my job"));
        duties.add(new DutyInfo("CFO_m05", "Doing my job"));
        duties.add(new DutyInfo("CFO_m06", "Doing my job"));
        duties.add(new DutyInfo("CFO_m07", "Doing my job"));
        duties.add(new DutyInfo("CFO_m08", "Doing my job"));
        duties.add(new DutyInfo("CFO_m09", "Doing my job"));
        duties.add(new DutyInfo("CFO_m10", "Doing my job"));
        duties.add(new DutyInfo("CFO_m11", "Doing my job"));
        duties.add(new DutyInfo("CFO_m12", "Doing my job"));
        duties.add(new DutyInfo("CFO_m13", "Doing my job"));

        return new PositionInfo("CFO", "CFO: Cheif Financial Officer", duties);
    }

    private PositionInfo initializePosition4() {
        List<DutyInfo> duties = new ArrayList<>();
        duties.add(new DutyInfo("CEO_m01", "Doing my job"));
        duties.add(new DutyInfo("CEO_m02", "Doing my job"));
        duties.add(new DutyInfo("CEO_m03", "Doing my job"));
        duties.add(new DutyInfo("CEO_m04", "Doing my job"));
        duties.add(new DutyInfo("CEO_m05", "Doing my job"));
        duties.add(new DutyInfo("CEO_m06", "Doing my job"));
        duties.add(new DutyInfo("CEO_m07", "Doing my job"));
        duties.add(new DutyInfo("CEO_m08", "Doing my job"));
        duties.add(new DutyInfo("CEO_m09", "Doing my job"));
        duties.add(new DutyInfo("CEO_m10", "Doing my job"));

        return new PositionInfo("CEO", "CEO: Chief Executive Officer", duties);
    }
}
