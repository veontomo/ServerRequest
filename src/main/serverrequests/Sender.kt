/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrequests

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import java.util.HashMap

/**

 * @author Andrea
 */
internal class Sender(val id: String) {

    private val charset = "UTF-8"
    private val senderId: Int

    init {
        this.senderId = counter
        counter++
    }

    fun getContent(url: String, tries: Int) {
        val r = RequestSender(url, tries, senderId)
        val t = Thread(r)
        t.start()
    }

    fun putContent(address: String, data: String) {
        val url: URL
        var connection: HttpURLConnection? = null
        try {
            //Create connection
            url = URL(address)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
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

            //Get Response
//            val `is` = connection.inputStream
//            val rd = BufferedReader(InputStreamReader(`is`))
//            var line: String
//            val response = StringBuffer()
//            while ((line = rd.readLine()) != null) {
//                response.append(line)
//                response.append('\r')
//            }
//            rd.close()
//            println(response.toString())

        } catch (e: Exception) {

            e.printStackTrace()

        } finally {

            if (connection != null) {
                connection.disconnect()
            }
        }

    }

    internal inner class RequestSender(private val url: String, private val tries: Int, private val senderId: Int) : Runnable {

        override fun run() {
            var connection: URLConnection?
            var response: InputStream
            val header: String
            for (i in 0..tries - 1) {
                //System.out.println("Sender " + senderId + " is trying to make connection no. " + i + " out of " + tries + " to " + url);
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
                    println("sender $senderId, try: $i can not connect to $url")
                    ex.printStackTrace()
                }

            }
            println("sender $senderId has finished")
        }
    }

    companion object {

        private var counter = 0

        /**
         * Converts hash map into a string

         * @param map string to string map
         * *
         * @return
         */
        fun <T> hashSerialize(map: HashMap<String, T>?): String {
            if (map == null) {
                return ""
            }
            var str = "{"
            var key: String
            var value: T

            for ((key1, value1) in map) {
                value = value1
                key = key1
                str += "\"" + key + "\":"
                if (value is String) {
                    str += "\"" + value + "\","
                } else {
                    val value2 = value as HashMap<String, *>
                    if (value2 != null) {
//                        str += hashSerialize<*>(value2) + ","
                    }
                }
            }
            str = str.substring(0, str.length - 1) + "}"
            return str
        }
    }
}
