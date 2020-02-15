package com.example.smarteducation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class RegproActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private EditText editTextName, editClass, editPhone;
    private Button buttonSave;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regpro);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        editTextName = findViewById(R.id.editTextName);
        editClass = findViewById(R.id.editClass);
        editPhone = findViewById(R.id.editPhone);
        buttonSave = findViewById(R.id.buttonSave);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        buttonSave.setOnClickListener(this);

    }

    private void saveUserInformation(){
        String name = editTextName.getText().toString().trim();
        String classs = editClass.getText().toString().trim();

        String phone = editPhone.getText().toString().trim();


        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(classs)||TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
        }
        else {
            //String id = databaseReference.push().getKey();
            String id = firebaseAuth.getCurrentUser().getUid();
            UserInformation userInformation = new UserInformation(id, name, classs, phone);
            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(id).setValue(userInformation);
        }

        Toast.makeText(this, "Information Updated ... ",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onClick(View v) {

        if(v==buttonSave){
            saveUserInformation();
            finish();
//            startActivity(new Intent(this,ProfileActivity.class));
        }

    }
}
