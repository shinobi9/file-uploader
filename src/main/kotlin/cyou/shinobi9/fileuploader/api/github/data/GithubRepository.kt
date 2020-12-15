package cyou.shinobi9.fileuploader.api.github.data

import cyou.shinobi9.fileuploader.api.Repository

class GithubRepository(
//    val id: Int,
    override val name: String,
    override val url: String,
    override val description: String,
//    val gitUrl: String,
//    val sshUrl: String,
) : Repository
