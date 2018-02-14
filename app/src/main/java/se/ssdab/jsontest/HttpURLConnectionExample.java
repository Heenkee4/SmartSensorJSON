package se.ssdab.jsontest;

import android.nfc.Tag;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Henrik on 2018-02-14.
 */

public class HttpURLConnectionExample {
    private final String USER_AGENT = "Chrome";
    private final String API_KEY = "api-key: 1404e0b0b1a858a467505aa1a9a3a185";
    private final String API_CODE = "code: 083-083-068-244-113-212";
    String jsonResponse = null;


    public HttpURLConnectionExample() throws JSONException {
    }

    public static void main(String[] args) throws Exception {

        HttpURLConnectionExample http = new HttpURLConnectionExample();

        //System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();
    }

        /*System.out.println("\nTesting 2 - Send Http POST request");
        http.sendPost();*/


    // HTTP GET request
    public void sendGet() throws Exception {

        String url = "https://www.hibouconnect.com/tapi/temperature/1";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

       /* try {
            InputStream inputStream = new BufferedInputStream(con.getInputStream());
        }catch ()*/

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        //con.getHeaderField(1);
        con.setRequestProperty("api-key", "1404e0b0b1a858a467505aa1a9a3a185");
        con.setRequestProperty("code", "083-083-068-244-113-212");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response code: " + responseCode);

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
}
