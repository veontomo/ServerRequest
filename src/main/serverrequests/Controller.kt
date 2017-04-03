package serverrequests

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import java.net.URL
import java.util.*

class Controller : Initializable {

    @FXML private var startBtn: Button? = null
    @FXML private var stopBtn: Button? = null
    @FXML private var baseUrlText: TextField? = null
    @FXML private var pathsTextArea: TextArea? = null
    @FXML private var numOfThreadsText: TextField? = null
    @FXML private var method: ComboBox<String>? = null
    val model = Model()


    override fun initialize(location: URL?, resources: ResourceBundle?) {
        stopBtn!!.isDisable = true
        method!!.items = FXCollections.observableArrayList(model.methods)
        method!!.value = model.defaultMethod
        method!!.valueProperty().addListener { _, oldValue, newValue -> model.onMethodChange(oldValue, newValue) }
        startBtn!!.setOnMouseClicked { startExecution() }
        stopBtn!!.setOnMouseClicked { stopExecution() }

        loadState(model.restoreState())
    }

    private fun stopExecution() {
        stopBtn!!.isDisable = true
        startBtn!!.isDisable = false
        model.stop()
    }

    private fun startExecution() {
        startBtn!!.isDisable = true
        val baseUrl = baseUrlText!!.text
        stopBtn!!.isDisable = false
        val simReq = try {
            Integer.parseInt(numOfThreadsText!!.text)
        } catch (e: NumberFormatException) {
            0
        }

        val requests = pathsTextArea!!.text
        saveState(baseUrl, requests, simReq)
        model.start(baseUrl, simReq, requests.split(Regex("\\s")))
    }

    fun saveState(baseUrl: String, paths: String, threads: Int) {
        val config = Config(baseUrl, paths, threads)
        model.saveState(config)
    }

    fun loadState(config: Config) {
        baseUrlText!!.text = config.url
        pathsTextArea!!.text = config.paths
        numOfThreadsText!!.text = config.threads.toString()


    }
}
