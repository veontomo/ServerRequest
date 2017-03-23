package serverrequests

import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection

/**
 * A model corresponding to the controller
 */
class Model {
    fun start(baseUrl: String, totalRequests: Int, maxSimRequests: Int, requests: List<String>) {
        for (i in 1..maxSimRequests) {
//            Sender("sender $i").getContent(baseUrl, totalRequests)
            Sender("sender $i").putContent(baseUrl + requests[0], "{\"port\":1234}")
        }
        println("$baseUrl, $totalRequests, $maxSimRequests, ${requests.joinToString { it }}")
    }

    fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}