package cyou.shinobi9.fileuploader.view

import cyou.shinobi9.fileuploader.style.RepositoryDetailsCss
import javafx.scene.layout.BorderPane
import tornadofx.*

class RepositoryDetailsView : View() {
    override val root = BorderPane().addClass(RepositoryDetailsCss.body)

    init {
        with(root) {
            center {
                label("hello")
            }
        }
    }
}
