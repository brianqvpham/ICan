package com.ican.ican;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static boolean dataRange = false;
    public static Context context;

    public MainActivity() {
        context = this;
    }

    public static void save() {
        // TODO multithread???
        try {
            FileOutputStream fos = context.openFileOutput("data.json", Context.MODE_PRIVATE);
            JSONObject object = new JSONObject();
            object.put("time", new Date().getTime());
            JSONArray daily = new JSONArray();
            for (int i = 0; i < Recyclable.values().length; i++) {
                JSONObject dailyObject = new JSONObject();
                dailyObject.put("numItems", Recyclable.values()[i].daily);
                dailyObject.put("index", i);
                daily.put(dailyObject);
            }
            JSONArray all = new JSONArray();
            for (int i = 0; i < Recyclable.values().length; i++) {
                JSONObject allObject = new JSONObject();
                allObject.put("numItems", Recyclable.values()[i].alltime);
                allObject.put("index", i);
                all.put(allObject);
            }
            object.put("daily", daily);
            object.put("all", all);
            fos.write(object.toString().getBytes());
            fos.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

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

                Spinner spinner = (Spinner) dialog.findViewById(R.id.category);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.Recyclables, android.R.layout.simple_spinner_item);
                spinner.setAdapter(adapter);

                View confirm = dialog.findViewById(R.id.confirm);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int index = ((Spinner) dialog.findViewById(R.id.category)).getSelectedItemPosition();
                        Recyclable recyclable = Recyclable.values()[index];
                        int numItems = 0;
                        try {
                            numItems = Integer.parseInt(((EditText) dialog.findViewById(R.id.numItems)).getText().toString());
                        } catch (NumberFormatException ignored) {
                        }
                        recyclable.daily += numItems;
                        recyclable.alltime += numItems;
                        ((TextView) ((RecyclerView) findViewById(R.id.stats)).getChildAt(index).findViewById(R.id.count)).setText("" + (MainActivity.dataRange ? Recyclable.values()[index].alltime : Recyclable.values()[index].daily));
                        dialog.cancel();

                        save();
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
                for(int i = 0; i < ((RecyclerView) findViewById(R.id.stats)).getChildCount(); i++) {
                    ((TextView) ((RecyclerView) findViewById(R.id.stats)).getChildAt(i).findViewById(R.id.count)).setText("" + (dataRange ? Recyclable.values()[i].alltime : Recyclable.values()[i].daily));
                }
            }
        });
    }
}
