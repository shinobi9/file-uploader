package cyou.shinobi9.fileuploader

import cyou.shinobi9.fileuploader.style.MainCss
import cyou.shinobi9.fileuploader.view.MainView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.javafx.JavaFx
import mu.KotlinLogging
import tornadofx.*
import kotlin.coroutines.CoroutineContext

class Application : App(MainView::class, MainCss::class), CoroutineScope {
    init {
        dumpStylesheets()
        reloadStylesheetsOnFocus()
//        reloadViewsOnFocus()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.JavaFx
}

fun main(args: Array<String>) = launch<Application>(args)

internal val LOG = KotlinLogging.logger {}
