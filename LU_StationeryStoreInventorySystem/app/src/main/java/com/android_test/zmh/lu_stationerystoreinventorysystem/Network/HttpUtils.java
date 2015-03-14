package com.android_test.zmh.lu_stationerystoreinventorysystem.Network;

/**
 * Created by student on 12/3/15.
 */

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class HttpUtils {
    public HttpUtils(){}
    public static String sendPostMethod(String path,Map<String, Object>params, String encoding){

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(path);

        String result = "";
        List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        try {
        if(params !=null&& !params.isEmpty()){
            for(Map.Entry<String,Object> entry: params.entrySet()){
                String name = entry.getKey();
                String value = entry.getValue().toString();
                BasicNameValuePair valuePair = new BasicNameValuePair( name,value);
                parameters.add(valuePair);
            }
        }

            /*urlEncodedFormEntity encodedFormEntity = new urlEncodedFormEntity
                   (parameters, encoding);
            httpPost.setEntity(encodedFormEntity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                String result = EntityUtils.
            }
*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

}
