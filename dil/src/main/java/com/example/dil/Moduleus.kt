package com.example.dil

import android.app.Application

class Modules(private val block: Modules.() -> Unit) {
    lateinit var contextApp: Application

    fun install() {
        block()
    }

    fun initContext(context: Application) {
        contextApp = context
    }

    inline fun <reified T : Any> singleton(named: String, noinline creator: () -> T) {
        Di.add(named, Singleton<T>(creator))
    }

    inline fun <reified T : Any> singleton(noinline creator: () -> T) {
        Di.add(Singleton<T>(creator))
    }

    inline fun <reified T : Any> factory(noinline creator: () -> T) {
        Di.add(Factory<T>(creator))
    }

    inline fun <reified T : Any> viewModel(noinline creator: () -> T) {
        Di.add(ViewModelDi<T>(creator))
    }
}