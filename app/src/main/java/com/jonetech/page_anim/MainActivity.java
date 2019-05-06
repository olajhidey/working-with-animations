package com.jonetech.page_anim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView redBlock = findViewById(R.id.redBlock);
        ImageView blueBlock = findViewById(R.id.blueBlock);
        ImageView androidImage  = findViewById(R.id.androidBlock);
        ImageView pinkBlock = findViewById(R.id.pinkBlock);

        // set baseclass method to appopriate blocks
        explodeTransition(this, redBlock);
        fadeTransition(this,blueBlock);
        rotateTransition(pinkBlock);
        switchAnimations(androidImage, blueBlock, new Intent(this, SecondActivity.class), this);
    }
}
