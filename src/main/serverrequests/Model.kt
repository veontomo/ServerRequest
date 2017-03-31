package serverrequests


/**
 * A model corresponding to the controller
 */
class Model {
    fun start(baseUrl: String, totalRequests: Int, maxSimRequests: Int, requests: List<String>) {
        val worker = Worker(listOf<Request>())
        worker.start()
    }

    fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}