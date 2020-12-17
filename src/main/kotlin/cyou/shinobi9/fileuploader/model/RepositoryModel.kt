package cyou.shinobi9.fileuploader.model

import javafx.beans.property.StringProperty
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.setValue
import tornadofx.stringProperty

@Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")
class RepositoryModel(
    val repository: Repository = Repository(),
) : ItemViewModel<RepositoryModel.Repository>() {
    var name: String by repository.nameProp
    var url: String by repository.urlProp
    var description: String by repository.descriptionProp

    data class Repository(
        val nameProp: StringProperty = stringProperty("nameProp"),
        val urlProp: StringProperty = stringProperty("urlProp"),
        val descriptionProp: StringProperty = stringProperty("descriptionProp"),
    )
}
