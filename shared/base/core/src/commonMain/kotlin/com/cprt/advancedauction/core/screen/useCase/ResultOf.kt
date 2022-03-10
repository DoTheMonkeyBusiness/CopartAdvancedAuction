package com.cprt.advancedauction.core.screen.useCase

sealed class ResultOf<out T> {
    class Success<out T>(val value: T) : ResultOf<T>()
    class Failure(val throwable: Throwable) : ResultOf<Nothing>()
}
