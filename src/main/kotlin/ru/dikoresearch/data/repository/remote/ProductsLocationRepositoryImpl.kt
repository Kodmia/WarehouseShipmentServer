package ru.dikoresearch.data.repository.remote

import io.ktor.client.*
import io.ktor.client.request.*
import org.jetbrains.exposed.sql.exposedLogger
import ru.dikoresearch.domain.repository.remote.ProductsLocationRepository

class ProductsLocationRepositoryImpl(
    private val productsLocationServerUrl: String,
    private val client: HttpClient
): ProductsLocationRepository {
    override suspend fun showProductLocation(art: String) {
        if (productsLocationServerUrl.isNotEmpty()){

            client.get(productsLocationServerUrl+art)
        }
        else {
            exposedLogger.info("Url is empty, showing location of $art")
        }
    }
}