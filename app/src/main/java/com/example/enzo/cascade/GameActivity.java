package com.example.enzo.cascade;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Iterator;
import java.util.List;

public class GameActivity extends Activity {

    TextView scoreView;
    TextView bestScore;
    GridView gridView;
    float difficulty;
    ImageAdapter imageAdapter;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    int nbBreaked=0;
    int score=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sharedPref = this.getSharedPreferences("scores",Context.MODE_PRIVATE);

        editor = sharedPref.edit();

        scoreView = (TextView) findViewById(R.id.Score);
        bestScore = (TextView) findViewById(R.id.bestScore);
        gridView = (GridView) findViewById(R.id.gridViewer);



        Intent intention = getIntent();
        Bundle parameters = intention.getExtras();
        imageAdapter = new ImageAdapter(this);


        difficulty = (float) parameters.get("difficulty");
        gridView.setNumColumns((int) (difficulty * 5));

        imageAdapter.setCount(((int) difficulty * 5) * ((int) difficulty * 5));

        imageAdapter.fillList();

        if(sharedPref.contains(""+difficulty)) {
            bestScore.setText(""+sharedPref.getInt(""+difficulty, 0));
        }
        else{editor.putInt(""+difficulty,0);
        editor.apply();}

        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                System.out.println(i);
                nbBreaked = 0;
                checkNeigh(i, true);
                for(int j=0;j<(int)difficulty*5;j++)
                {
                    dropDown();
                    dropLeft();
                }
                refreshScore();
            if(isFinished())
            {
                Context context = getApplicationContext();
                CharSequence text = "Partie terminée !";
                int duration = Toast.LENGTH_LONG;
                if(sharedPref.contains(""+difficulty)) {
                    if (score > sharedPref.getInt(""+difficulty, 0)) {
                        bestScore.setText(""+score);

                        editor.putInt(""+difficulty, score);
                        editor.apply();

                        System.out.println("diff"+difficulty);
                    }
                }
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            }

        });
    }





    void refreshScore()
    {
        score += nbBreaked * nbBreaked;
        nbBreaked = 0;
        scoreView.setText(""+score);
    }

    boolean isFinished()
    {
        boolean finished = true;

        for (int i=0; i<imageAdapter.getCount()+1;i++)
        {
            try{
            if(imageAdapter.cellList[i]!=imageAdapter.mThumbIds[0])
            {
                try {
                    if (imageAdapter.cellList[i].equals(imageAdapter.cellList[i - 1])&&finished) {
                        if (i % (5 * difficulty) != 0) {
                            finished = false;
                        }
                    }
                }
                catch (Exception e){}
                try {
                if(imageAdapter.cellList[i].equals(imageAdapter.cellList[i+1])&&finished)
                {
                    if((i % (5 * difficulty) != 5 * difficulty - 1)){

                        finished = false;
                }}}
                catch (Exception e){}
                try {
                    if(imageAdapter.cellList[i].equals(imageAdapter.cellList[i + (5 * (int) difficulty)])&&finished)
                {
                    finished = false;}
                }
                catch (Exception e){}
                try {
                    if(imageAdapter.cellList[i].equals((imageAdapter.cellList[i - (5 * (int) difficulty)]))&&finished)
                {
                    finished  =false;
                }
                }
                catch (Exception e){}
            }

        }
            catch(Exception e)
            {

            }}

        return finished;
    }

    void dropDown()
    {
       for(int i =(int)difficulty*5; i<gridView.getCount();i++)
       {
           try {
               if (imageAdapter.cellList[i].equals(imageAdapter.mThumbIds[0])) {
                   Integer tmp = imageAdapter.cellList[i - (5 * (int) difficulty)];
                   imageAdapter.cellList[i-(5 * (int)difficulty)] = imageAdapter.mThumbIds[0];
                   imageAdapter.cellList[i]  = tmp;
                   System.out.println("rec");
               }
           }
           catch (Exception e){}
       }
    }
    void dropLeft()
    {
        int last = (int)difficulty*(int)difficulty*25;
        for(int i =last-2; i > last - gridView.getNumColumns()-1;i--)
        {
           if(imageAdapter.cellList[i].equals(imageAdapter.mThumbIds[0]))
           {
               for(int j=0;j<gridView.getNumColumns();j++)
               {
                   imageAdapter.cellList[i-(j*((int)difficulty*5))]=imageAdapter.cellList[i-(j*((int)difficulty*5))+1];
                   imageAdapter.cellList[i-(j*((int)difficulty*5))+1]=imageAdapter.mThumbIds[0];
               }
           }
        }
    }
                //while()



    void checkNeigh(int position, boolean first) {
        Integer current = imageAdapter.cellList[position];
        //Sur la gauche
        try {
            if (imageAdapter.cellList[position - 1].equals(current)
                    && imageAdapter.cellList[position - 1] != imageAdapter.mThumbIds[0]) {
                if (position % (5 * difficulty) != 0) {
                    imageAdapter.cellList[position] = imageAdapter.mThumbIds[0];
                    gridView.setAdapter(imageAdapter);
                    nbBreaked++;
                    checkNeigh(position - 1, false);
                    System.out.println("Same gauche");
                }
            }
        } catch (Exception e) { }
        //Sur la droite
        try {
            if (imageAdapter.cellList[position + 1].equals(current)
                    && imageAdapter.cellList[position + 1] != imageAdapter.mThumbIds[0]) {

                if (position % (5 * difficulty) != 5 * difficulty - 1) {
                    imageAdapter.cellList[position] = imageAdapter.mThumbIds[0];
                    gridView.setAdapter(imageAdapter);
                    nbBreaked++;
                    checkNeigh(position + 1, false);
                    System.out.println("Same droite");
                }
            }
        } catch (Exception e) {
        }
        //Sur le haut
        try {
            if (imageAdapter.cellList[position - (5 * (int) difficulty)].equals(current)
                    && imageAdapter.cellList[position - (5 * (int) difficulty)] != imageAdapter.mThumbIds[0]) {
                System.out.println("Same haut");
                imageAdapter.cellList[position] = imageAdapter.mThumbIds[0];
                gridView.setAdapter(imageAdapter);
                nbBreaked++;
                checkNeigh(position - (5 * (int) difficulty), false);
            }
        } catch (Exception e) {
        }
        //Sur le bas
        try {
            if (imageAdapter.cellList[position + (5 * (int) difficulty)].equals(current)
                    && imageAdapter.cellList[position + (5 * (int) difficulty)] != imageAdapter.mThumbIds[0]) {
                imageAdapter.cellList[position] = imageAdapter.mThumbIds[0];
                gridView.setAdapter(imageAdapter);
                nbBreaked++;
                checkNeigh(position + (5 * (int) difficulty), false);
                System.out.println("Same bas");
            }
        } catch (Exception e) {
        }
        if (!first) {
            imageAdapter.cellList[position] = imageAdapter.mThumbIds[0];
            gridView.setAdapter(imageAdapter);
        }
    }

    public static Integer[] convertIntegers(List<Integer> integers)
    {
        Integer[] ret = new Integer[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }
}
