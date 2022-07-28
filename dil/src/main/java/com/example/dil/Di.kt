package com.example.dil

import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.KCallable
import kotlin.reflect.KClass

object Di {

    private val dependenciesHolder = HashMap<Qualifier, DependencyFabric<*>>()

    fun <T : Any> get(qualifier: Qualifier): T {
        val dependency = dependenciesHolder[qualifier]
        if (dependency != null) {
            return dependency.get() as T
        } else {
            throw IllegalArgumentException("No")
        }
    }

    fun <T : Any> add(qualifier: Qualifier, dependencyFabric: DependencyFabric<T>) {
        if (dependenciesHolder.containsKey(qualifier)) {
            throw IllegalArgumentException("Уже сохранено")
        }
        dependenciesHolder[qualifier] = dependencyFabric
    }

    //    fun <T : Any> add(dependencies: T) {
//        dependenciesHolder[dependencies::class] = dependencies
//    }


    inline fun <reified T : Any> add(dependencyFabric: DependencyFabric<T>) {
        add(Qualifier(T::class), dependencyFabric)
    }

    inline fun <reified T : Any> add(named: String, dependencyFabric: DependencyFabric<T>) {
        add(Qualifier(T::class, named), dependencyFabric)
    }
}

inline fun <reified T : Any> getInline(): T {
    return Di.get(Qualifier(T::class))
}

inline fun <reified T : Any> getInline(named: String): T {
    return Di.get(Qualifier(T::class, named))
}

inline fun <reified T : Any> inject() = lazy<T> {
    Di.get(Qualifier(T::class))
}

inline fun <reified T : Any> inject(named: String) = lazy<T> {
    Di.get(Qualifier(T::class, named))
}

data class Qualifier(
    private val clazz: KClass<*>,
    private val named: String = "default",
)