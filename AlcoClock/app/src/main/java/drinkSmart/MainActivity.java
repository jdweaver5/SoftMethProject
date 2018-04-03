package drinkSmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button; // START button

    // string flags for shared preferences
    public static final String PROFILES = "Profiles";
    public static final String NAME = "Name";
    public static final String AGE = "Age";
    public static final String HEIGHT = "Height";
    public static final String WEIGHT = "Weight";
    public static final String GENDER = "Gender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.start); //link to xml
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Loading kegs...", Snackbar.LENGTH_LONG) // little tab slides up from bottom for status update
                        .setAction("Action", null).show();
                if(check()){
                    goToExistingUser(); // if profile already exists, go to profile page
                }
                else{
                    goToNewUser1(); // if no profile, go to create profile page
                }
            }
        });
    }

    public void goToNewUser1() { // method to go to new profile page
        Intent intent = new Intent(MainActivity.this, newUser.class);
        startActivity(intent);
    }

    public void goToExistingUser() { // method to go to current profile page
        Intent intent = new Intent(MainActivity.this, existingUser.class);
        startActivity(intent);
    }
    public boolean check() { // check if profile exists
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.contains(NAME);
    }
}

