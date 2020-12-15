package cyou.shinobi9.fileuploader.model

import cyou.shinobi9.fileuploader.api.imageLoader
import javafx.beans.binding.ObjectBinding
import javafx.beans.property.StringProperty
import javafx.scene.image.Image
import tornadofx.*

class UserModel(
    val user: User = User(),
) : ViewModel() {
    var name by user.nameProp
    var personalAccessToken by user.personalAccessTokenProp
    var avatarUrl by user.avatarUrlProp
    val avatarUrld by user.avatarImageProp

    data class User(
        val nameProp: StringProperty = stringProperty(""),
        val personalAccessTokenProp: StringProperty = stringProperty(""),
        val avatarUrlProp: StringProperty = stringProperty(defaultImageUrl),
//    val avatarImageProp: ObjectProperty<Image> = objectProperty(Image(defaultImageUrl)).apply { bindStringProperty() }
        val avatarImageProp: ObjectBinding<Image> =
            nonNullObjectBinding(avatarUrlProp) {
                val url = if (value.isNullOrEmpty()) defaultImageUrl else value
                Image(imageLoader.loadImageIfExistProxySettings(url), 50.0, 50.0, false, false)
            },
    ) {
        companion object {
            const val defaultImageUrl =
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2703822895,1318686390&fm=11&gp=0.jpg"
        }
    }
}
