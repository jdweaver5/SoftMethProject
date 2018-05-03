package drinkSmart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;



public class newUser extends AppCompatActivity {
    private Button next, back;

    //input fields
    EditText nameInput;
    EditText ageInput;
    EditText heightInput;
    EditText weightInput;
    EditText genderInput;

    // shared preferences to save persistent data
    public static final String PROFILES = "Profiles";
    public static final String NAME = "Name";
    public static final String AGE = "Age";
    public static final String HEIGHT = "Height";
    public static final String WEIGHT = "Weight";
    public static final String GENDER = "Gender";


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //prevents keyboard from automatically showing up


        //link to xml buttons
        nameInput = findViewById(R.id.inputName);
        ageInput = findViewById(R.id.inputAge);
        heightInput = findViewById(R.id.inputHeight);
        weightInput = findViewById(R.id.inputWeight);
        genderInput = findViewById(R.id.inputGender);
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (saveData()) { // store in internal storage

                    case 1 : // age not a number
			Snackbar.make(view, "Age Must Be A Number", Snackbar.LENGTH_INDEFINITE)
				.setDuration(3000)
				.setAction("Action", null).show();
			ageInput.setText("");
                        break;
                    case 2 : // height not a number
			Snackbar.make(view, "Height Must Be A Number", Snackbar.LENGTH_INDEFINITE)
				.setDuration(3000)
				.setAction("Action", null).show();
			heightInput.setText("");
                        break;
                    case 3 : // weight not a number
			Snackbar.make(view, "Weight Must Be A Number", Snackbar.LENGTH_INDEFINITE)
				.setDuration(3000)
				.setAction("Action", null).show();
			weightInput.setText("");
                        break;
                    case 4 : // gender not male or female
                        Snackbar.make(view, "To make things simpler, choose:\nMale/male or Female/female", Snackbar.LENGTH_INDEFINITE)
                                .setDuration(3000)
                                .setAction("Action", null).show();
                        genderInput.setText("");
                        break;
                    default:
                        if(check()){ // check if profile exists
                            Snackbar.make(view, "Profile saved", Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();
                        }
                        break;
                }
            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHome();
            }
        });
    }

    public void gotoHome() { // method goes to Start page
        Intent intent = new Intent(newUser.this, MainActivity.class);
        startActivity(intent);
    }

    public int saveData() { // method saves profile
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);// getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(NAME, nameInput.getText().toString());
	if (nameInput.getText().toString().matches("[0-9]+")
        	editor.putInt(AGE, toInt(nameInput.getText().toString()));
	else
		return 1;
	if (heightInput.getText().toString().matches("[0-9]+")
		editor.putInt(HEIGHT, toInt(heightInput.getText().toString()));
	else
		return 2;
        if (weightInput.getText().toString().matches("[0-9]+")
		editor.putInt(WEIGHT, toInt(weightInput.getText().toString()));
	else
		return 3;
        if (genderInput.getText().toString().equals("male") || genderInput.getText().toString().equals("Male") || genderInput.getText().toString().equals("female") || genderInput.getText().toString().equals("Female")) {
            editor.putString(GENDER, genderInput.getText().toString());
        }
        else {
            return 4;
        }
        //editor.putString(MAX, genderInput.getText().toString());

        editor.apply();
        return 0;
    }

    public int toInt(String temp){ // method converts string to int
        int converted = 0;
        try {
            converted = Integer.parseInt(temp);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return converted;
    }
    public boolean check() { // checks if profile exists
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.contains(NAME);
    }

}

