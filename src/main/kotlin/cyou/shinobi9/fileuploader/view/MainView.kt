@file:Suppress("JoinDeclarationAndAssignment")

package cyou.shinobi9.fileuploader.view

import cyou.shinobi9.fileuploader.controller.GithubController
import cyou.shinobi9.fileuploader.model.RepositoryModel
import cyou.shinobi9.fileuploader.model.UserModel
import cyou.shinobi9.fileuploader.style.MainCss
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.scene.Parent
import javafx.scene.control.Alert.AlertType.CONFIRMATION
import javafx.scene.control.ButtonBar.ButtonData.LEFT
import javafx.scene.control.TableView
import javafx.scene.layout.Priority.ALWAYS
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.stage.Modality
import javafx.stage.StageStyle
import tornadofx.*

class MainView : View() {
    override val root: Parent = VBox().addClass(MainCss.body)
    private val userModel by inject<UserModel>()
    private val githubController by inject<GithubController>()
    private val repos = mutableListOf<RepositoryModel>().asObservable()
    private lateinit var reposTable: TableView<RepositoryModel>
    private val selectedRepo :ReadOnlyObjectProperty<RepositoryModel?> by lazy { reposTable.selectionModel.selectedItemProperty() }

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
                        text = "用户信息"
                        hgrow = ALWAYS
                        action { githubController.user(userModel) }
                    }
                    button(type = LEFT) {
                        text = "仓库信息"
                        hgrow = ALWAYS
                        action { githubController.repositories(userModel, repos) }
                    }
                    button(type = LEFT) {
                        text = "清除所有"
                        hgrow = ALWAYS
                        action { githubController.clearAll(userModel, repos) }
                    }
                }
            }
            label(userModel.user.nameProp) {
                style { backgroundColor += Color.GREEN }
            }
//            hyperlink(userModel.user.avatarUrlProp)

            imageview(userModel.user.avatarImageProp)

            reposTable = tableview(repos) {
                column("NAME", RepositoryModel::name) {
                    addClass(MainCss.repoNameColumn)
                }
                column("URL", RepositoryModel::url) {
                    addClass(MainCss.repoUrlColumn)
                }
//                resizeColumnsToFitContent()
//                subscribe<TestEvent> { e ->
//                    alert(INFORMATION, "hi", "test event")
// //                    Notifications
//                } onLeftClick {
//                    fire(TestEvent())
//                }
//                selectionModel.selectedItemProperty().addListener(
//                    ChangeListener<RepositoryModel> { _: ObservableValue<out RepositoryModel>?, _: RepositoryModel, new: RepositoryModel ->
//                        alert(INFORMATION, "hi", new.url)
//                    }
//                )
            }
            hbox {
                style {
                    borderColor += box(Color.BLACK)
                }
                button("跳转") {
                    addClass(MainCss.repoButton)
                    action {
//                        showRepositoryDetailsView()
                        alert(CONFIRMATION, "hi", selectedRepo.value?.url ?: "empty!")
                    }
                }
            }
        }
    }

    private fun Component.showRepositoryDetailsView() {
        find<RepositoryDetailsView>().openModal(
            StageStyle.DECORATED,
            Modality.APPLICATION_MODAL,
            true,
            currentWindow,
            false
        )
    }
}
