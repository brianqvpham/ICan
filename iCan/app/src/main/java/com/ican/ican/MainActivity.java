package com.ican.ican;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    public static boolean dataRange = false;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View fab = findViewById(R.id.newRecyclable);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("New Recyclable");
                dialog.setContentView(R.layout.maindialog);
                dialog.setCancelable(true);

                View confirm = dialog.findViewById(R.id.confirm);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int index = ((Spinner) dialog.findViewById(R.id.category)).getCount();
                        Recyclable recyclable = Recyclable.values()[index];
                        recyclable.daily++;
                        recyclable.alltime++;
                        //((RecyclerView) findViewById(R.id.stats)).getChildAt(index);
                    }
                });

                View cancel = dialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });

        View share = findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");

                share.putExtra(Intent.EXTRA_SUBJECT, "iCan do it!");
                share.putExtra(Intent.EXTRA_TEXT, "I helped the earth by doing a thing!");

                startActivity(Intent.createChooser(share, "Share status!"));
            }
        });

        RecyclerView stats = (RecyclerView) findViewById(R.id.stats);
        RecyclerView.LayoutManager statsLayoutManager = new LinearLayoutManager(this);
        stats.setLayoutManager(statsLayoutManager);

        RecyclerView.Adapter statsAdapter = new CardViewDataAdapter();
        stats.setAdapter(statsAdapter);

        final Switch range = (Switch) findViewById(R.id.dataRange);
        range.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataRange = range.isChecked();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
