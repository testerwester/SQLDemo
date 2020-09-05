package com.example.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btn_add, btn_view;
    EditText et_name, et_age;
    Switch sw_activPerson;
    ListView lw_personView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add = findViewById(R.id.AddButton);
        btn_view = findViewById(R.id.ViewButton);

        et_name = findViewById(R.id.FirstName);
        et_age = findViewById(R.id.Age);

        sw_activPerson = findViewById(R.id.IsActive);

        lw_personView = findViewById(R.id.listView);

        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                try{
                    PersonModel person = new PersonModel(et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_activPerson.isChecked());
                    Toast.makeText(MainActivity.this, person.toString(), Toast.LENGTH_LONG).show();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }



            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "View button", Toast.LENGTH_SHORT).show();
            }
        });

    }
}