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

}