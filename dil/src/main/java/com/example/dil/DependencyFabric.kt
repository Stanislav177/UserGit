package com.example.dil

abstract class DependencyFabric <T : Any> (protected val creator: () -> Any) {
    abstract fun get(): Any
}