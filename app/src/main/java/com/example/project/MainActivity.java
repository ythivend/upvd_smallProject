package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;

    private CardView mCard1, mCard2, mCard3, mCard4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mCard1 = (CardView) findViewById(R.id.card1);
        mCard2 = (CardView) findViewById(R.id.card2);
        mCard3 = (CardView) findViewById(R.id.card3);
        mCard4 = (CardView) findViewById(R.id.card4);

        mCard1.setOnClickListener(this);
        mCard2.setOnClickListener(this);
        mCard3.setOnClickListener(this);
        mCard4.setOnClickListener(this);

    }

    //create the top bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //event to move through the pages
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.card1 :
                i = new Intent(this, Activity1.class);
                startActivity(i);
                break;
            case R.id.card2 :
                i = new Intent(this, Activity2.class);
                startActivity(i);
                break;
            case R.id.card3 :
                i = new Intent(this, Activity3.class);
                startActivity(i);
                break;
            case R.id.card4 :
                i = new Intent(this, Activity4.class);
                startActivity(i);
                break;
        }
    }
}