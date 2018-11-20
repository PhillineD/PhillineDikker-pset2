// Philline Dikker 12317608
// Last screen form mad libs app

package com.example.phill.phillinedikker_pset2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LastActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_activity);

        // get the madestory en fill it in TextView Fstory
        TextView FStory = findViewById(R.id.FilledStory);
        FStory.setText(getIntent().getStringExtra("story"));

        }

        // when clicked on "Create another story"
        public void New(View view){
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);

        }
}
