package com.azri.moviedbjetpackcompose.utils

import android.util.Log
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLPeerUnverifiedException

suspend fun <T> Flow<Response<T>>.collectAndHandle(
    onError: (Throwable?) -> Unit = { error->
        Log.e("collectAndHandle", error?.message.toString() )
    },
    onLoading : () -> Unit = {},
    stateReducer : (T) -> Unit //onSuccess retrieving data from response
){
    collect { response ->
        when(response){
            is Response.Error -> {
                onError(response.error)
            }
            is Response.Loading -> {
                onLoading()
            }
            is Response.Success -> {
                stateReducer(response.data)
            }
        }
    }
}

fun Throwable.toUserMessage(): String {
    return when (this) {
        is SocketTimeoutException -> "Connection Timed Out. Please try again."
        is InterruptedIOException -> "Request timed out. Please try again."
        is UnknownHostException -> "No Internet Connection"
        is ConnectException -> "Unable to connect to server."
        is SSLHandshakeException -> "Unable to establish SSL handshake."
        is SSLPeerUnverifiedException -> "Secure connection failed."
        is HttpException -> when (code()) {
            400 -> "Invalid request. Please try again."
            401 -> "Session expired. Please log in again."
            403 -> "You don't have permission to perform this action."
            404 -> "Requested resource not found."
            in 500..599 -> "Server is currently unavailable. Please try later."
            else -> "Unexpected server error."
        }
        is IOException -> "Network error. Please check your connection."
        else -> "Unexpected error occurred."
    }
}