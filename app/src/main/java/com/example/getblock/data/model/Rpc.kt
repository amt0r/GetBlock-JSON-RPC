package com.example.getblock.data.model

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@JsonIgnoreUnknownKeys
data class RpcResponse<T>(
    val result: T
)

@Serializable
data class RpcRequest(
    @EncodeDefault val jsonrpc: String = "2.0",
    @EncodeDefault val id: String = "getblock.io",
    @EncodeDefault val params: List<JsonElement> = emptyList(),
    val method: String
)