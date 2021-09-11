package com.patna.marketplace.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.patna.marketplace.model.Constants
import com.patna.marketplace.model.Fact
import com.patna.marketplace.model.FactCategory
import com.patna.marketplace.model.FactDao
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject

class FactsViewModel(private val factDao: FactDao,application: Application) :AndroidViewModel(application) {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _facts = MutableLiveData<List<Fact>>()
    val facts:LiveData<List<Fact>>
     get() = _facts
    init {

        initializeDatabase()

        initializeFacts()


    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun initializeFacts(){
        uiScope.launch {
            _facts.value = getFactsFromDatabase()

        }
    }
    private fun initializeDatabase(){
        uiScope.launch {
            val facts = extractData()
            insert(facts)
        }
    }

    private suspend fun getFactsFromDatabase():List<Fact>{

        return withContext(Dispatchers.IO){
            val facts = factDao.getAllFacts()

            facts
        }
    }
    /*private suspend fun initializeAndGetData(){
        val job = uiScope.launch {
            val facts = extractData()
            insert(facts)
        }
        job.join()

        uiScope.launch {
            _facts.value = getFactsFromDatabase()
            Log.i(FactsViewModel::class.simpleName,"${_facts.value}")

        }

    }*/
    private suspend fun insert(facts: List<Fact>){
        withContext(Dispatchers.IO){
            for (fact in facts){
                factDao.insert(fact)
            }

        }

    }
    private fun extractData():List<Fact>{
        val facts = mutableListOf<Fact>()
        val jsonObject = JSONObject(Constants.facts_json)
        val factsJson = jsonObject.getJSONObject(Constants.facts)
        val political: JSONArray = factsJson.getJSONArray(Constants.political)
        for (i in 0 until political.length()){
            val element: JSONObject = political.get(i) as JSONObject
            val heading = element.get(Constants.heading) as String
            val body = element.get(Constants.body) as String
            facts.add(Fact(category = FactCategory.POLITICAL,heading = heading,body = body))
        }
        return facts
    }
}