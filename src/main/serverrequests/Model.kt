package serverrequests
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch


/**
 * A model corresponding to the controller
 */
class Model {
    val methods = listOf("POST", "PUT")
    val defaultMethod = methods[0]
    /**
     * Index of currently selected method in the list {@methods}
     */
    var currentMethodIndex = 0

    fun start(baseUrl: String, totalRequests: Int, maxSimRequests: Int, paths: List<String>) {
        val requests = paths.map { it -> Get(it) }
        val threads = (1..maxSimRequests).map { it -> Thread(Runnable { Worker("$it", requests).start() })}
        threads.forEach { it -> it.start() }


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
//        println("before launch: thread ${Thread.currentThread().name}")
//        launch(CommonPool) {
//            println("launch thread ${Thread.currentThread().name}")
//            for (i in 1..3) {
//                sleepAndWakeUp(i).await()
//            }
//        }
//        println("after launch: thread ${Thread.currentThread().name}")
    }

//    fun sleepAndWakeUp(ind: Int) = async(CommonPool) {
//        println("$ind sleepAndWakeUp thread ${Thread.currentThread().name}")
//        val original = longOperation(1000)
//        println("$ind $original")
//    }
//
//    fun longOperation(timeMs: Long): Long {
//        println("longOperation thread ${Thread.currentThread().name}")
//        val start = System.currentTimeMillis()
//        Thread.sleep(timeMs)
//        return System.currentTimeMillis() - start
//    }


}