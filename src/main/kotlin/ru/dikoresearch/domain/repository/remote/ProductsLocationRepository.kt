package ru.dikoresearch.domain.repository.remote

interface ProductsLocationRepository {
    /** In response to this request, the server will highlight the product specified in the request with a lamp **/
    suspend fun showProductLocation(art: String): Unit
}