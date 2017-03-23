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
        var connection: URLConnection?
        var response: InputStream
        val header: String
        for (i in 0..tries - 1) {
            //System.out.println("Sender " + id + " is trying to make connection no. " + i + " out of " + tries + " to " + url);
            try {
                connection = URL(url).openConnection()
                connection!!.setRequestProperty("Accept-Charset", charset)
                connection.addRequestProperty("User-Agent", "Mozilla/4.76")
                response = connection.getInputStream()
                if (connection == null) {
                    println("no responce!")
                }
            } catch (ex: MalformedURLException) {
                println("url $url is not a valid one.")
            } catch (ex: IOException) {
                println("sender $id, try: $i can not connect to $url")
                ex.printStackTrace()
            }

        }
        println("sender $id has finished")
    }
}
