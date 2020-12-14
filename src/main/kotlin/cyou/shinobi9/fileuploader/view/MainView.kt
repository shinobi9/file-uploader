package cyou.shinobi9.fileuploader.view

import cyou.shinobi9.fileuploader.api.github.githubUserAPI
import cyou.shinobi9.fileuploader.api.imageLoader
import cyou.shinobi9.fileuploader.model.UserModel
import javafx.scene.Parent
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import tornadofx.*

class MainView : View() {
    override val root: Parent = VBox()
    private val userModel: UserModel by inject()
    private lateinit var image: ImageView

    init {
        with(root) {
            style {
                prefWidth = 600.px
                prefHeight = 360.px
            }

            button {
                text = "读取环境变量！"
                action {
                    userModel.personalAccessToken = System.getenv("TOKEN")
                }
            }
            label {
                textProperty().bind(userModel.user.personalAccessTokenProp)
            }
            button {
                text = "登录！"
                action {
                    val user = githubUserAPI.fetchCurrentUser(userModel.personalAccessToken)
                    userModel.user.nameProp.set(user.name)
                    userModel.user.avatarUrlProp.set(user.avatarUrl)
                    image.imageProperty().bind(
                        objectBinding(userModel.user.avatarUrlProp) {
                            value?.let {
                                Image(
                                    imageLoader.loadImageIfExistProxySettings(it)
                                )
                            }
                        }
                    )
//                    image.imageProperty().bind(objectBinding("https://www.dogedoge.com/assets/new_logo_header.min.png".toProperty()) { value?.let { Image(it, true) } })
                }
            }
            label {
                textProperty().bind(userModel.user.nameProp)
            }
            hyperlink(userModel.user.avatarUrlProp)

            image = imageview {
            }
        }
    }
}
