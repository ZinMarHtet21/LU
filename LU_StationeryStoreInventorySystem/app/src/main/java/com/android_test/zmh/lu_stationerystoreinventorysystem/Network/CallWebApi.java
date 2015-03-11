package com.android_test.zmh.lu_stationerystoreinventorysystem.Network;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by student on 11/3/15.
 */
public class CallWebApi {

public void get(){

}

public void Put(String url,String jsonString){
    InputStream inputStream = null;
    String result = "";
    Log.d(url,url);
    Log.d("data",jsonString);
    try {

        // 1. create HttpClient
        HttpClient httpclient = new DefaultHttpClient();

        // 2. make POST request to the given URL
        HttpPut httpPut = new HttpPut(url);

        String json = "";

        // 3. build jsonObject
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.accumulate("name", person.getName());
//        jsonObject.accumulate("country", person.getCountry());
//        jsonObject.accumulate("twitter", person.getTwitter());

        // 4. convert JSONObject to JSON to String
        json = jsonString;

        // ** Alternative way to convert Person object to JSON string usin Jackson Lib
        // ObjectMapper mapper = new ObjectMapper();
        // json = mapper.writeValueAsString(person);

        // 5. set json to StringEntity
        StringEntity se = new StringEntity(json);

        // 6. set httpPut Entity
        httpPut.setEntity(se);

        // 7. Set some headers to inform server about the type of the content
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");

        // 8. Execute POST request to the given URL
        HttpResponse httpResponse = httpclient.execute(httpPut);

        // 9. receive response as inputStream
        inputStream = httpResponse.getEntity().getContent();

        // 10. convert inputstream to string
        if(inputStream != null)
            result = inputStream.toString();
        else
            result = "Did not work!";

        Log.d("result",result);
    } catch (Exception e) {
        Log.d("Updated successfully...", e.getLocalizedMessage());
    }

    // 11. return result


}

}
