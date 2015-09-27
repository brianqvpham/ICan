package com.ican.ican;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            FileInputStream fis = getActivity().openFileInput("data.json");
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            String json = new String(buffer, "UTF-8");

            JSONObject object = new JSONObject(json);
            long time = object.getLong("time");
            // TODO do a time thing
            // TODO separate thread and update the UI elements
            JSONArray daily = object.getJSONArray("daily");
            for (int i = 0; i < daily.length(); i++) {
                JSONObject dailyObject = (JSONObject) daily.get(i);
                int index = dailyObject.getInt("index");
                int numItems = dailyObject.getInt("numItems");
                Recyclable.values()[index].daily = numItems;
            }
            JSONArray all = object.getJSONArray("all");
            for (int i = 0; i < all.length(); i++) {
                JSONObject allObject = (JSONObject) all.get(i);
                int index = allObject.getInt("index");
                int numItems = allObject.getInt("numItems");
                Recyclable.values()[index].alltime = numItems;
            }
        } catch(IOException | JSONException e) {
        }
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
