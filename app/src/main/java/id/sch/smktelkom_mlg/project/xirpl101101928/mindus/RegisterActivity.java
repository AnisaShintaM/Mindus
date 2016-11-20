package id.sch.smktelkom_mlg.project.xirpl101101928.mindus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRegister;
    private EditText editTextemail;
    private EditText editTextpassword;
    private TextView textViewregister;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.btn_login);
        editTextemail = (EditText) findViewById(R.id.editText4);
        editTextpassword = (EditText) findViewById(R.id.editText5);
        textViewregister = (TextView) findViewById(R.id.textView9);
        buttonRegister.setOnClickListener(this);
        textViewregister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            registeruser();
        }
        if (view == textViewregister) {
            // will open login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void registeruser() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please ENter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Could not register. Please Try Again", Toast.LENGTH_SHORT);

                        }
                    }
                });
    }
}