package com.example.enzo.cascade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class MainActivity extends Activity {


    RatingBar diffSelector;
    Button playBtn;
    Button scrBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = (Button) findViewById(R.id.playBtn);
        scrBtn = (Button) findViewById(R.id.scrBtn);
        diffSelector = (RatingBar) findViewById(R.id.diffSelector);

        final Intent goToGame = new Intent(this, GameActivity.class);
        final Intent goToScr = new Intent(this, ScoreActivity.class);


        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGame.putExtra("difficulty",diffSelector.getRating());
                startActivity(goToGame);
            }
        });

        scrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(goToScr);
            }
        });
    }
}
