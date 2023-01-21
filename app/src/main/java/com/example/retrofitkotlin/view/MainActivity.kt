package com.example.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.adapter.CryptoAdapter
import com.example.retrofitkotlin.databinding.ActivityMainBinding
import com.example.retrofitkotlin.modul.Crypto
import com.example.retrofitkotlin.service.CryptoAPI
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import io.reactivex.internal.schedulers.RxThreadFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), CryptoAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL="https://raw.githubusercontent.com/"
    private lateinit var cryptoArrayList: ArrayList<Crypto>

    private var compositeDisposable:CompositeDisposable?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        compositeDisposable= CompositeDisposable()

        //https://raw.githubusercontent.com/
        //atilsamancioglu/K21-JSONDataSet/master/crypto.json
        loadData()

        binding.recyclerView.layoutManager=LinearLayoutManager(this)



    }


    fun loadData(){

        //set up retrofit
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)

            .addConverterFactory(GsonConverterFactory.create())
            .build()





       val service=retrofit.create(CryptoAPI::class.java) //create service


        val call=service.getData() // call getData of CryptoApi class

        call.enqueue(object :Callback<List<Crypto>>{
            override fun onResponse(call: Call<List<Crypto>>, response: Response<List<Crypto>>) {

                if(response.isSuccessful){

                    response.body()?.let {

                        cryptoArrayList=ArrayList(it)

                        cryptoArrayList?.let {
                            val adapter=CryptoAdapter(it,this@MainActivity)
                            binding.recyclerView.adapter=adapter
                        }
                       /* for (response : Crypto in cryptoArrayList){

                            println(response.price)

                        }
                        */

                    }

                }
            }

            override fun onFailure(call: Call<List<Crypto>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        }


        )


    }

    override fun onItemClick(crypto: Crypto) {
        Toast.makeText(this,"Clicked : ${crypto.currency}",Toast.LENGTH_LONG).show()
    }


}