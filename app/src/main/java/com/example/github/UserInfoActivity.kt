package com.example.github.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.github.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val userInfo = intent
        userLogin.text = userInfo.getStringExtra("login")
        Picasso.with(this).load(userInfo.getStringExtra("avatar_url")).into(userIcon)

    }
}