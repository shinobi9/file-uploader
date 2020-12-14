package cyou.shinobi9.fileuploader.model

import javafx.beans.property.StringProperty
import tornadofx.*

class User(
    val nameProp: StringProperty = stringProperty(""),
    val personalAccessTokenProp: StringProperty = stringProperty(""),
    val avatarUrlProp: StringProperty = stringProperty(""),
)

class UserModel(
    val user: User = User()
) : ViewModel() {
    var name by user.nameProp
    var personalAccessToken by user.personalAccessTokenProp
    var avatarUrl by user.avatarUrlProp
}
