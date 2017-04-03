package serverrequests
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection

/**
 * An imumtable data type corresponding to a GET http request
 */
class Get(val target: String) : Request {
    private val charset = "UTF-8"
    override fun perform(): Report {
        val connection: URLConnection?
        val response: InputStream
        val header: String
        try {
            connection = URL(target).openConnection()
            connection!!.setRequestProperty("Accept-Charset", charset)
            connection.addRequestProperty("User-Agent", "Mozilla/4.76")
            response = connection.getInputStream()
            if (connection == null) {
                println("no responce!")
            }
            return Report(true, "")
        } catch (ex: MalformedURLException) {
            return Report(false, "invalid url $target: ${ex.message}")
        } catch (ex: IOException) {
            return Report(false, "Failed to connect to $target: ${ex.message}")
        }

    }
}

