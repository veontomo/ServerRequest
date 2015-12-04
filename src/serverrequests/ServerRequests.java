/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrequests;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 *
 * @author Andrea
 * http://stackoverflow.com/questions/2793150/using-java-net-urlconnection-to-fire-and-handle-http-requests
 */
public class ServerRequest {

    private static final String url = "http://192.168.5.28/views";
    private static final String charset = "UTF-8";
    private static final int ATTEMPTS = 15;
    private static final int USERS = 10;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException {

        Sender s;

        String param2, query;
        HashMap<String, String> paths = new HashMap<>();
        HashMap<String, String> auth = new HashMap<>();
        HashMap<String, HashMap<String, String>> data = new HashMap<>();
        
        paths.put("1", "http://www.venditori.it");
        paths.put("2", "http://www.rappresentanti.it");
        paths.put("3", "http://www.soluzioneagenti.it");
        paths.put("4", "http://www.cercoagenti.it");
        
        auth.put("login", "Sun");
        auth.put("password", "shine");
        
        data.put("auth", auth);
        data.put("paths", paths);
        
        int poolSize = paths.size();
        String[] pool = paths.keySet().toArray(new String[poolSize]);
        // curl -H "Content-Type: application/json" -X POST -d "{\"1\":\"http://www.venditori.it\",\"2\":\"http://www.rappresentanti.it\"}" http://192.168.5.28:3000/views/routes/load
        // curl -H "Content-Type: application/json" -X POST -d "{\"1\":\"http://localhost:3000\/views\/local\"}" http://192.168.5.28:3000/views/routes/load
        
        //s.put("http:\/\/192.168.5.28:3000\/views\/routes\/load", "{\"1\":\"http://localhost:3000\/views\/local\"}");

        s = new Sender();
        s.put(url + "/routes/load", Sender.hashSerialize(data));
        System.out.println(Sender.hashSerialize(data));
        
        for (int i = 0; i < USERS; i++) {
            param2 = pool[i % poolSize];
            query = String.format("/idem/%s", URLEncoder.encode(param2, charset));

            s = new Sender();
            
            s.get(url + query, ATTEMPTS);
        }


    }

}
