package serverrequests
/**
 *
 * An immutable data type.
 * Represents a worker with multiple tasks to execute.
 */
class Worker(val name: String, val jobs: List<Request>) {
    /**
     * Perform all jobs assigned to this worker
     */
    fun start() {
        println("Worker: $name start")
        jobs.forEach(Request::perform)
        println("Worker: $name end")
    }

    fun stop(){
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}