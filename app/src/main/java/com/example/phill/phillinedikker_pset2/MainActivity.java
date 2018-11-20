// Philline Dikker 12317608
// First screen form mad libs app

package com.example.phill.phillinedikker_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // to go to Secondactivity class when clicked on button "let's start"
    public void Nextpage(View view){
        Intent j= new Intent(this, SecondActivity.class );
        startActivity(j);
     }

}