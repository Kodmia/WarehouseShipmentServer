package ru.dikoresearch.routes.orders

import io.ktor.server.auth.*
import io.ktor.server.routing.*
import ru.dikoresearch.data.repository.local.WarehouseLocalRepositoryImpl
import ru.dikoresearch.domain.repository.local.ImageFileManager
import ru.dikoresearch.domain.repository.remote.OrdersRemoteRepository
import ru.dikoresearch.domain.repository.remote.ProductsLocationRepository
import ru.dikoresearch.routes.orders.routes.*

fun Route.orders(
    warehouseImagesLocalRepository: WarehouseLocalRepositoryImpl,
    ordersRemoteRepository: OrdersRemoteRepository,
    productsLocationRepository: ProductsLocationRepository,
    imageFileManager: ImageFileManager
) {
    route("orders"){
        authenticate {
            allOrders(
                warehouseImagesLocalRepository
            )

            newOrder(
                warehouseImagesLocalRepository
            )

            orderByName(
                warehouseImagesLocalRepository,
                ordersRemoteRepository
            )

            uploadImage(
                warehouseImagesLocalRepository,
                imageFileManager
            )

            showProductLocation(
                productsLocationRepository
            )

        }

        orderImageByName(
            warehouseImagesLocalRepository,
            imageFileManager
        )

    }

}