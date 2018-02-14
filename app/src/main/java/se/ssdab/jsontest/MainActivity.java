package se.ssdab.jsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void main(String[] args) throws Exception {

        HttpURLConnectionExample http = new HttpURLConnectionExample();


        System.out.println("Testing 1 - Send Http GET request");
        //http.sendGet();


        //private void sendGet() throws Exception {

        String url = "https://www.hibouconnect.com/tapi/particleavg/1";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        final String USER_AGENT = "Chrome";
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("api-key", "1404e0b0b1a858a467505aa1a9a3a185");
        con.setRequestProperty("code", "083-083-068-244-113-212");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);

        if (responseCode == 200) {
            InputStream inputStr = con.getInputStream();
            String encoding = con.getContentEncoding() == null ? "UTF-8" : con.getContentEncoding();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            System.out.println("Body: " + response);
        }
    }
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
                    System.out.println("Hashmap: " + particle);

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
