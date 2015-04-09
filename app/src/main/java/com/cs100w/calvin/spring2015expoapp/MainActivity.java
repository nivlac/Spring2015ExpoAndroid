package com.cs100w.calvin.spring2015expoapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The activity that opens when the app opens. Starts the tour for the user.
 */
public class MainActivity extends Activity {

    private String dText;
    private final int  NUM_OF_PROJECTS = 2; //Number of projects in the expo.

    /**
     * Set the layout for the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startTourButton = (Button) findViewById(R.id.startTourButton);
    }

    /**
     * Starts the tour when the start tour button is pressed.
     * Dialog box opens asking for the project ID number.
     * @param view
     */
    public void startTour(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Team Number");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dText = input.getText().toString();
                //If the number is within the number of projects, start the info activity.
                if(Integer.parseInt(dText) <= NUM_OF_PROJECTS && Integer.parseInt(dText) > 0){
                    Intent intent = new Intent(MainActivity.this, TeamInfoActivity.class);
                    intent.putExtra("project_id", dText);
                    startActivity(intent);
                }
                //Create error dialog asking user to enter valid project ID.
                else{
                    Toast.makeText(MainActivity.this, "Enter a number 0 - " + NUM_OF_PROJECTS, Toast.LENGTH_SHORT);
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        //Show dialog box.
        builder.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
    //Hi Debra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_favorites) {
            SharedPreferences getFavorites = getSharedPreferences("FAVORITES", Context.MODE_PRIVATE);
            if(getFavorites.getStringSet("key", null) == null){
                Toast.makeText(this, "No Favorites!", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(this, FavoritesActivity.class);
                startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
