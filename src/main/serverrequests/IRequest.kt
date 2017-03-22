/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrequests

import java.net.URL

/**

 * @author Andrea
 */
interface IRequest {

    /**
     * Method by means of which the request is to be sent (POST, PUT, GET etc)
     * @param s
     */
    var method: String

    /**
     * Set url where the request should be sent
     * @param url
     */
    var url: URL

    /**
     * Performs the request
     */
    fun execute()

    /**
     * Server responce to the request
     * @return
     */
    fun responce(): String
}
