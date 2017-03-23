package serverrequests

import javafx.application.Application
import javafx.stage.Stage
import serverrequests.views.MainView

fun main(args: Array<String>) {
    GUI.main(args)
}
/**
 * A GUI application that performs various checks of selected html file.
 */
class GUI : Application() {

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Server request"
        primaryStage.scene = MainView(primaryStage).getScene()
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GUI::class.java)
        }
    }



}
