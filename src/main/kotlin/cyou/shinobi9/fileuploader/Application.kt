package cyou.shinobi9.fileuploader

import cyou.shinobi9.fileuploader.view.MainView
import mu.KotlinLogging
import tornadofx.App
import tornadofx.launch

class Application : App(MainView::class)

fun main(args: Array<String>) = launch<Application>(args)

internal val LOG = KotlinLogging.logger {}