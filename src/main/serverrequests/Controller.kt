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
    @FXML private var numRequestPerThreadText: TextField? = null
    @FXML private var method: Spinner<String>? = null
    val model = Model()


    override fun initialize(location: URL?, resources: ResourceBundle?) {
        stopBtn!!.isDisable = true
        method!!.valueFactory = SpinnerValueFactory.ListSpinnerValueFactory<String>(FXCollections.observableArrayList(listOf("POST", "PUT")))
        startBtn!!.setOnMouseClicked { startExecution() }

        stopBtn!!.setOnMouseClicked { stopExecution() }
    }

    private fun stopExecution() {
        stopBtn!!.isDisable = true
        startBtn!!.isDisable = false
        model.stop()
    }

    private fun startExecution() {
        startBtn!!.isDisable = true
        val baseUrl = "http://192.168.5.95:8070"
//        urlText!!.text
        stopBtn!!.isDisable = false

        val simReq = Integer.parseInt(numOfThreadsText!!.text)
        val req = Integer.parseInt(numRequestPerThreadText!!.text)
//        val requests = requestsTextArea!!.text.split("\n")
        val requests = listOf("/subscribe", "/unsubscribe")
        model.start(baseUrl, req, simReq, requests)

    }

}
