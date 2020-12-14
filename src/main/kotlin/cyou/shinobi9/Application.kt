package cyou.shinobi9

import cyou.shinobi9.view.MainView
import tornadofx.App
import tornadofx.launch

class Application : App(MainView::class)

fun main(args: Array<String>) = launch<Application>(args)
