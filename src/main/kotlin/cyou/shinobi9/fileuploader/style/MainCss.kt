package cyou.shinobi9.fileuploader.style

import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Color
import tornadofx.*

class MainCss : Stylesheet() {
    companion object {
        val body by cssclass()
        val buttons by cssclass()
    }

    init {
        body {
            prefWidth = 600.px
            prefHeight = 360.px
            padding = box(10.px)
            spacing = 10.px
        }
        buttons {
//            backgroundColor += Color.RED
        }
    }
}
