package com.example.retrofitkotlin.service

import android.database.Observable
import com.example.retrofitkotlin.modul.Crypto
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {


    //https://raw.githubusercontent.com/
    // atilsamancioglu/K21-JSONDataSet/master/crypto.json



    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData():Call<List<Crypto>>



}