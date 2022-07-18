package com.example.usergit.ui.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class RxButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : androidx.appcompat.widget.AppCompatButton(context, attrs) {


    private val disposable = CompositeDisposable()

    fun rxBtnClickListener(view: View, myClickListener: RxClick) {
        disposable.addAll(
            Observable.create(ObservableOnSubscribe<View> { emitter ->
                view.setOnClickListener {
                    emitter.onNext(it)
                }
            }).subscribeBy(
                onNext = {
                    myClickListener.rxOnClick(it)
                }
            )
        )
    }
    fun stop (){
        disposable.dispose()
    }
}