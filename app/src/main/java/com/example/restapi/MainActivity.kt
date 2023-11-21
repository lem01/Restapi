package com.example.restapi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.restapi.data.Service
import com.example.restapi.data.model.TopRatedResult
import com.example.restapi.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import okhttp3.ResponseBody


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initComponets()

        var service = getData()

//        chageText(service)


    }

    private fun chageText(service: TopRatedResult) {
        for (itemResults in service.results) {

            textView.setText(itemResults.name + "\n ")

        }
    }

    private fun getData(): TopRatedResult {
        val service = Service.ServiceFactory.makeService()
        lateinit var topRatedResult: TopRatedResult
        lifecycleScope.launch {
            try {
                val topRatedResult = service.getTopRated("978c086010f26d51cfdde7c12faa6d23")
                println(topRatedResult)

            } catch (e: Exception) {
                e.printStackTrace()

                if (e is retrofit2.HttpException) {
                    val responseBody: ResponseBody? = e.response()?.errorBody()
                    println(responseBody?.string())
                } else {
                    println("Error: ${e.message}")
                }
            }

        }
        return topRatedResult
    }

    private fun initComponets() {
        textView.findViewById<TextView>(R.id.textView)
    }


}