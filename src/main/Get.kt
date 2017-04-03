package serverrequests
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
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
            //Get Response
            val inputStream = connection.inputStream
            val rd = BufferedReader(InputStreamReader(inputStream))
            val responseBuffer = StringBuffer()
            var line = rd.readLine()
            while (line != null) {
                responseBuffer.append(line)
                responseBuffer.append('\r')
                line = rd.readLine()
            }
            rd.close()
            return Report(true, responseBuffer.toString().length.toString())
        } catch (ex: MalformedURLException) {
            return Report(false, "invalid url $target: ${ex.message}")
        } catch (ex: IOException) {
            return Report(false, "Failed to connect to $target: ${ex.message}")
        }

    }
}

