package com.example.sharedpreferencestutorial;



import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    private Button mToolbar;
    private Button mRedColor;
    private Button mGreenColor;
    private Button mYellowColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar= (Button) findViewById(R.id.toolbar);
        mRedColor= (Button) findViewById(R.id.btnRed);
        mGreenColor= (Button) findViewById(R.id.btnGreen);
        mYellowColor= (Button) findViewById(R.id.btnYellow);

        // becuse we didnt use the defult Toolbar we need to det the title of the app in here
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mToolbar.setTitle(getResources().getString(R.string.app_name));
//        }


        //before the user is entered any value to the SP, this will check what is the current parameter
        //on the SP and will dispaly it on the app so that the user open it with the same chosen color
        // from the last time
        // so will check if the color inside the SP is not equal to the defult color
        if (getColor() != getResources().getColor(R.color.colorPrimary)){

            //set the color of the toolbar to the SP color
            mToolbar.setBackgroundColor(getColor());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getColor());
            }

        }

        mRedColor.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View v) {

                mToolbar.setBackgroundColor(getResources().getColor((R.color.colorRed)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(getResources().getColor((R.color.colorRed)));
                }
                storeColor(getResources().getColor((R.color.colorRed)));
            }
        });

        mGreenColor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                mToolbar.setBackgroundColor(getResources().getColor((R.color.colorGreen)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(getResources().getColor((R.color.colorGreen)));
                }
                storeColor(getResources().getColor((R.color.colorGreen)));
            }
        });

        mYellowColor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                mToolbar.setBackgroundColor(getResources().getColor((R.color.colorYellow)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(getResources().getColor((R.color.colorYellow)));
                }
                storeColor(getResources().getColor((R.color.colorYellow)));
            }
        });

    }

    //method to install the values (the colors) on the sheredpreferences file
    private void storeColor (int color){
        SharedPreferences mSharedPreferences = getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("Color", color);
        mEditor.apply();
    }

    // method to get the values from the SheredPreferences file
    private int getColor() {

        SharedPreferences mSharedPreferences = getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        int selectedColor = mSharedPreferences.getInt("Color", getResources().getColor(R.color.colorPrimary));
        return selectedColor;
    }


}
