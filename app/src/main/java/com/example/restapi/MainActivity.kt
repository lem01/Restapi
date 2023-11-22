package com.example.restapi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.example.restapi.data.RetrofitService
import com.example.restapi.data.model.ListUsers
import com.example.restapi.data.model.TopRatedResult
import com.example.restapi.data.model.User
import com.example.restapi.data.model.UserResponse
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
        binding.progressBar.isVisible = false

        initComponets()


    }

    private fun getUsers() {
        val service = RetrofitService.ServiceFactory.makeService()
        lifecycleScope.launch {
                try {

                    binding.tvData.setText("")
                    binding.progressBar.isVisible = true
                    val listUsers = service.gitListUsers()
                    println(listUsers)
                    chageTextUsers(listUsers)
                    binding.progressBar.isVisible = false

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

//        if (lifecycle.currentState == Lifecycle.State.DESTROYED){
//            println("entro aqui")
//        }

    }

    private fun chageTextUsers(service: ListUsers) {
        var textNames = ""
        for (itemResults in service.data) {
            textNames += itemResults.first_name + "\n "

        }
        binding.tvData.setText(textNames)

    }

    private fun chageTextMovies(service: TopRatedResult) {
        var textNames = ""
        for (itemResults in service.results) {
            textNames += itemResults.name + "\n "

        }
        binding.tvData.setText(textNames)

    }

    private fun getTopRatedMovies() {
        val service = RetrofitService.ServiceFactory.makeService()
        lateinit var topRatedResult: TopRatedResult
        lifecycleScope.launch {
            try {
                val topRatedResult = service.getTopRated("978c086010f26d51cfdde7c12faa6d23")
                println(topRatedResult)
                chageTextMovies(topRatedResult)
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
    }

    private fun registerUser() {
        val service = RetrofitService.ServiceFactory.makeService()
        lateinit var userResponse: UserResponse
        lifecycleScope.launch {
            try {
                binding.progressBar.isVisible = true
                val user: User = User("eve.holt@reqres.in","pistol")
                val userResponse = service.registerUser(user)
                println(userResponse)
                var response = "\n id = " + userResponse.id + "\n token = " + userResponse.token
                binding.tvData.setText(response)
                binding.progressBar.isVisible = false
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
    }

    private fun updateUser() {
        val updatedData = mapOf(
            "name" to "Abhishek",
            "job" to "zion resident"
        )

        val service = RetrofitService.ServiceFactory.makeService()
        lifecycleScope.launch {
            try {
                binding.progressBar.isVisible = true
                binding.tvData.setText("")

                val updatedUser = service.updateUser(2, updatedData)

                println(updatedUser)
                var response = "\n update at = " + updatedUser.updatedAt
                binding.tvData.setText(response)
                binding.progressBar.isVisible = false

            } catch (e: Exception) {
                e.printStackTrace()
                // Maneja cualquier error que pueda ocurrir
            }
        }
    }

    private fun initComponets() {
        binding.btnGet.setOnClickListener {
            getTopRatedMovies()
            getUsers()

        }

        binding.btnCreate.setOnClickListener {

            visibleComponents()

            binding.tvDescription.isVisible = false
            binding.btnCreateAcept.isVisible = true
            binding.editTextTextEmailAddress.isVisible = true
            binding.editTextTextPersonName.isVisible = true
        }

        binding.btnCreateAcept.setOnClickListener {
            invisibleComponents()
            registerUser()
        }

        binding.btnUpdate.setOnClickListener {
            updateUser()
        }

        binding.btnDelete.setOnClickListener { }


    }

    private fun invisibleComponents() {
        binding.tvDescription.isVisible = true
        binding.btnCreateAcept.isVisible = false
        binding.editTextTextEmailAddress.isVisible = false
        binding.editTextTextPersonName.isVisible = false
    }

    private fun visibleComponents() {
        binding.tvDescription.isVisible = false
        binding.btnCreateAcept.isVisible = true
        binding.editTextTextEmailAddress.isVisible = true
        binding.editTextTextPersonName.isVisible = true
    }


}