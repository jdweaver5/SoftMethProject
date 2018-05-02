package drinkSmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public class existingUser extends AppCompatActivity {
    Button home, clear; // buttons on xml
    Button drink;
    Button drink1;
    Button drink2;
    Button drink3;
    Button drink4;
    Button drink5;
    Button drink6;
    Button drink7;
    Button drink8;
    Button drink9;
    Button drink10;
    Button drink11;
    Button drink12;
    Button drink13;
    Button drink14;
    Button drink15;
    Button drink16;
    Button drink17;
    Button drink18;


    TextView bac1;
    TextView drinks1;
    // private double timeHours = 0.0;
    public double time = 0.0;
    public double limit = 0.04;
    public double limitHours = 0.0;
    public double limitMinutes = 0.0;
    private double mainbac = 0.0;
    private int weight;
    private double gender;
    private double alcPercent;
    private int counter = 0;

    // string flags for shared preferences
    public static final String PROFILES = "Profiles";
    public static final String NAME = "Name";
    public static final String AGE = "Age";
    public static final String HEIGHT = "Height";
    public static final String WEIGHT = "Weight";
    public static final String GENDER = "Gender";
    public static final String BAC = "Bac";
    public static final String TIME = "Time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_user);


        bac1 = findViewById(R.id.bac);
        drinks1 = findViewById(R.id.drinks);
        drink = findViewById(R.id.drink); // light beer
        drink1 = findViewById(R.id.drink1); // IPA
        drink2 = findViewById(R.id.drink2); // Stout
        drink3 = findViewById(R.id.drink3); // Red Wine
        drink4 = findViewById(R.id.drink4); // White wine
        drink5 = findViewById(R.id.drink5); // Rose
        drink6 = findViewById(R.id.drink6); // Champagne
        drink7 = findViewById(R.id.drink7); // Cider
        drink8 = findViewById(R.id.drink8); // Smirnoff
        drink9 = findViewById(R.id.drink9); // Whiskey
        drink10 = findViewById(R.id.drink10); // Vodka
        drink11 = findViewById(R.id.drink11); // Gin
        drink12 = findViewById(R.id.drink12); // Rum
        drink13 = findViewById(R.id.drink13); // Tequila
        drink14 = findViewById(R.id.drink14); // Moonshine
        drink15 = findViewById(R.id.drink15); // Malibu
        drink16 = findViewById(R.id.drink16); // Fireball
        drink17 = findViewById(R.id.drink17); // Bombay
        drink18 = findViewById(R.id.drink18); // Captain Morgan



        home = findViewById(R.id.home);
        clear = findViewById(R.id.clear);

        alcPercent = 0.0;
        loadProfile();
        //mainbac = calcBAC();
        drinks1.setText(Integer.toString(counter));
        bac1.setText(String.format("%.2f", mainbac));


        home.setOnClickListener(new View.OnClickListener() { // go to START
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
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // light beer
                alcPercent = 12 * 0.09/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // IPA
                alcPercent = 12 * 0.12/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Stout
                alcPercent = 12 * 0.15/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Red wine
                alcPercent = 5 * 0.28/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // White wine
                alcPercent = 5 * 0.25/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink5.setOnClickListener(new View.OnClickListener() { // Rose
            @Override
            public void onClick(View v) {
                alcPercent = 5 * 0.24/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink6.setOnClickListener(new View.OnClickListener() { // Champagne
            @Override
            public void onClick(View v) {
                alcPercent = 5 * 0.24/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink7.setOnClickListener(new View.OnClickListener() { // Cider
            @Override
            public void onClick(View v) {
                alcPercent = 12 * 0.1/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink8.setOnClickListener(new View.OnClickListener() { // Smirnoff
            @Override
            public void onClick(View v) {
                alcPercent = 12 * 0.1/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink9.setOnClickListener(new View.OnClickListener() { // Whiskey
            @Override
            public void onClick(View v) {
                alcPercent = 1.5 * 0.8/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink10.setOnClickListener(new View.OnClickListener() { // Vodka
            @Override
            public void onClick(View v) {
                alcPercent = 1.5 * 0.8/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();

            }
        });
        drink11.setOnClickListener(new View.OnClickListener() { // Gin
            @Override
            public void onClick(View v) {
                alcPercent = 1.5 * 0.8/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();

            }
        });
        drink12.setOnClickListener(new View.OnClickListener() { // Rum
            @Override
            public void onClick(View v) {
                alcPercent = 1.5 * 0.8/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink13.setOnClickListener(new View.OnClickListener() { // Tequila
            @Override
            public void onClick(View v) {
                alcPercent = 1.5 * 0.8/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink14.setOnClickListener(new View.OnClickListener() { // Moonshine
            @Override
            public void onClick(View v) {
                alcPercent = 1.5 * 1.0/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink15.setOnClickListener(new View.OnClickListener() { // Malibu
            @Override
            public void onClick(View v) {
                alcPercent = 1.5 * 0.42/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink16.setOnClickListener(new View.OnClickListener() { // fireball
            @Override
            public void onClick(View v) {
                alcPercent = 1.5 * 0.66/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink17.setOnClickListener(new View.OnClickListener() { // bombay
            @Override
            public void onClick(View v) {
                alcPercent = 1.5 * 0.94/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
        drink18.setOnClickListener(new View.OnClickListener() {// captain morgan
            @Override
            public void onClick(View v) {
                alcPercent = 1.5 * 0.7/2;
                mainbac += calcBAC();
                calcTime();
                bac1.setText(String.format("%.2f", mainbac));
                counter++;
                drinks1.setText(Integer.toString(counter));
                saveStats();
            }
        });
    }

    public double calcBAC() {
        return ((alcPercent * (5.14/weight) * gender)); //(alcPercent / (((double)weight * 453.592) * gender));
    }

    public void calcTime() {
        time = limitHours = (mainbac - limit)/0.015;
        limitMinutes = ceil(60*(limitHours - floor(limitHours)));
        limitHours = floor(limitHours);
    }

    public double ifMale(String temp) {
        if (temp.equals("Male") || temp.equals("male")) {
            return 0.73;
        }
        else {
            return 0.66;
        }
    }

    private void loadProfile() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // SharedPreferences.Editor editor = sharedPreferences.edit();

        gender = ifMale(sharedPreferences.getString(GENDER,""));
        weight = sharedPreferences.getInt(WEIGHT,0);
        mainbac = (double) sharedPreferences.getFloat(BAC, 0);
        time = (double) sharedPreferences.getFloat(TIME, 0);
        // mainBAC.setText("0 %"); //Double.toString(calcBAC())
    }

    public boolean check() { // method checks if profile exists
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPreferences.contains(NAME) && sharedPreferences.contains(GENDER) && sharedPreferences.contains(WEIGHT)) return true;
        return false;
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


    public void saveStats() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);// getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putFloat(BAC, (float) mainbac);
        editor.putFloat(TIME, (float) time);

        editor.apply();

    }
}
