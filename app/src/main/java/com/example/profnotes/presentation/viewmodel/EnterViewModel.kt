package com.example.profnotes.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profnotes.data.api.Api
import com.example.profnotes.data.api.Token
import com.example.profnotes.domain.request.EnterRequest
import com.example.profnotes.domain.responce.AuthenticationResponse
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnterViewModel : ViewModel() {

    @JsonClass(generateAdapter = true)
    sealed class EnterResult {
        data class Success(val response: AuthenticationResponse) : EnterResult()
        /*data class Token(val token: String) : EnterResult()*/
        data class Error(val message: String) : EnterResult()
    }

    private val _enterResult: MutableLiveData<EnterResult> = MutableLiveData()
    val enterResult: LiveData<EnterResult> = _enterResult

    fun auth(phone: String, password: String) = viewModelScope.launch {
        val api = Api.apiService
        val enterRequest = EnterRequest(phone, password)
        val callback = api.auth(enterRequest)
        callback.enqueue(object : Callback<AuthenticationResponse> {

            override fun onResponse(
                call: Call<AuthenticationResponse>,
                response: Response<AuthenticationResponse>
            ) {
                when (response.code()) {
                    200 -> {
                        val enterResponse = response.body()
                        /*enterResponse?.data?.token?.let { EnterResult.Token(it) }*/
                        Token.token = enterResponse?.data?.token.toString()
                        _enterResult.value = enterResponse?.let { EnterResult.Success(it) }
                    }
                    401 -> {
                        _enterResult.value = EnterResult.Error("Неверный пароль или номер")
                    }
                }
            }

            override fun onFailure(call: Call<AuthenticationResponse>, t: Throwable) {
                _enterResult.value = EnterResult.Error("Ошибка авторизации")
            }
        })
    }
}