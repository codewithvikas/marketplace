package com.patna.marketplace

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.patna.marketplace.model.Constants
import com.patna.marketplace.model.Fact
import org.json.JSONArray
import org.json.JSONObject
import java.lang.StringBuilder

class FactsViewModel :ViewModel() {

    companion object{
        const val DONE = 0L
        const val ONE_SECOND = 1000L
        const val COUNTDOWN_TIME = 10000L

    }
    private val _currentTime = MutableLiveData<Long>()
    val currentTime:LiveData<Long>
    get() = _currentTime

    private val _timerFinished = MutableLiveData<Boolean>()
    val timerFinished:LiveData<Boolean>
    get() = _timerFinished

    private val _facts = MutableLiveData<String>()
    val facts:LiveData<String>
    get() = _facts

    private val timer:CountDownTimer
    init {
        timer = object :CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value  = millisUntilFinished
            }

            override fun onFinish() {
                _timerFinished.value = true
            }

        }
        timer.start()
        _facts.value = extractData()
    }

    fun onGameFinishComplete(){
        _timerFinished.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    fun extractData():String{
        val jsonObject = JSONObject(Constants.facts_json)
        val facts = jsonObject.getJSONObject(Constants.facts)
        val political: JSONArray = facts.getJSONArray(Constants.political)
        val stringBuilder = StringBuilder()
        for (i in 0 until political.length()){
            val element: JSONObject = political.get(i) as JSONObject
            stringBuilder.append(element.get(Constants.heading))
            stringBuilder.append("\n")
            stringBuilder.append(element.get(Constants.body))
        }
        return stringBuilder.toString()
    }
}