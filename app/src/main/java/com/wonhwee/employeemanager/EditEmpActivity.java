package com.wonhwee.employeemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.wonhwee.employeemanager.dao.DataManager;
import com.wonhwee.employeemanager.model.EmployeeInfo;
import com.wonhwee.employeemanager.model.PositionInfo;

import java.util.List;

public class EditEmpActivity extends AppCompatActivity {
    public static final String EMPLOYEE_POSITION = "com.wonhwee.employeemanager.EMPLOYEE_POSITION";
    public static final String ORIGINAL_EMPLOYEE_POS_ID = "com.wonhwee.employeemanager.ORIGINAL_EMPLOYEE_POS_ID";
    public static final String ORIGINAL_EMPLOYEE_NAME = "com.wonhwee.employeemanager.ORIGINAL_EMPLOYEE_NAME";
    public static final String ORIGINAL_EMPLOYEE_EMAIL = "com.wonhwee.employeemanager.ORIGINAL_EMPLOYEE_EMAIL";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(ORIGINAL_EMPLOYEE_POS_ID, mOriginalEmpPositionId);
        outState.putString(ORIGINAL_EMPLOYEE_NAME, mOriginalEmpName);
        outState.putString(ORIGINAL_EMPLOYEE_EMAIL, mOriginalEmpEmail);
    }

    public static final int POSITION_NOT_SET = -1;
    private EmployeeInfo mEmployeeInfo;
    private boolean mIsNewEmployee;
    private Spinner mSpinnerPositions;
    private EditText mTextEmpName;
    private EditText mTextEmpEmail;
    private int mEmployeePosition;
    private boolean mIsCancelling;
    private String mOriginalEmpPositionId;
    private String mOriginalEmpName;
    private String mOriginalEmpEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSpinnerPositions = (Spinner)findViewById(R.id.spinner_positions);

        List<PositionInfo> positions = DataManager.getInstance().getPositions();
        ArrayAdapter<PositionInfo> adapterPosition =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positions);

        adapterPosition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerPositions.setAdapter(adapterPosition);

        readDisplayStateValues();

        if(savedInstanceState == null) {
            saveOriginalEmployeeValues();
        }
        else{
            restoreOriginalEmployeeValues(savedInstanceState);
        }

        mTextEmpName = (EditText) findViewById(R.id.text_emp_name);
        mTextEmpEmail = (EditText) findViewById(R.id.text_emp_email);

        if(!mIsNewEmployee) {
            displayEmployee(mSpinnerPositions, mTextEmpName, mTextEmpEmail);
        }
    }

    private void restoreOriginalEmployeeValues(Bundle savedInstanceState) {
        mOriginalEmpPositionId = savedInstanceState.getString(ORIGINAL_EMPLOYEE_POS_ID);
        mOriginalEmpName = savedInstanceState.getString(ORIGINAL_EMPLOYEE_NAME);
        mOriginalEmpEmail = savedInstanceState.getString(ORIGINAL_EMPLOYEE_EMAIL);
    }

    private void saveOriginalEmployeeValues() {
        if(mIsNewEmployee){
            return;
        }

        mOriginalEmpPositionId = mEmployeeInfo.getPositionInfo().getPositionCode();
        mOriginalEmpName = mEmployeeInfo.getName();
        mOriginalEmpEmail = mEmployeeInfo.getEmail();
    }

    private void displayEmployee(Spinner spinnerPositions, EditText textEmpName, EditText textEmpEmail) {
        List<PositionInfo> positionsList = DataManager.getInstance().getPositions();
        int positionIndex = positionsList.indexOf(mEmployeeInfo.getPositionInfo());
        spinnerPositions.setSelection(positionIndex);
        textEmpName.setText(mEmployeeInfo.getName());
        textEmpEmail.setText(mEmployeeInfo.getEmail());
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        int position = intent.getIntExtra(EMPLOYEE_POSITION, POSITION_NOT_SET);
        mIsNewEmployee = position == POSITION_NOT_SET;

        if(mIsNewEmployee){
            createNewEmployee();
        }
        else{
            mEmployeeInfo = DataManager.getInstance().getEmployee().get(position);
        }
    }

    private void createNewEmployee() {
        DataManager dm = DataManager.getInstance();
        mEmployeePosition = dm.createNewEmployee();
        mEmployeeInfo = dm.getEmployee().get(mEmployeePosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_emp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send_email) {
            sendEmail();
            return true;
        } else if (id == R.id.action_cancel) {
            mIsCancelling = true;
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(mIsCancelling){
          if(mIsNewEmployee){
              DataManager.getInstance().removeEmployee(mEmployeePosition);
          } else {
              storePreviousEmployeeValues();
          }
        }
        else {
            saveEmployee();
        }
    }

    private void storePreviousEmployeeValues() {
        PositionInfo positionInfo = DataManager.getInstance().getPositionInfo(mOriginalEmpPositionId);
        mEmployeeInfo.setPositionInfo(positionInfo);
        mEmployeeInfo.setName(mOriginalEmpName);
        mEmployeeInfo.setEmail(mOriginalEmpEmail);
    }

    private void saveEmployee() {
        mEmployeeInfo.setPositionInfo((PositionInfo) mSpinnerPositions.getSelectedItem());
        mEmployeeInfo.setName(mTextEmpName.getText().toString());
        mEmployeeInfo.setEmail(mTextEmpEmail.getText().toString());
    }


    private void sendEmail() {
        PositionInfo positionInfo = (PositionInfo)mSpinnerPositions.getSelectedItem();
        String subject = mTextEmpName.getText().toString();
        String text = "Important notification for \"" + positionInfo.getTitle() + "\" " + mTextEmpEmail.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822");

        String[] recepients = new String[1];
        recepients[0] = mTextEmpEmail.getText().toString();

        intent.putExtra(Intent.EXTRA_EMAIL, recepients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }
}
