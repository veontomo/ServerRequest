import serverrequests.Report
import serverrequests.Request
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/**
 * An immutable data type corresponding to a POST http request
 */
class Put(val target: String, val data: RequestData) : Request {
    private val charset = "UTF-8"
    override fun perform(): Report {
        val info = data.toString()
        val url: URL
        var connection: HttpURLConnection? = null
        try {
            //Create connection
            url = URL(target)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "PUT"
            connection.setRequestProperty("Content-Type", "application/json")

            connection.setRequestProperty("Content-Length", "" + Integer.toString(info.toByteArray().size))
            connection.setRequestProperty("Content-Language", charset)

            connection.useCaches = false
            connection.doInput = true
            connection.doOutput = true

            //Send request
            val wr = DataOutputStream(connection.outputStream)
            wr.writeBytes(info)
            wr.flush()
            wr.close()
            println("Done")

            //Get Response
            val inputStream = connection.inputStream
            val rd = BufferedReader(InputStreamReader(inputStream))
            val response = StringBuffer()
            var line = rd.readLine()
            while (line != null) {
                response.append(line)
                response.append('\r')
                line = rd.readLine()
            }
            rd.close()
            println("Put request $response")

        } catch (e: MalformedURLException) {

            e.printStackTrace()
            return Report(false, "invalid url $target: ${e.message}")

        } finally {

            if (connection != null) {
                connection.disconnect()
            }
        }
        return Report(true, "improve the message")

    }

}