package com.example.github

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel(){
    lateinit var userListData: MutableLiveData<ArrayList<UserData>>
    lateinit var userViewAdapter: UserViewAdapter

    init{
        userListData = MutableLiveData()
        userViewAdapter = UserViewAdapter()
    }

    fun getAdapter() : UserViewAdapter{
        return userViewAdapter
    }

    fun setAdapterData(data : ArrayList<UserData>){
        userViewAdapter.setDataList(data)
        userViewAdapter.notifyDataSetChanged()
    }

    fun getUserListDataObserver(): MutableLiveData<ArrayList<UserData>>{
        return userListData
    }

    fun makeAPICall(){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromAPI()
        call.enqueue(object : Callback<ArrayList<UserData>>{
            override fun onFailure(call: Call<ArrayList<UserData>>, t: Throwable) {
                userListData.postValue(null)
            }

            override fun onResponse(call: Call<ArrayList<UserData>>, response: Response<ArrayList<UserData>>) {
                if(response.isSuccessful){
                    userListData.postValue(response.body())
                } else {
                    userListData.postValue(null)
                }
            }
        })
    }

}