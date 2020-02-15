package com.example.smarteducation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private EditText InputRegistrationNumber,InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView AdminLink,NotAdmin;

    private String parentDbName="Users";
    private CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton=(Button) findViewById(R.id.login_btn);
        InputRegistrationNumber=(EditText) findViewById(R.id.login_registration_number_input);
        InputPassword=(EditText) findViewById(R.id.login_password_input);
        AdminLink=(TextView)findViewById(R.id.admin_panel_link);
        NotAdmin=(TextView)findViewById(R.id.not_admin_panel_link);
        loadingBar=new ProgressDialog(this);

        chkBoxRememberMe=(CheckBox) findViewById(R.id.remember_me_chkb);
        Paper.init(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdmin.setVisibility(View.VISIBLE);
                parentDbName="Admins";
            }
        });

        NotAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdmin.setVisibility(View.INVISIBLE);
                parentDbName="Users";
            }
        });
    }

    private void LoginUser() {
        String phone=InputRegistrationNumber.getText().toString();
        String password=InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please write your phone number...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please write the password....",Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait,while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(phone,password);
        }
    }

    private void AllowAccessToAccount(final String registration, final String password) {
        if (chkBoxRememberMe.isChecked()){
            Paper.book().write(Prevalant.UserRegistrationKey,registration);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }
        DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();
        RootRef = RootRef.child(parentDbName).child(registration);

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Users userData=dataSnapshot.getValue(Users.class);
//                    Log.d("debug",userData.toString());
//                    Log.d("debug",registration);
                    if (userData!=null&&userData.getRegistration_number().equals(registration)){
                        Toast.makeText(LoginActivity.this,"Hiii",Toast.LENGTH_SHORT).show();
                        if (userData.getPassword().equals(password)){

                            if (parentDbName.equals("Admins")) {
                                Toast.makeText(LoginActivity.this, "Welcome Admin..", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                startActivity(intent);
                            }
                            else if (parentDbName.equals("Users")){
                                Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                Prevalent.currentOnlineUser=userData;
                                startActivity(intent);
                                finish();
                            }


                        }
                        else{
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this,"Password is Incorrect..",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this,"Account with this"+registration+"number do not exist",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
