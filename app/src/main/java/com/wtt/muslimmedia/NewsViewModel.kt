package com.wtt.muslimmedia

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.wtt.muslimmedia.data.NewsResponse
import com.wtt.muslimmedia.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {
    // expose screen ui state
    private var _commonMuslimNews = MutableLiveData<NewsResponse>()
    val commonMuslimNews get() = _commonMuslimNews as LiveData<NewsResponse>
    private var _aboutAlQuran = MutableLiveData<NewsResponse>()
    val aboutAlQuran get() = _aboutAlQuran as LiveData<NewsResponse>
    private var _alJazeera = MutableLiveData<NewsResponse>()
    val alJazeera get() = _alJazeera as LiveData<NewsResponse>
    private var _warningForIslam = MutableLiveData<NewsResponse>()
    val warningForIslam get() = _warningForIslam as LiveData<NewsResponse>

    private var _searchNews = MutableLiveData<NewsResponse>()
    val searchNews get() = _searchNews as LiveData<NewsResponse>

    // handle business logic
    fun getCommonMuslimNews() {
        ApiClient.getApiService().getCommonMuslimNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    Log.i("ViewModel", "onResponse: ${response.body()}")
                    _commonMuslimNews.value = response.body()
                }else Log.e(
                    "ViewModel",
                    "onFailedCall: Call error with Http Status Code : " + response.code()
                )
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ViewModel", "onFailure: " + t.localizedMessage)
            }

        })
    }

    fun getAboutAlQuranNews() {
        ApiClient.getApiService().getAboutAlQuranNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    Log.i("ViewModel", "onResponse: ${response.body()}")
                    _aboutAlQuran.value = response.body()
                }else Log.e(
                    "ViewModel",
                    "onFailedCall: Call error with Http Status Code : " + response.code()
                )
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ViewModel", "onFailure: " + t.localizedMessage)
            }

        })
    }

    fun getAlJazeeraNews() {
        ApiClient.getApiService().getAlJazeeraNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    Log.i("ViewModel", "onResponse: ${response.body()}")
                    _alJazeera.value = response.body()
                }else Log.e(
                    "ViewModel",
                    "onFailedCall: Call error with Http Status Code : " + response.code()
                )
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ViewModel", "onFailure: " + t.localizedMessage)
            }

        })
    }

    fun getWarningForIslam() {
        ApiClient.getApiService().getWarningForIslam().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    Log.i("ViewModel", "onResponse: ${response.body()}")
                    _warningForIslam.value = response.body()
                }else Log.e(
                    "ViewModel",
                    "onFailedCall: Call error with Http Status Code : " + response.code()
                )
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ViewModel", "onFailure: " + t.localizedMessage)
            }

        })
    }

    fun searchNews(query: String) {
        ApiClient.getApiService().searchNews(query).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    _searchNews.postValue(response.body())
                } else Log.e("NewsViewModel", "onResponse: ${response.message()}", )
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}