// Philline Dikker 12317608
// Second screen form mad libs app

package com.example.phill.phillinedikker_pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private EditText nextword;
    private TextView EText;
    private Button Ok;

    @Override
    protected void onCreate(@Nullable Bundle OutState) {
        super.onCreate(OutState);
        setContentView(R.layout.second_activity);

        tarzan = (CheckBox) findViewById(R.id.Tarzan);
        simple = (CheckBox) findViewById(R.id.Simple);
        uni = (CheckBox) findViewById(R.id.university);
        clothes = (CheckBox) findViewById(R.id.clothes);
        dance = (CheckBox) findViewById(R.id.dance);
        Progress = (TextView) findViewById(R.id.progress);
        nextword = (EditText) findViewById(R.id.input);
        EText = (TextView) findViewById(R.id.textView6);
        Ok = ( Button) findViewById(R.id.button2);


        // if something is already saved ( if checked or visible)
        if (OutState != null){

            // if was checked
            simple.setChecked(OutState.getBoolean("simple"));
            uni.setChecked(OutState.getBoolean("uni"));
            tarzan.setChecked(OutState.getBoolean("tarzan"));
            clothes.setChecked(OutState.getBoolean("clothes"));
            dance.setChecked(OutState.getBoolean("dance"));

            // get visibility
            simple.setVisibility(OutState.getInt("vsimple"));
            uni.setVisibility(OutState.getInt("vuni"));
            tarzan.setVisibility(OutState.getInt("vtarzan"));
            clothes.setVisibility(OutState.getInt("vclothes"));
            dance.setVisibility(OutState.getInt("vdance"));

            // get progress
            Progress.setText(OutState.getString("vProgress"));
            Progress.setVisibility(OutState.getInt("EProgress"));

            // get input edittext
            nextword.setText(OutState.getString("vinput"));
            nextword.setVisibility(OutState.getInt("next"));

            EText.setText(OutState.getString("texttext"));
            EText.setVisibility(OutState.getInt("EText"));
            Ok.setVisibility(OutState.getInt("ok"));

        }

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

            // other checkboxes set on invisible
            if (uni.getVisibility() == View.VISIBLE ) {
                uni.setVisibility(View.INVISIBLE);
                simple.setVisibility(View.INVISIBLE);
                clothes.setVisibility(View.INVISIBLE);
                dance.setVisibility(View.INVISIBLE);
            }
        }
        else if (simple.isChecked()) {
            Log.d("mainactivy", "SImpel ");
            InputStream stream  = this.getResources().openRawResource(R.raw.madlib0_simple);
            madestory = new Story(stream);

            // other checkboxes set on invisible
            if (uni.getVisibility() == View.VISIBLE ) {
                uni.setVisibility(View.INVISIBLE);
                tarzan.setVisibility(View.INVISIBLE);
                clothes.setVisibility(View.INVISIBLE);
                dance.setVisibility(View.INVISIBLE);
            }
        }
        else if (uni.isChecked()) {
            Log.d("mainactivy", "SImpel ");
            InputStream stream  = this.getResources().openRawResource(R.raw.madlib2_university);
            madestory = new Story(stream);
            if (tarzan.getVisibility() == View.VISIBLE ) {
                tarzan.setVisibility(View.INVISIBLE);
                simple.setVisibility(View.INVISIBLE);
                clothes.setVisibility(View.INVISIBLE);
                dance.setVisibility(View.INVISIBLE);
            }
        }
        else if (clothes.isChecked()) {
            Log.d("mainactivy", "SImpel ");
            InputStream stream  = this.getResources().openRawResource(R.raw.madlib3_clothes);
            madestory = new Story(stream);

            // other checkboxes set on invisible
            if (uni.getVisibility() == View.VISIBLE ) {
                uni.setVisibility(View.INVISIBLE);
                simple.setVisibility(View.INVISIBLE);
                tarzan.setVisibility(View.INVISIBLE);
                dance.setVisibility(View.INVISIBLE);
            }
        }
        else if (dance.isChecked()) {
            Log.d("mainactivy", "SImpel ");
            InputStream stream  = this.getResources().openRawResource(R.raw.madlib4_dance);
            madestory = new Story(stream);

            // other checkboxes set on invisible
            if (uni.getVisibility() == View.VISIBLE ) {
                uni.setVisibility(View.INVISIBLE);
                simple.setVisibility(View.INVISIBLE);
                clothes.setVisibility(View.INVISIBLE);
                tarzan.setVisibility(View.INVISIBLE);
            }
        }

        // other elements on screen visible
        Progress = findViewById(R.id.progress);
        Progress.setVisibility(View.VISIBLE);
        Progress.setText(String.valueOf(Story.getPlaceholderCount()));
        nextword = findViewById(R.id.input);
        nextword.setVisibility(View.VISIBLE);
        nextword.setHint(String.valueOf(Story.getNextPlaceholder()));
        Ok = findViewById(R.id.button2);
        Ok.setVisibility(View.VISIBLE);
        EText = findViewById(R.id.textView6);
        EText.setVisibility(View.VISIBLE);

    }

    public void TypedWord(View view){

        // get the word that typed in by user, fill in the next placeholder
        EditText nextword = findViewById(R.id.input);
        String word = nextword.getText().toString();
        Story.fillInPlaceholder(word);

        // make input empty
        nextword.setText("");

        // when all words are typed start activity: LastActivity.class
        if (Story.isFilledIn()){

            // go to next page: LastAcivity and pass the whole story
            Intent givestory = new Intent(this, LastActivity.class );
            givestory.putExtra("story", madestory.toString());
            startActivity(givestory);
            finish();
        }

        // when not all words are typed in get next, stay at second_activity.xml
        else{

            // update the progress
            Progress.setText(String.valueOf(Story.getPlaceholderRemainingCount()));

            // set hint in nextword
            nextword.setHint(String.valueOf(Story.getNextPlaceholder()));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // made story is saved in "story"
        outState.putSerializable("story", madestory);

        // save checkboxes "checks"
        outState.putBoolean("simple", simple.isChecked() );
        outState.putBoolean("tarzan", tarzan.isChecked() );
        outState.putBoolean("uni", uni.isChecked());
        outState.putBoolean("clothes", clothes.isChecked() );
        outState.putBoolean("dance", dance.isChecked() );

        // save checkboxes visibility
        outState.putInt("vsimple", simple.getVisibility());
        outState.putInt("vtarzan", tarzan.getVisibility());
        outState.putInt("vuni", uni.getVisibility());
        outState.putInt("vclothes", clothes.getVisibility());
        outState.putInt("vdance", dance.getVisibility());

        // save strings from the second elements of the screen
        outState.putString("vProgress",Progress.getText().toString());
        outState.putString("vinput", nextword.getText().toString());
        outState.putString("texttext", EText.getText().toString());

        // save visibility from the second elements of the screen
        outState.putInt("EText", EText.getVisibility());
        outState.putInt("Ok", Ok.getVisibility());
        outState.putInt("next", nextword.getVisibility());
        outState.putInt( "EProgres", Progress.getVisibility());

    }

    public void onRestoreInstanceState(Bundle inState){

        // save story that is chosen
        madestory = (Story) inState.getSerializable("story");
        super.onSaveInstanceState(inState);
    }
}
