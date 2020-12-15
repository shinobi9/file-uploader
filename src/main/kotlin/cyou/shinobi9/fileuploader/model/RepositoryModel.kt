package cyou.shinobi9.fileuploader.model

import javafx.beans.property.StringProperty
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.setValue
import tornadofx.stringProperty

class RepositoryModel(
    val repository: Repository = Repository(),
) : ItemViewModel<RepositoryModel.Repository>() {
    var name by repository.nameProp
    var url by repository.urlProp
    var description by repository.descriptionProp

    data class Repository(
        val nameProp: StringProperty = stringProperty("nameProp"),
        val urlProp: StringProperty = stringProperty("urlProp"),
        val descriptionProp: StringProperty = stringProperty("descriptionProp"),
    )
}
