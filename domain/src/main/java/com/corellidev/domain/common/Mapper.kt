package com.corellidev.domain.common

abstract class Mapper<in P, out T> {

    abstract fun convert(from: P): T

    fun map(from: P): T {
        return convert(from)
    }

    fun map(from: List<P>): List<T> {
        return from.map { convert(it) }
    }
}