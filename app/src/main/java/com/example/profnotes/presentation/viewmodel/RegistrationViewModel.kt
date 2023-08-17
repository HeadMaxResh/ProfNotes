package com.example.profnotes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profnotes.data.api.Api
import com.example.profnotes.data.api.Token
import com.example.profnotes.domain.model.request.EnterRequest
import com.example.profnotes.domain.model.request.RegistrationRequest
import com.example.profnotes.domain.model.responce.AuthenticationResponse
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body

class RegistrationViewModel : ViewModel() {

    private val api = Api.apiService

    @JsonClass(generateAdapter = true)
    sealed class RegisterResult {
        data class Success(val response: AuthenticationResponse) : RegisterResult()
        data class Error(val message: String) : RegisterResult()
    }

    private val _registerResult: MutableLiveData<RegisterResult> = MutableLiveData()
    val registerResult: LiveData<RegisterResult> = _registerResult

    fun register(name: String, lastname: String, phone: String, password: String) = viewModelScope.launch {
        val registerRequest = RegistrationRequest(name, lastname, null, phone, password)
        val callback = api.register(registerRequest)
        callback.enqueue(object : Callback<AuthenticationResponse> {

            override fun onResponse(
                call: Call<AuthenticationResponse>,
                response: Response<AuthenticationResponse>
            ) {
                when (response.code()) {
                    200 -> {
                        val registerResponse = response.body()
                        /*registerResponse?.data?.token?.let { RegisterResult.Token(it) }*/
                        _registerResult.value = registerResponse?.let { RegisterResult.Success(it) }
                    }
                    409 -> {
                        _registerResult.value = RegisterResult.Error("Пользователь уже существует")
                    }
                }
            }

            override fun onFailure(call: Call<AuthenticationResponse>, t: Throwable) {
                _registerResult.value = RegisterResult.Error("Ошибка регистрации")
            }
        })
    }
}