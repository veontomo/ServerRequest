/*
 * Simulates simultaneous requests to a single resource
 */
package serverrequests

import java.util.concurrent.Semaphore

/**

 * @author Andrea
 */
class RequestPool(
        /**
         * max number of requests that the pool can execute simultaneously
         */
        private val size: Int) {
    /**
     * Url of the server to which the requests are to be sent
     */
    private var serverUrl: String? = null

    private val semaphore: Semaphore

    init {
        this.semaphore = Semaphore(size)
    }

    fun setServerUrl(url: String) {
        this.serverUrl = url
    }


}
