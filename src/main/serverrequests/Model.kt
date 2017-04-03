package serverrequests

import java.util.prefs.Preferences


/**
 * A model corresponding to the controller
 */
class Model {
    /**
     * Name of a token under which the base url is saved
     */
    private val BASE_URL_TOKEN = "url"

    /**
     * Name of a token under which the paths are saved
     */
    private val PATHS_TOKEN = "paths"
    /**
     * Name of a token under which the value of number of threads is saved
     */
    private val  THREADS_TOKEN ="num of threads"
    /**
     * List of available http methods
     */
    val methods = listOf("POST", "PUT")
    /**
     * Default http method
     */
    val defaultMethod = methods[0]

    /**
     * Index of currently selected method in the list {@methods}
     */
    var currentMethodIndex = 0

    fun start(baseUrl: String, threads: Int, paths: List<String>) {
        val requests = paths.map { it -> Get(baseUrl + it) }
        (1..threads).map { it -> Thread( Worker("$it", requests) ) }.forEach { it -> it.start() }
    }

    fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Set up the index of currently selected method
     */
    fun onMethodChange(oldValue: String?, newValue: String?) {
        val index = methods.indexOf(newValue)
        if (index > -1) {
            currentMethodIndex = index
        }
    }

    fun saveState(config: Config) {
        val prefs = Preferences.userNodeForPackage(this::class.java)
        prefs.put(BASE_URL_TOKEN, config.url)
        prefs.put(PATHS_TOKEN, config.paths)
        prefs.putInt(THREADS_TOKEN, config.threads)
        prefs.flush()
    }

    fun restoreState(): Config {
        val prefs = Preferences.userNodeForPackage(this::class.java)
        val baseUrl = prefs.get(BASE_URL_TOKEN, "")
        val paths = prefs.get(PATHS_TOKEN, "")
        val threads = prefs.getInt(THREADS_TOKEN, 0)
        return Config(baseUrl, paths, threads)
    }


}