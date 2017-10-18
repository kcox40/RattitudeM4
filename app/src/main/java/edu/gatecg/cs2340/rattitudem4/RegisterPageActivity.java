package edu.gatecg.cs2340.rattitudem4;

/**
 * Created by daltontouch on 10/01/2017
 * v1.0
 */

import android.content.Intent;
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
    EditText firstNameEntry,lastNameEntry,emailEntry,usernameEntry,passwordEntry,confirmPasswordEntry;
    Button btnCreateAccount,btnBack;
    LoginDataBaseAdapter loginDataBaseAdapter;
    RadioGroup userType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // Create instance of Database Adapter
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        // Get References of Views
        firstNameEntry = (EditText) findViewById(R.id.firstNameEntry);
        lastNameEntry = (EditText) findViewById(R.id.lastNameEntry);
        emailEntry = (EditText) findViewById(R.id.emailEntry);
        usernameEntry = (EditText) findViewById(R.id.usernameEntry);
        passwordEntry = (EditText) findViewById(R.id.passwordEntry);
        confirmPasswordEntry = (EditText) findViewById(R.id.confirmPasswordEntry);
        userType = (RadioGroup) findViewById(R.id.userTypeRadioGroup);


        btnBack = (Button) findViewById(R.id.backButton);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(this, MainLoginActivity.class);
//                startActivity(intent);
//            }
//        });

        btnCreateAccount = (Button) findViewById(R.id.createAccountButton);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String firstName = firstNameEntry.getText().toString();
                String lastName = lastNameEntry.getText().toString();
                String email = emailEntry.getText().toString();
                String username = usernameEntry.getText().toString();
                String password = passwordEntry.getText().toString();
                String confirmPassword = confirmPasswordEntry.getText().toString();

                // Check if any fields are empty:
                if (firstName.equals("") || lastName.equals("") || email.equals("")
                        || username.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Empty", Toast.LENGTH_LONG).show();
                    return;
                }

                if (userType.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(),
                            "No user type selected", Toast.LENGTH_LONG).show();
                    return;
                }
                if (loginDataBaseAdapter.getSinlgeEntry(username) != "NOT EXIST") {
                    Toast.makeText(getApplicationContext(),
                            "Username Already Taken", Toast.LENGTH_LONG).show();
                    return;
                }
                // Check if passwords match:
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Passwords do not match", Toast.LENGTH_LONG).show();
                    return;
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
     * @param view is view of backbutton for login 
     */ 
    public void loginBackBtn(View view) {
        // Do something in response to button
//        Intent intent = new Intent(this, MainLoginActivity.class);
//        startActivity(intent);
        finish();
    }

}
