package com.example.jepretajitu.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed
import com.example.jepretajitu.databinding.ActivitySplashScreenBinding
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.utils.SharedPrefences
import com.example.jepretajitu.view.activity.admin.MenuUtama
import com.example.jepretajitu.view.activity.fotographer.AddFotoActivity
import com.example.jepretajitu.view.activity.user.MUtamaActivity

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding : ActivitySplashScreenBinding
    var timer : Int = 5000
    lateinit var sharePreferences : SharedPrefences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharePreferences = SharedPrefences(this)

        var checkedLogin = sharePreferences.getIntData(Constant.AFTER_LOGIN)
        var checkedIdLevel = sharePreferences.getIntData(Constant.ADD_ID_LEVEL)

        if (checkedLogin == 1 && checkedIdLevel == 1) startActivity(Intent(this,MenuUtama::class.java))
        else if (checkedLogin == 1 && checkedIdLevel == 2 ) startActivity(Intent(this, MUtamaActivity::class.java))
        else if (checkedLogin == 1 && checkedIdLevel == 3) startActivity(Intent(this, AddFotoActivity::class.java))
        else if (checkedLogin == 0) {
            Handler().postDelayed({
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }, timer.toLong())
        }
    }
}