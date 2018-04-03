package drinkSmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class existingUser extends AppCompatActivity {
    Button chill, clear, drunk; // buttons on xml

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
        setContentView(R.layout.activity_existing_user);

        drunk = findViewById(R.id.drunk);
        chill = findViewById(R.id.chill);
        clear = findViewById(R.id.clear);

        chill.setOnClickListener(new View.OnClickListener() { // go to START
            @Override
            public void onClick(View view) {
                goToStart();

            }
        });
        clear.setOnClickListener(new View.OnClickListener() { // CLEAR profile
            @Override
            public void onClick(View view) {
               clear();
               if(check()){ // check if profile was cleared or not
                   Snackbar.make(view, "Profile still exists", Snackbar.LENGTH_LONG)
                           .setAction("Action", null).show();
               }
               else {
                   Snackbar.make(view, "Profile deleted", Snackbar.LENGTH_LONG)
                           .setAction("Action", null).show();
               }
            }
        });

    }
    public boolean check() { // method checks if profile exists
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.contains(NAME);
    }


    public void goToStart() { // method goes back to start page
        Intent intent = new Intent(existingUser.this, MainActivity.class);
        startActivity(intent);
    }

    public void clear() { // method clears a profile
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }
}
