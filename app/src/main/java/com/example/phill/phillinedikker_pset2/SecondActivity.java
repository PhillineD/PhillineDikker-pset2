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
    private CheckBox simple;
    private CheckBox tarzan;
    private CheckBox uni;
    private CheckBox dance;
    private CheckBox clothes;
    @Override
    protected void onCreate(@Nullable Bundle OutState) {
        super.onCreate(OutState);
        setContentView(R.layout.second_activity);

        tarzan = (CheckBox) findViewById(R.id.Tarzan);
        simple =  findViewById(R.id.Simple);
        uni = (CheckBox) findViewById(R.id.university);
        clothes = (CheckBox) findViewById(R.id.clothes);
        dance = (CheckBox) findViewById(R.id.dance);
//        Boolean simplecheck = simple.isChecked();
//        Boolean tarzancheck = tarzan.isChecked();
//        Boolean unicheck = uni.isChecked();
//        Boolean clothescheck = clothes.isChecked();
//        Boolean dancecheck= dance.isChecked();


        // if something is already saved
        if (OutState != null){
            simple.setChecked(OutState.getBoolean("simple"));
            uni.setChecked(OutState.getBoolean("uni"));
            tarzan.setChecked(OutState.getBoolean("tarzan"));
            clothes.setChecked(OutState.getBoolean("clothes"));
            dance.setChecked(OutState.getBoolean("dance"));
        }
        // Update the user the progress
        Progress = findViewById(R.id.progress);
        Progress.setText("Words left..");
    }

    public void PickStory(View view) {
        CheckBox tarzan = (CheckBox) findViewById(R.id.Tarzan);
        CheckBox simple = (CheckBox) findViewById(R.id.Simple);
        CheckBox uni = (CheckBox) findViewById(R.id.university);
        CheckBox clothes = (CheckBox) findViewById(R.id.clothes);
        CheckBox dance = (CheckBox) findViewById(R.id.dance);

        // The user should be allowed to pick one of the stories before starting.
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
        else if (uni.isChecked()) {
            Log.d("mainactivy", "SImpel ");
            InputStream stream  = this.getResources().openRawResource(R.raw.madlib2_university);
            madestory = new Story(stream);
        }
        else if (clothes.isChecked()) {
            Log.d("mainactivy", "SImpel ");
            InputStream stream  = this.getResources().openRawResource(R.raw.madlib3_clothes);
            madestory = new Story(stream);
        }
        else if (dance.isChecked()) {
            Log.d("mainactivy", "SImpel ");
            InputStream stream  = this.getResources().openRawResource(R.raw.madlib4_dance);
            madestory = new Story(stream);
        }

        Progress = findViewById(R.id.progress);
        Progress.setText(String.valueOf(Story.getPlaceholderCount()));

    }

    public void TypedWord(View view){


        // get the word that typed in by user, fill in the next placeholder
        EditText nextword = findViewById(R.id.input);
        String word = nextword.getText().toString();
        Story.fillInPlaceholder(word);

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
        outState.putBoolean("simple", simple.isChecked() );
        outState.putBoolean("tarzan", tarzan.isChecked() );
        outState.putBoolean("uni", uni.isChecked() );
        outState.putBoolean("clothes", clothes.isChecked() );
        outState.putBoolean("dance", dance.isChecked() );
    }
}
