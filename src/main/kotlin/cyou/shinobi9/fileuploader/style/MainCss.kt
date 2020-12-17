package cyou.shinobi9.fileuploader.style

import tornadofx.*

class MainCss : Stylesheet() {
    companion object {
        val body by cssclass()
        val buttons by cssclass()
        val repoNameColumn by cssclass()
        val repoUrlColumn by cssclass()
        val repoButton by cssclass()
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
        repoNameColumn {
            prefWidth = 200.px
        }
        repoUrlColumn {
            prefWidth = 400.px
        }
        repoButton {
//            prefWidth = 100.percent
            spacing = 10.px
        }
    }
}
