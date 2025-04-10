package com.example.getblock.data.api

import com.example.getblock.data.model.BlockResult
import com.example.getblock.data.model.EpochResult
import com.example.getblock.data.model.RpcRequest
import com.example.getblock.data.model.RpcResponse
import com.example.getblock.data.model.SupplyResult
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.encodeToJsonElement


object ApiService {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                encodeDefaults = true
            })
        }
    }

    private suspend inline fun <reified T> makeRequest(
        method: String,
        params: List<JsonElement> = emptyList()
    ): RpcResponse<T>? {
        return try {
            val request = RpcRequest(
                method = method,
                params = params
            )

            val response: HttpResponse = client.post(BASE_URL) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            response.body()
        } catch (e: Exception) {
            println("Error: ${e.localizedMessage}")
            null
        }
    }


    suspend fun getSupply(): RpcResponse<SupplyResult>? {
        return makeRequest("getSupply")
    }

    suspend fun getEpoch(): RpcResponse<EpochResult>? {
        return makeRequest("getEpochInfo")
    }

    suspend fun getBlocks(start: Long, end: Long): RpcResponse<List<Long>>? {
        return makeRequest(
            "getBlocks",
            listOf(Json.encodeToJsonElement(start), Json.encodeToJsonElement(end), JsonNull)
        )
    }

    suspend fun getBlock(slot: Long): RpcResponse<BlockResult>? {
        return makeRequest(
            "getBlock",
            listOf(
                Json.encodeToJsonElement(slot),
                Json.encodeToJsonElement(mapOf("maxSupportedTransactionVersion" to 0))
            )
        )
    }
}