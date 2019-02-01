package com.example.consumersignup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ProgressBar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText number;
    private EditText address;
    private EditText password;
    private EditText cpass;
    private Button register;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        name=findViewById(R.id.Name);
        email=findViewById(R.id.email);
        number=findViewById(R.id.number);
        address=findViewById(R.id.postAddress);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        cpass=findViewById(R.id.confPass);
        progressBar = findViewById(R.id.progressBar);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyData();
                String email =;
                email = email.getText().toString();
                String password= password.getText().toString();
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(MainActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }

    boolean isEmail(EditText t){
        CharSequence email= t.getText().toString();
        return(!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText str){
        CharSequence s = str.getText().toString();
        return TextUtils.isEmpty(s);
    }

    boolean invalidPW(EditText pass) {
        CharSequence p = pass.getText().toString();
        return (!TextUtils.isEmpty(p)&&p.length() < 4);
    }


    void verifyData () {
        if (isEmpty(name)) {
            name.setError("Name is mandatory");
        }

        if (isEmpty(number)) {
            number.setError("Number is mandatory");
        }

        if ((!isEmpty(number))&&(number.length() != 10)) {
            number.setError("Number must be 10 digits");
        }

        if (isEmpty(address)) {
            address.setError("Address is mandatory");
        }

        if (isEmpty(password)) {
            password.setError("Password is mandatory");
        }

        if (invalidPW(password)) {
            password.setError("Password must be minimum 4 characters");
        }

        if((isEmpty(cpass))||(!password.equals(cpass))){
            cpass.setError("Passwords must match");
        }

        if (!isEmail(email)) {
            email.setError("Enter a valid email");
        }
    }
}
