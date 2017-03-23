/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrequests

/**

 * @author Andrea
 */
internal class Sender(val id: String) {

    fun getContent(url: String, tries: Int) {
        val r = RequestSenderGet(url, tries, id)
        val t = Thread(r)
        t.start()
    }

    fun putContent(url: String, data: String, tries: Int) {
        Thread(RequestSenderPut(url, data, tries, id)).start()

    }



}
