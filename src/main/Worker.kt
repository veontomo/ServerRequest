/**
 * An immutable data type.
 * Represents a worker with multiple tasks to execute.
 */
class Worker(val jobs: List<Request>) {
    /**
     * Perform all jobs assigned to this worker
     */
    fun start() {
        jobs.forEach(Request::perform)
    }
}