package serverrequests

import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection

/**
 * Perform a series of GET requests one after another
 * @param url address to which the requests are to be performed
 * @param tries number of the requests. Are to be executed consequently.
 * @param id marker
 */

class RequestSenderGet(private val url: String, private val tries: Int, private val id: String) : Runnable {
    private val charset = "UTF-8"

    override fun run() {
    }
}
