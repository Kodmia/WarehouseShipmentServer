package ru.dikoresearch.routes.orders.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.dikoresearch.domain.entities.models.Order
import ru.dikoresearch.domain.entities.responses.ListOfAllOrdersResponse
import ru.dikoresearch.domain.repository.local.WarehouseOrdersLocalRepository
import ru.dikoresearch.domain.repository.remote.ProductsLocationRepository

fun Route.showProductLocation(
    productsLocationRepository: ProductsLocationRepository
){
    get("product/{art}"){
        val productArt= call.parameters["art"] ?: return@get call.respond(HttpStatusCode.BadRequest)
        productsLocationRepository.showProductLocation(productArt)
        call.respond(HttpStatusCode.OK)
    }
}