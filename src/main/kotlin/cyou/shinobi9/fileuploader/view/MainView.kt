@file:Suppress("JoinDeclarationAndAssignment")

package cyou.shinobi9.fileuploader.view

import cyou.shinobi9.fileuploader.controller.GithubController
import cyou.shinobi9.fileuploader.model.RepositoryModel
import cyou.shinobi9.fileuploader.model.UserModel
import cyou.shinobi9.fileuploader.style.MainCss
import javafx.scene.Parent
import javafx.scene.control.ButtonBar.ButtonData.LEFT
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import tornadofx.*

class MainView : View() {
    override val root: Parent = VBox().addClass(MainCss.body)
    private val userModel by inject<UserModel>()
    private val githubController by inject<GithubController>()
    private val repos = mutableListOf<RepositoryModel>().asObservable()

    init {
        with(root) {
            form {
                fieldset("github") {
                    field("access token:") {
                        textfield(userModel.user.personalAccessTokenProp) {
                            promptText = "github personal access token"
                        }
                    }
                }
            }
            hbox {
//                style { backgroundColor += Color.ALICEBLUE }
                addClass(MainCss.buttons)
                buttonbar {
                    button(type = LEFT) {
                        text = "获取用户信息"
                        action { githubController.user(userModel) }
                    }
                    button(type = LEFT) {
                        text = "获取仓库"
                        action { githubController.repositories(userModel, repos) }
                    }
                    button(type = LEFT) {
                        text = "清除所有数据"
                        action { githubController.clearAll(userModel, repos) }
                    }
                }
            }
            label(userModel.user.nameProp) {
                style { backgroundColor += Color.GREEN }
            }
//            hyperlink(userModel.user.avatarUrlProp)

            imageview(userModel.user.avatarImageProp)

            tableview(repos) {
                column("NAME", RepositoryModel::name)
                column("URL", RepositoryModel::url)
            }
        }
    }
}
