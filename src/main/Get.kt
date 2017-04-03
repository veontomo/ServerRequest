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
    override fun perform() {
        var connection: URLConnection?
        var response: InputStream
        val header: String
        try {
            connection = URL(target).openConnection()
            connection!!.setRequestProperty("Accept-Charset", charset)
            connection.addRequestProperty("User-Agent", "Mozilla/4.76")
            response = connection.getInputStream()
            if (connection == null) {
                println("no responce!")
            }
        } catch (ex: MalformedURLException) {
            println("url $target is not a valid one.")
        } catch (ex: IOException) {
            println("Get request failed: can not connect to $target")
            ex.printStackTrace()
        }

    }
}

