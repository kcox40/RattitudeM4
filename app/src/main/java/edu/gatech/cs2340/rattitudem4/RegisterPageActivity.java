package edu.gatech.cs2340.rattitudem4;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/** 
 * @author Created by daltontouch on 10/01/2017 
 * @version v1.0 
 */ 


public class RegisterPageActivity extends AppCompatActivity {

    // Instantiate widgets for the register page.
    private EditText firstNameEntry;
    private EditText lastNameEntry;
    private EditText emailEntry;
    private EditText usernameEntry;
    private EditText passwordEntry;
    private EditText confirmPasswordEntry;
    private Button btnCreateAccount;
    private LoginDataBaseAdapter loginDataBaseAdapter;
    private RadioGroup userType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // Create instance of Database Adapter
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        // Get References of Views
        firstNameEntry = findViewById(R.id.firstNameEntry);
        lastNameEntry = findViewById(R.id.lastNameEntry);
        emailEntry = findViewById(R.id.emailEntry);
        usernameEntry = findViewById(R.id.usernameEntry);
        passwordEntry = findViewById(R.id.passwordEntry);
        confirmPasswordEntry = findViewById(R.id.confirmPasswordEntry);
        userType = findViewById(R.id.userTypeRadioGroup);


        btnCreateAccount = findViewById(R.id.createAccountButton);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEntry.getText().toString();
                String lastName = lastNameEntry.getText().toString();
                String email = emailEntry.getText().toString();
                String username = usernameEntry.getText().toString();
                String password = passwordEntry.getText().toString();
                String confirmPassword = confirmPasswordEntry.getText().toString();

                // Check if any fields are empty:
                if ("".equals(firstName) || "".equals(lastName) || "".equals(email)
                        || "".equals(username) || "".equals(password) ||
                        "".equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Field Empty",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if (userType.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(),
                            "No user type selected", Toast.LENGTH_LONG).show();
                    return;
                }
                if (loginDataBaseAdapter.getSingleEntry(username) != "NOT EXIST") {
                    Toast.makeText(getApplicationContext(),
                            "Username Already Taken", Toast.LENGTH_LONG).show();
                    return;
                }
                // Check if passwords match:
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Passwords do not match", Toast.LENGTH_LONG).show();
                } else {
                    // Save Data in Database:
                    loginDataBaseAdapter.insertEntry(username, password);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
    /** 
     * login 
     * @param view is view of backButton for login
     */ 
    public void loginBackBtn(View view) {
        // Do something in response to button
//        Intent intent = new Intent(this, MainLoginActivity.class);
//        startActivity(intent);
        finish();
    }

}
