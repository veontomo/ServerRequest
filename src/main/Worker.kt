package serverrequests

/**
 *
 * An immutable data type.
 * Represents a worker with multiple tasks to execute.
 */
class Worker(val name: String, val jobs: List<Request>) : Runnable {
    override fun run() {
        start()
    }

    /**
     * Perform all jobs assigned to this worker
     */
    fun start() {
        println("Worker: $name start")
        val reports = jobs.map { it -> it.perform() }
        println("Worker: $name end, jobs ${jobs.size}, reports: ${reports.size}")
    }

    fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}