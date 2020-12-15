@file:Suppress("ClassName")

package cyou.shinobi9.fileuploader.api

import cyou.shinobi9.fileuploader.api.github.proxyClient
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.ext.asStream
import io.github.rybalkinsd.kohttp.ext.url

object imageLoader {

    fun loadImageIfExistProxySettings(url: String) = httpGet(proxyClient) {
        url(url)
    }.asStream().let { requireNotNull(it) }
}
