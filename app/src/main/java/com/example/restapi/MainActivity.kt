package com.example.restapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restapi.data.Service
import com.example.restapi.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
//    var data =  ArrayList<Products>()
    lateinit var  binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val service = Service.ServiceFactory.makeService()
lifecycleScope.launch {
    val movie = service.listMovies("978c086010f26d51cfdde7c12faa6d23","US")
    println(movie)
}

//        setContentView(R.layout.activity_main)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        binding.rcMain.layoutManager = LinearLayoutManager(this)

//        getAllProducts()
    }

//    private fun getAllProducts() {
//        val retrofit = Service.buildService(ServiceInterface::class.java)
//
//        retrofit.getAllProducts().enqueue(object : Callback<ApiResponse> {
//            override fun onResponse(call: Call<ApiResponse>,response : Response<ApiResponse>) {
//
//                try {
//                    val responseBody = response.body()!!
//                    data = responseBody.products
//
//                }catch (ex:java.lang.Exception){
//                    ex.printStackTrace()
//                            }
//            }
//
//            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }

}