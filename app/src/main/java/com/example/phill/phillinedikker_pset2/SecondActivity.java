package com.example.phill.phillinedikker_pset2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;

public class SecondActivity extends AppCompatActivity {

    TextView Progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the story
        InputStream stream  = this.getResources().openRawResource(R.raw.madlib1_tarzan.txt);
        Story madestory = new Story(stream);

        // Update the user the progress
        Progress = findViewById(R.id.progress);
        Progress.setText(Story.getPlaceholderCount());

       //
    }

    public void TypedWord(View view){

        // get the word that typed in by user, fill in the next placeholder
        EditText nextword = findViewById(R.id.input);
        Story.fillInPlaceHoldere(nextword);

        // when all words are typed
        if (Story.isFilledIn()){
            // go to next page 

        }
        // when not all words are typed in get next
        else{

            // update the progress

            // set word in story





        }



    }
}
