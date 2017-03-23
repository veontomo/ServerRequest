package serverrequests

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.text.Text
import java.net.URL
import java.util.*

class Controller : Initializable {

    @FXML private var startBtn: Button? = null
    @FXML private var stopBtn: Button? = null
    @FXML private var urlText: TextField? = null
    @FXML private var requestsTextArea: TextArea? = null
    @FXML private var simReqText: TextField? = null
    @FXML private var reqText: TextField? = null
    val model = Model()


    override fun initialize(location: URL?, resources: ResourceBundle?) {
        stopBtn!!.isDisable = true
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

        val simReq = Integer.parseInt(simReqText!!.text)
        val req = Integer.parseInt(reqText!!.text)
//        val requests = requestsTextArea!!.text.split("\n")
        val requests = listOf("/subscribe", "/unsubscribe")
        model.start(baseUrl, req, simReq, requests)

    }

}
