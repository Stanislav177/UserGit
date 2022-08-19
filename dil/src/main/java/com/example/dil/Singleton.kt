package com.example.dil

class Singleton <T : Any>(creator: () -> Any) : DependencyFabric<T>(creator) {

    private val dependency by lazy {
        creator.invoke()
    }

    override fun get() = dependency
}