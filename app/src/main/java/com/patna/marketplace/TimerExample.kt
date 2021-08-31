package com.patna.marketplace

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


class TimerExample( lifecycle: Lifecycle):LifecycleObserver {
    var secondCount = 0

    lateinit var runnable: Runnable
    var handler = android.os.Handler()

    init {
        lifecycle.addObserver(this)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startTimer()
    {
        runnable = Runnable {
            secondCount++
            Log.i(TimerExample::class.simpleName,"Timer is ar ${secondCount}")

            handler.postDelayed(runnable,1000)

        }
        handler.postDelayed(runnable,1000)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopTimer(){
        handler.removeCallbacks(runnable)
    }
}