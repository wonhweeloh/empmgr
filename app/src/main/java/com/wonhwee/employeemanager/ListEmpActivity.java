package com.wonhwee.employeemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wonhwee.employeemanager.dao.DataManager;
import com.wonhwee.employeemanager.model.EmployeeInfo;

import java.util.List;

public class ListEmpActivity extends AppCompatActivity {

    private ArrayAdapter<EmployeeInfo> mAdapterEmployeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_emp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add employee information", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                startActivity(new Intent(ListEmpActivity.this, EditEmpActivity.class));
            }
        });
        
        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapterEmployeeList.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
        final ListView listEmp = (ListView) findViewById(R.id.list_emp);

        List<EmployeeInfo> employeeInfoList = DataManager.getInstance().getEmployee();
        mAdapterEmployeeList = new ArrayAdapter(this, android.R.layout.simple_list_item_1, employeeInfoList );

        listEmp.setAdapter(mAdapterEmployeeList);

        listEmp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListEmpActivity.this, EditEmpActivity.class);
                intent.putExtra(EditEmpActivity.EMPLOYEE_POSITION, position);
                startActivity(intent);
            }
        });
    }

}
