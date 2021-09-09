package com.wakuzama.temperworks.data.other

import com.bumptech.glide.load.engine.Resource

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
companion object {
    fun <T> success(data: T?): com.wakuzama.temperworks.data.other.Resource<T> {
        return Resource(Status.SUCCESS, data, null)
    }

    fun <T> error(msg: String, data: T?): com.wakuzama.temperworks.data.other.Resource<T> {
        return Resource(Status.ERROR, data, msg)
    }

    fun <T> loading(data: T?): com.wakuzama.temperworks.data.other.Resource<T> {
        return Resource(Status.LOADING, data, null)
    }
}
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}