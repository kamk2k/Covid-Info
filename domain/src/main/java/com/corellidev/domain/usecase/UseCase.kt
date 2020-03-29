package com.corellidev.domain.usecase

interface UseCase<P, T> {
    suspend fun execute(data: P? = null): T
}