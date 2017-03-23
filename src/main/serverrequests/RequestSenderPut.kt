package serverrequests

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Execute PUT requests
 * @param url url to which the requests are to be performed
 * @param data string version of the json object to be passed
 * @param tries how many times the request is to pe repeated
 * @param id marker
 */
class RequestSenderPut(val url: String, val data: String, val tries: Int, val id: String) : Runnable {
    private val charset = "UTF-8"
    override fun run() {
        for (i in 1..tries) {
            putContent2(url, data)
        }
    }

    private fun putContent2(address: String, data: String) {
        val url: URL
        var connection: HttpURLConnection? = null
        try {
            //Create connection
            url = URL(address)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "PUT"
            connection.setRequestProperty("Content-Type", "application/json")

            connection.setRequestProperty("Content-Length", "" + Integer.toString(data.toByteArray().size))
            connection.setRequestProperty("Content-Language", charset)

            connection.useCaches = false
            connection.doInput = true
            connection.doOutput = true

            //Send request
            val wr = DataOutputStream(
                    connection.outputStream)
            wr.writeBytes(data)
            wr.flush()
            wr.close()
            println("Done")

            //Get Response
            val `is` = connection.inputStream
            val rd = BufferedReader(InputStreamReader(`is`))
            val response = StringBuffer()
            var line = rd.readLine()
            while (line != null) {
                response.append(line)
                response.append('\r')
                line = rd.readLine()
            }
            rd.close()
            println("put sender $id ${response.toString()}")

        } catch (e: Exception) {

            e.printStackTrace()

        } finally {

            if (connection != null) {
                connection.disconnect()
            }
        }

    }
}