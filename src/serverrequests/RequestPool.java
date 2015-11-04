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
    
    private final Semaphore semaphore;
    
    public RequestPool(int s){
        this.size = s;
        this.semaphore = new Semaphore(s);
    }
    
}
