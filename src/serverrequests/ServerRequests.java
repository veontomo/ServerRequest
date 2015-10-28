/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrequests;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Andrea
 * http://stackoverflow.com/questions/2793150/using-java-net-urlconnection-to-fire-and-handle-http-requests
 */
public class ServerRequests {

    //private static final String url = "http://2.228.14.114";
    private static final String url = "http://192.168.5.28:3000/views";
    private static final String charset = "UTF-8";
    private static final int ATTEMPTS = 1;
    private static final int USERS = 3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException {

        Sender s;

        String param2, query;
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "http://www.venditori.it");
        map.put("2", "http://www.rappresentanti.it");
        map.put("3", "http://www.soluzioneagenti.it");
        map.put("4", "http://www.cercoagenti.it");
        int poolSize = map.size();
        String[] pool = map.keySet().toArray(new String[poolSize]);
        // curl -H "Content-Type: application/json" -X POST -d "{\"1\":\"http://www.venditori.it\",\"2\":\"http://www.rappresentanti.it\"}" http://192.168.5.28:3000/views/routes/load
        // curl -H "Content-Type: application/json" -X POST -d "{\"1\":\"http://localhost:3000\/views\/local\"}" http://192.168.5.28:3000/views/routes/load
        
        //s.put("http:\/\/192.168.5.28:3000\/views\/routes\/load", "{\"1\":\"http://localhost:3000\/views\/local\"}");

        s = new Sender();
        String jsonData = "{";
        for (HashMap.Entry<String, String> entry : map.entrySet()){
            jsonData += "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\",";
        }
        jsonData = jsonData.substring(0, jsonData.length()-1) + "}";
        s.put(url + "/routes/load", jsonData);
        
        
        for (int i = 0; i < USERS; i++) {
            param2 = pool[i % poolSize];
            query = String.format("/idem/%s", URLEncoder.encode(param2, charset));

            s = new Sender();
            s.get(url + query, ATTEMPTS);
        }

//        String param1 = "value1";
//        String param2 = "value2";
//        String query = String.format("param1=%s&param2=%s",
//                URLEncoder.encode(param1, charset),
//                URLEncoder.encode(param2, charset));
//        URLConnection connection = new URL(url + "?" + query).openConnection();
//        connection.setRequestProperty("Accept-Charset", charset);
//        InputStream response = connection.getInputStream();
//        for (Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
//            System.out.println(header.getKey() + "=" + header.getValue());
//        }
    }

}
