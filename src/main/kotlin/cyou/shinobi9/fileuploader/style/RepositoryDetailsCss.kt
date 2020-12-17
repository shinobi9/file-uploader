package cyou.shinobi9.fileuploader.style

import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class RepositoryDetailsCss : Stylesheet() {
    companion object {
        val body by cssclass()
    }
    init {
        body {
            prefWidth = 600.px
            prefHeight = 360.px
            padding = box(10.px)
            spacing = 10.px
        }
    }
}
