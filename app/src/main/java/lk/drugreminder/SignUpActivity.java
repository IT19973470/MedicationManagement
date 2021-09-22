package lk.drugreminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import lk.drugreminder.db.FirebaseDB;
import lk.drugreminder.model.Sickness;
import lk.drugreminder.model.User;

public class SignUpActivity extends Activity {

    private EditText txtName, txtEmail, txtPassword, txtConfPassword;
    private Button btnSignUp;
    private DatabaseReference dbUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.medi_blue));
        getWindow().setStatusBarColor(getResources().getColor(R.color.medi_blue));

        mAuth = FirebaseAuth.getInstance();

        txtName = findViewById(R.id.txt_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        txtConfPassword = findViewById(R.id.txt_confirm_password);
        btnSignUp = findViewById(R.id.btn_signup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accSignup();
            }
        });
    }


}
