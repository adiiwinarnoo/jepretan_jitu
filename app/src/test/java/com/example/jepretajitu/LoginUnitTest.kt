package com.example.jepretajitu

import android.util.Log
import androidx.lifecycle.Observer
import com.example.jepretajitu.model.LoginResponse
import com.example.jepretajitu.network.ApiConfig
import com.example.jepretajitu.network.ApiService
import com.example.jepretajitu.repository.LoginRepository
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.viewmodel.LoginViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okio.Timeout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


class LoginUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setup() {
        loginRepository = mockk(relaxed = true)
        loginViewModel = LoginViewModel()
        loginViewModel.loginRepository = loginRepository
    }

    @Test
    fun testLogin_withValidCredentials() {
        // Arrange
        val email = "akunuser@gmail.com"
        val password = "password123"
        val expectedResponse = LoginResponse(message = "Login Berhasil")
        val liveDataObserver = mockk<Observer<LoginResponse>>()
        loginViewModel.loginData.observeForever(liveDataObserver)
        every { loginRepository.login(email, password, any()) } answers {
            val callback: (result: LoginResponse) -> Unit = secondArg()
            callback.invoke(expectedResponse)
        }

        // Act
        val result = loginViewModel.login(email, password)

        // Assert
        assertNotNull(result)
        verify { liveDataObserver.onChanged(expectedResponse) }
    }

}

class InstantTaskExecutorRule {

}
