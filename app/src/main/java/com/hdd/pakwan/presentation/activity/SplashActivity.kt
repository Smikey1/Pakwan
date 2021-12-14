package com.hdd.pakwan.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.hdd.pakwan.R
import com.hdd.pakwan.data.localDataSource.UserDatabase
import com.hdd.pakwan.data.models.User
import com.hdd.pakwan.data.remoteDataSource.ServiceBuilder
import com.hdd.pakwan.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    var email: String? = ""
    var password: String? = ""
    var token: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        retrieveUserDetails()
        fakeNavigation()
        SystemClock.sleep(2000)
    }

    private fun retrieveUserDetails() {
        val sharedPreferences = getSharedPreferences("userAuth", MODE_PRIVATE)
        email = sharedPreferences.getString("email", "")
        password = sharedPreferences.getString("password", "")
        token = sharedPreferences.getString("token", "")
        // withApiRetrofit()
    }

    private fun fakeNavigation() {
        startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
        finish()
    }

    private fun withApiRetrofit() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = UserRepository().loginUser(User(email = email!!, password = password!!))
            if (user.success == true) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                ServiceBuilder.token = "Bearer $token"
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
            }
        }
    }

    private fun withRoomDB() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = UserDatabase.getInstance(this@SplashActivity)?.userDao().loginUser(email!!, password!!)
            if (user == null) {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }
    }
}