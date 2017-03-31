/**
 * An abstract data type for possible requests.
 *
 * A request is one of:
 * 1. Post
 * 2. Get
 * 3. Delete
 */
interface Request {
    /**
     * Perform the request
     */
    fun perform()
}