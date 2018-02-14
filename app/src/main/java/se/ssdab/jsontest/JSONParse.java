package se.ssdab.jsontest;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by Henrik on 2018-02-13.
 */

public class JSONParse {
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();
        String url = "https://www.hibouconnect.com/tapi/particleavg/1";

        String jsonString = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonString);
        System.out.println("jsonstring" + jsonString.toString());

        if (jsonString != null) {
            try {
                jsonString = "{ \"particles\":[" + jsonString + "] }";

                JSONObject jsonObject = new JSONObject(jsonString);

                JSONArray particles = jsonObject.getJSONArray("particles");

                for (int i = 0; i < particles.length(); i++) {
                    JSONObject c = particles.getJSONObject(i);

                    String id = c.getString("id");
                    String sensorid = c.getString("sensorid");
                    String pm1 = c.getString("pm1");
                    String pm25 = c.getString("pm25");
                    String pm10 = c.getString("pm10");
                    String timestamp = c.getString("timestamp");

                    HashMap<String, String> particle = new HashMap<>();
                    particle.put("id", id);
                    particle.put("sensorid", sensorid);
                    particle.put("pm1", pm1);
                    particle.put("pm25", pm25);
                    particle.put("pm10", pm10);
                    particle.put("timestamp", timestamp);

                    Log.i("TAG Id", ":" + id);
                    Log.i("TAG sensorid", ":" + sensorid);
                    Log.i("TAG pm1", ":" + pm1);
                    Log.i("TAG pm25", ":" + pm25);
                    Log.i("TAG pm10", ":" + pm10);
                    Log.i("TAG timestamp", ":" + timestamp);


                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json Parsing error: " + e.getMessage());
            }

        } return null;
    }
}

