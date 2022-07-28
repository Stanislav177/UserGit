package com.example.dil

class Factory<T: Any>(creator: () -> Any) : DependencyFabric<T>(creator) {
    override fun get(): Any = creator
}