package com.example.goalfeed.home

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalfeed.security.BiometricAuthManager
import com.example.goalfeed.util.NewsApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val apiServiceImpl: NewsApiServiceImpl,
    private val biometricAuthManager: BiometricAuthManager
) : ViewModel() {

    private val _news = MutableStateFlow<List<NewsItem>>(emptyList())
    val news = _news.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    private val _showRetry = MutableStateFlow(false)
    val showRetry = _showRetry.asStateFlow()

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    // Carga noticias solo tras autenticar
    fun authenticate(activity: FragmentActivity) {
        biometricAuthManager.authenticate(
            activity,
            onError = {
                _isAuthenticated.value = false
                Toast.makeText(activity, "There was an error in the authentication", Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                _isAuthenticated.value = true
                loadNews(activity)
            },
            onFail = {
                _isAuthenticated.value = false
                Toast.makeText(activity, "The authentication failed, try again", Toast.LENGTH_SHORT).show()
            }
        )
    }

    fun retryApiCall(activity: FragmentActivity) {
        if (_isAuthenticated.value) {
            loadNews(activity)
        } else {
            authenticate(activity)
        }
    }

    private fun loadNews(activity: FragmentActivity) {
        _loading.value = true
        apiServiceImpl.getNews(
            context = activity,
            onSuccess = {
                viewModelScope.launch { _news.emit(it) }
                _showRetry.value = false
            },
            onFail = {
                _showRetry.value = true
            },
            loadingFinished = {
                _loading.value = false
            }
        )
    }
}
