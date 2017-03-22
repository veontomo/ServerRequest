package serverrequests

/**
 * A model corresponding to the controller
 */
class Model {
    fun start(baseUrl: String, totalRequests: Int, maxSimRequests: Int, requests: List<String>) {

        println("$baseUrl, $totalRequests, $maxSimRequests, ${requests.joinToString { it }}")
    }

    fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}