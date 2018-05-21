package com.br.kid.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterAccount extends AppCompatActivity {
    //TODO snack bar
    //TODO database entry

    private LinearLayout linlayout;
    private LinearLayout main_layout;
    private FirebaseAuth mAuth;

    private EditText username;
    private EditText email;
    private EditText password;
    private EditText phoneno;

    private Button register_btn;

    private Spinner spinner;

    private String username_text;
    private String email_text;
    private String password_text;
    private String phno_text;
    private String catogery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar)
        mAuth = FirebaseAuth.getInstance();
        linlayout=(LinearLayout) findViewById(R.id.email_register_form);
        linlayout.setBackground(ContextCompat.getDrawable(this,R.drawable.frame));

        main_layout=(LinearLayout)findViewById(R.id.child_email_register);
        main_layout.setBackgroundColor(Color.WHITE);

        spinner = (Spinner) findViewById(R.id.catogery_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.catogery_spinner_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        username=(EditText)findViewById(R.id.user_name_register);
        email=(EditText)findViewById(R.id.register_email);
        password=(EditText)findViewById(R.id.register_password);
        phoneno=(EditText)findViewById(R.id.phoneno_register);
        register_btn=(Button)findViewById(R.id.register_user_button);



        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_text=username.getText().toString();
                email_text=email.getText().toString();
                password_text=password.getText().toString();
                phno_text=phoneno.getText().toString();
                catogery=spinner.getSelectedItem().toString();

                if(checkRegisterFields(username_text,email_text,password_text,phno_text)){
                    mAuth.createUserWithEmailAndPassword(email_text, password_text)
                        .addOnCompleteListener(RegisterAccount.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("createuser", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent login=new Intent(RegisterAccount.this,LoginActivity.class);
                                    startActivity(login);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Createuser", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterAccount.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                }



            }
        });


//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Repl ace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
    private boolean isEmailValid(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean isUserNameValid(String username){
        if (username.isEmpty()){
            return false;
        }
        return true;
    }

    private boolean isPhoneNumberValid(String pno){
        if (pno.isEmpty()){
            return false;
        }
        return true;
    }
    private boolean checkRegisterFields(String uname,String mail,String pwd,String pno){
        //check user name
        if (!isUserNameValid(uname)){
            username.setError("Enter valid user name");
            return false;
        }
        //check email
        if (!isEmailValid(mail)){
            email.setError("Enter valid email");
            return false;
        }
        if (!isPasswordValid(pwd)){
            password.setError("Enter valid password");
            return false;
        }
        if(!isPhoneNumberValid(pno)){
            phoneno.setError("Enter valid phone number");
            return false;
        }
        return true;
    }

}
