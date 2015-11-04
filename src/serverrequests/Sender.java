/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrequests;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
class Sender {

    private static int counter = 0;

    private final String charset = "UTF-8";
    private int senderId;

    public Sender() {
        this.senderId = counter;
        counter++;
    }

    public void get(String url, int tries) {
        RequestSender r = new RequestSender(url, tries, senderId);
        Thread t = new Thread(r);
        t.start();
    }

    public void put(String address, String data) {
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setRequestProperty("Content-Length", ""
                    + Integer.toString(data.getBytes().length));
            connection.setRequestProperty("Content-Language", charset);

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();

            //Get Response	
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            System.out.println(response.toString());

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

    }
    
    /**
     * Converts hash map into a string
     * @param map  string to string map
     * @return 
     */
    public static String hashSerialize(HashMap<String, String> map){
        String str = "{";
        for (HashMap.Entry<String, String> entry : map.entrySet()) {
            str += "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\",";
        }
        str = str.substring(0, str.length() - 1) + "}";
        return str;
    }

    

    class RequestSender implements Runnable {

        private final String url;
        private final int tries;
        private final int senderId;

        public RequestSender(String url, int tries, int id) {
            this.senderId = id;
            this.url = url;
            this.tries = tries;
        }

        @Override
        public void run() {
            URLConnection connection;
            InputStream response;
            String header;
            for (int i = 0; i < tries; i++) {
                System.out.println("Sender " + senderId + " is trying to make connection no. " + i + " out of " + tries + " to " + url);
                try {
                    connection = new URL(url).openConnection();
                    connection.setRequestProperty("Accept-Charset", charset);
                    connection.addRequestProperty("User-Agent", "Mozilla/4.76");
                    response = connection.getInputStream();
                    if (connection == null) {
                        System.out.println("no responce!");
                    }
                } catch (MalformedURLException ex) {
                    System.out.println("url " + url + " is not a valid one.");
                } catch (IOException ex) {
                    System.out.println("sender " + senderId + ", try: " + i + " can not connect to " + url);
                    ex.printStackTrace();
                }

            }
            System.out.println("sender " + senderId + " has finished");
        }
    };
}
