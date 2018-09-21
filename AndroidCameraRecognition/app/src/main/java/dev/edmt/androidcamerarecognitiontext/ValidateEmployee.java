package dev.edmt.androidcamerarecognitiontext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import dev.edmt.androidcamerarecognitiontext.entities.Employee;

public class ValidateEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_employee);


        RecyclerView rvEmployees = (RecyclerView) findViewById(R.id.rvEmployees);

        // Initialize contacts
        List<Employee> employees = (List<Employee>) getIntent().getSerializableExtra("employees");
        // Create adapter passing in the sample user data
        EmployeesAdapter adapter = new EmployeesAdapter(employees);
        // Attach the adapter to the recyclerview to populate items
        rvEmployees.setAdapter(adapter);
        // Set layout manager to position the items
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
    }
}
