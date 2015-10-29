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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 * http://stackoverflow.com/questions/2793150/using-java-net-urlconnection-to-fire-and-handle-http-requests
 */
public class ServerRequests {

    //private static final String url = "http://2.228.14.114";
    private static final String url = "http://192.168.5.28:8080/views";
    private static final String charset = "UTF-8";
    private static final int ATTEMPTS = 20;
    private static final int USERS = 100;

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
        s.put(url + "/routes/load", map);
        
        
        for (int i = 0; i < USERS; i++) {
            param2 = pool[i % poolSize];
            query = String.format("/idem/%s", URLEncoder.encode(param2, charset));

            s = new Sender();
            s.get(url + query, ATTEMPTS);
        }


    }

}
