package com.example.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button btn_add, btn_view;
    EditText et_name, et_age;
    Switch sw_activPerson;
    ListView lw_personView;


    private void updateList()
    {
        DataBaseHelper dataBasehelper = new DataBaseHelper(MainActivity.this);

        List<PersonModel> everyone = dataBasehelper.getEveryone();
        //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

        ArrayAdapter personArrayAdapter = new ArrayAdapter<PersonModel>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
        lw_personView.setAdapter(personArrayAdapter);
    }

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

        updateList();



        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                PersonModel person;
                try{
                     person = new PersonModel(et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_activPerson.isChecked());
                    Toast.makeText(MainActivity.this, person.toString(), Toast.LENGTH_LONG).show();
                    updateList();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    person = new PersonModel("error", 0, false);

                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.addOne(person);

                Toast.makeText(MainActivity.this, "Success = " + success, Toast.LENGTH_SHORT).show();
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateList();
            }
        });


    }

}