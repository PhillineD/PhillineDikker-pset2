package com.example.phill.phillinedikker_pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.LayoutInflater;
import java.io.InputStream;

public class SecondActivity extends AppCompatActivity {

    public TextView Progress;
    public Story madestory;

    @Override
    protected void onCreate(@Nullable Bundle OutState) {
        super.onCreate(OutState);
        setContentView(R.layout.second_activity);

        // Get the story - Tarzan
//        CheckBox tarzan = (CheckBox) findViewById(R.id.Tarzan);
//        if (tarzan.isChecked()){
        InputStream stream  = this.getResources().openRawResource(R.raw.madlib1_tarzan);
        madestory = new Story(stream);
//        }

//
        // Update the user the progress
        Progress = findViewById(R.id.progress);
        Progress.setText(String.valueOf(Story.getPlaceholderCount()));
    }

    public void PickStory(View view) {
        CheckBox tarzan = (CheckBox) findViewById(R.id.Tarzan);
        CheckBox simple = (CheckBox) findViewById(R.id.Simple);
        if (tarzan.isChecked()) {
            Log.d("mainactivy", "PickStory: ");
            InputStream stream  = this.getResources().openRawResource(R.raw.madlib1_tarzan);
            madestory = new Story(stream);
        }
        else if (simple.isChecked()) {
            Log.d("mainactivy", "SImpel ");
            InputStream stream  = this.getResources().openRawResource(R.raw.madlib0_simple);
            madestory = new Story(stream);
        }
    }

    public void TypedWord(View view){

//        InputStream stream  = this.getResources().openRawResource(R.raw.madlib1_tarzan);
//        madestory = new Story(stream);
        // get the word that typed in by user, fill in the next placeholder
        EditText nextword = findViewById(R.id.input);
        String word = nextword.getText().toString();
//        Story.fillInPlaceholder(String.valueOf(nextword));
//        Story.fillInPlaceholder("Philline");
        Story.fillInPlaceholder(word);

//        CheckBox tarzan = (CheckBox) findViewById(R.id.Tarzan);
//        if (tarzan.isChecked()){
//            InputStream stream  = this.getResources().openRawResource(R.raw.madlib0_simple);
//            madestory = new Story(stream);
//        }


        // when all words are typed start activity: LastActivity.class
        if (Story.isFilledIn()){

            // go to next page: LastAcivity and pass the whole story
            Intent givestory = new Intent(this, LastActivity.class );
            givestory.putExtra("story",madestory.toString());
            startActivity(givestory);
            finish();
        }

        // when not all words are typed in get next, stay at second_activity.xml
        else{

            // update the progress
            Progress.setText(String.valueOf(Story.getPlaceholderRemainingCount()));

            // set word in story
            nextword.setText(String.valueOf(Story.getNextPlaceholder()));
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // made story is saved in "story"
        outState.putSerializable("story", madestory);
    }
}
