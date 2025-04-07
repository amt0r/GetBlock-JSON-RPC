package com.example.getblock.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class RpcResponse<T>(
    val result: T
)

@Serializable
data class RpcRequest(
    val jsonrpc: String = "2.0",
    val id: String = "getblock.io",
    val params: List<JsonElement> = emptyList(),
    val method: String
)