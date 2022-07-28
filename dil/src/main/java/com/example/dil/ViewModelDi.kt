package com.example.dil

class ViewModelDi<T : Any>(creator: () -> Any) : DependencyFabric<T>(creator) {
    val viewModelDependency by lazy {
        creator.invoke()
    }

    override fun get(): Any {
        return viewModelDependency
    }

}