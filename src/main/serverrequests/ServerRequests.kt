/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrequests

import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.ArrayList
import java.util.HashMap

/**

 * @author Andrea
 * * http://stackoverflow.com/questions/2793150/using-java-net-urlconnection-to-fire-and-handle-http-requests
 */
object ServerRequests {

    //    private static final String URL = "http://2.228.14.114/news";
    private val URL = "http://192.168.5.95/news"
    private val charset = "UTF-8"
    private val ATTEMPTS = 3
    private val USERS = 20

    /**
     * @param args the command line arguments
     */
    @Throws(UnsupportedEncodingException::class)
    @JvmStatic fun main(args: Array<String>) {

        var s: Sender

        var param2: String
        var query: String
        val paths = HashMap<String, String>()
        val auth = HashMap<String, String>()
        val data = HashMap<String, HashMap<String, String>>()

        //        paths.putContent("germany/steel-2015-12-09", "http://www.handelsvertreterangebote.com/Handelsvertreter_Automotive-Beleuchtung-M%C3%B6bel-und-Maschinen-Ausr%C3%BCstungen_2208700003-51.html");
        //        paths.putContent("germany/home", "http://www.handelsvertreterangebote.com");
        //        paths.putContent("germany/wine-2015-12-09", "http://www.handelsvertreterangebote.com/Handelsvertreter_Bars-Restaurant-Hotel-Weine-und-Getr%C3%A4nke_2287600001-18.html");
        //        paths.putContent("germany/contacts", "http://www.handelsvertreterangebote.com/");
        //        paths.putContent("venditori/auguri-natale-2015", "http://www.venditori.it/");
        //
        //        paths.putContent("consigli/donne-di-denari-ebook", "http://www.amazon.it/Donne-denari-strategie-vincenti-gestire-ebook/dp/B014DS365U");
        //        paths.putContent("consigli/donne-di-denari-libro", "http://www.deagostinilibri.it/dl_it/donne-di-denari.html");
        //        paths.putContent("consigli/donne-di-denari-sito", "http://www.deagostini.it/prodotti/donne-di-denari");
        //        paths.putContent("consigli/unsubscribe", "http://www.agentimail.it/mails/remove/5fa5d4265777dd41af58cc1b2025b76cd14268f8");
        paths.put("venditori/come-funziona", "http://www.venditori.it/infovendi.asp")
        paths.put("soluzione/offerta", "http://www.soluzioneagenti.it/promo/offerta-2016")
        paths.put("fatture/prodotto", "http://www.fattureprovvigionali.it/prodotto")
        paths.put("venditori/auguri-natale-2015", "http://www.venditori.it/")
        paths.put("consigli/unsubscribe", "http://www.agentimail.it/mails/remove/5fa5d4265777dd41af58cc1b2025b76cd14268f8")

        //
        //        auth.putContent("login", "Sun");
        //        auth.putContent("password", "shine");
        auth.put("login", "Andrea")
        auth.put("password", "AndreaR0")

        data.put("auth", auth)
        data.put("paths", paths)

        val urls = ArrayList<String>()

        for (url in paths.keys) {
            urls.add(url)
            urls.add(url + "/TRACKCODE")
        }
        urls.add("images/venditori/logo_natale_2015.jpg")
        urls.add("images/venditori/12345qweroiu/logo_natale_2015.jpg")
        urls.add("images/test/img.jpg")
        urls.add("images/test/lkjflkdjsLJLJLJ987/img.jpg")

        val poolSize = urls.size
        val pool = urls.toTypedArray()

        s = Sender("route loader")
        s.putContent(URL + "/routes/load", Sender.hashSerialize(data))
        println(Sender.hashSerialize(data))

        for (i in 0..USERS - 1) {
            param2 = pool[i % poolSize]
            query = String.format("/%s", URLEncoder.encode(param2, charset))

            s = Sender("sender $i")

            s.getContent(URL + query, ATTEMPTS)
        }

    }

}
