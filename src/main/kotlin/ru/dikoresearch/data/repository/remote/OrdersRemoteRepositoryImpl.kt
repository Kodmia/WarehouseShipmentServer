package ru.dikoresearch.data.repository.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.dikoresearch.domain.entities.models.RemoteGoods
import ru.dikoresearch.domain.entities.models.RemoteOrder
import ru.dikoresearch.domain.repository.remote.OrdersRemoteRepository
import java.time.LocalDateTime

class OrdersRemoteRepositoryImpl(
    private val remoteServerUrl: String,
    private val client: HttpClient
): OrdersRemoteRepository {

    override suspend fun getOrderByOrderName(orderName: String): RemoteOrder? {
        if (remoteServerUrl.isNotEmpty()){
            val remoteOrderResponse = client.get(remoteServerUrl+orderName)

            return if (remoteOrderResponse.status != HttpStatusCode.OK){
                println("Got response from remote server with code ${remoteOrderResponse.status}")
                null
            } else {
                createOrderFromRemoteResponse(remoteOrderResponse.body())
            }
        }
        else {

            val g1 = RemoteGoods(
                art = "123456",
                name = "G1",
                count = "1",
                price = 111
            )

            val g2 = RemoteGoods(
                art = "234567",
                name = "G2",
                count = "2",
                price = 222
            )

            val g3 = RemoteGoods(
                art = "345678",
                name = "G3",
                count = "3",
                price = 333
            )


            return RemoteOrder(
                date = LocalDateTime.now().toString(),
                name = orderName,
                uuid = "1111-2222-3333-4444",
                goods = listOf(g1, g2, g3)
            )
        }

    }

    private fun createOrderFromRemoteResponse(responseText: String): RemoteOrder?{
        return try {
            Json.decodeFromString<RemoteOrder>(responseText)

        } catch (e: Exception){
            println("Got exception while converting response from server $e")
            null
        }
    }
}