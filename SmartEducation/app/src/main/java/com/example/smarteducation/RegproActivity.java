package com.example.smarteducation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static android.widget.Toast.LENGTH_SHORT;

public class RegproActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private EditText editTextName, editClass, editPhone;
    private Button buttonSave;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regpro);



        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }


        editTextName = findViewById(R.id.editTextName);
        editPhone = findViewById(R.id.editPhone);
        editClass = findViewById(R.id.editClass);
        buttonSave = findViewById(R.id.buttonSave);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });

    }

  /*  public void addListenerOnSpinnerItemSelection() {
        //sp1 = (Spinner) findViewById(R.id.spinner1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classs = sp1.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(),"Item selected", LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Item not selected", LENGTH_SHORT).show();
            }
        });
    }
*/
    private void saveUserInformation(){
        final String name = editTextName.getText().toString().trim();
        final String classs = editClass.getText().toString().trim();
        final String phone = editPhone.getText().toString().trim();


            final String id = firebaseAuth.getCurrentUser().getUid();

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("Users");

            UserInformation userInformation = new UserInformation(id, name, classs, phone);
            databaseReference.setValue(userInformation).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(RegproActivity.this, "Hello", LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegproActivity.this, "Success", LENGTH_SHORT).show();
                    }
                }
            });


            FirebaseUser user = firebaseAuth.getCurrentUser();
//            databaseReference.child(id).setValue(userInformation);
//        }

        Toast.makeText(this, "Information Updated ... ", LENGTH_SHORT).show();


    }

    @Override
    public void onClick(View v) {

        if(v==buttonSave){
            saveUserInformation();
            finish();
            startActivity(new Intent(this,HomeActivity.class));
        }

    }

}
