/*
 * Simulates simultaneous requests to a single resource
 */
package serverrequests;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Andrea
 */
public class RequestPool {
    /**
     * max number of requests that the pool can execute simultaneously
     */
    private final int size;
    /**
     * Url of the server to which the requests are to be sent
     */
    private String serverUrl;
    
    private final Semaphore semaphore;
    
    public RequestPool(int s){
        this.size = s;
        this.semaphore = new Semaphore(s);
    }
    
    public void setServerUrl(String url){
        this.serverUrl = url;
    }
    
    
    
    
}
