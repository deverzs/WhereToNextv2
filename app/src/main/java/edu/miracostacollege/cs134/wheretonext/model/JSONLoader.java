package edu.miracostacollege.cs134.wheretonext.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class loads College data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (College) with data.
 */
public class   JSONLoader {

    /**
     * Loads JSON data from a file in the assets directory.
     *
     * @param context The activity from which the data is loaded.
     * @throws IOException If there is an error reading from the JSON file.
     */
    public static List<College> loadJSONFromAsset(Context context) throws IOException {
        List<College> allCollegesList = new ArrayList<>();
        String json = null;
        InputStream is = context.getAssets().open("colleges.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");

        try {
            JSONObject jsonRootObject = new JSONObject(json);

            JSONArray allCollegesArray = jsonRootObject.getJSONArray("colleges");
            int numberOfColleges = allCollegesArray.length();


            for (int i = 0; i < numberOfColleges; i++) {
                JSONObject eventJson = allCollegesArray.getJSONObject(i);
                College event = new College();
                event.setImageName(eventJson.getString("FileName"));
                event.setName(eventJson.getString("Name"));
                event.setRating(eventJson.getDouble("Rating"));
                event.setPopulation(eventJson.getInt("Population"));
                event.setTuition(eventJson.getDouble("Tuition"));

                allCollegesList.add(event);
            }

             // DONE: Loop through all the colleges in the JSON data, create a College object for each
             // DONE: Add each college object to the list



        } catch (JSONException e) {
            Log.e("WhereToNext", e.getMessage());
        }

        return allCollegesList;
    }
}
