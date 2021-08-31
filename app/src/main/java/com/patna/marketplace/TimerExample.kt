package com.patna.marketplace

import android.util.Log


class TimerExample {
    var secondCount = 0

    lateinit var runnable: Runnable
    var handler = android.os.Handler()

    fun startTimer()
    {
        runnable = Runnable {
            secondCount++
            Log.i(TimerExample::class.simpleName,"Timer is ar ${secondCount}")

            handler.postDelayed(runnable,1000)

        }
        handler.postDelayed(runnable,1000)
    }

    fun stopTimer(){
        handler.removeCallbacks(runnable)
    }
}