package com.example.github

import retrofit2.Call
import retrofit2.http.GET

interface RetroService {

    //repositories?q=newyork
    //fun getDataFromAPI(@Query("q")query: String):Call<UserList>

    @GET("users")
    fun getDataFromAPI():Call<ArrayList<UserData>>

    @GET("users/{login}/repos")
    fun getRepoFromAPI():Call<ArrayList<Repo>>
}