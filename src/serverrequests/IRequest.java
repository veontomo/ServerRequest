/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrequests;

import java.net.URL;

/**
 *
 * @author Andrea
 */
public interface IRequest {
    /**
     * Method by means of which the request is to be sent (POST, PUT, GET etc)
     * @param s 
     */
    void setMethod(String s);
    
    String getMethod();
    /**
     * Set url where the request should be sent
     * @param url 
     */
    void setUrl(URL url);
    
    URL getUrl();

    /**
     * Performs the request
     */
    void execute();
    
    /**
     * Server responce to the request
     * @return 
     */
    String responce();
}
