package com.example.github

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
        userLogin.text = "Login : " + userInfo.getStringExtra("login")
        userType.text = "Account type : " + userInfo.getStringExtra("type")
        userAdmin.text = "Admin permissions : " + userInfo.getStringExtra("site_admin")
        userId.text = "Id : " + userInfo.getStringExtra("id")
        userNodeId.text = "Node Id : " + userInfo.getStringExtra("node_id")
        userHtmlUrl.text = "@Github : " + userInfo.getStringExtra("html_url")
        Picasso.with(this).load(userInfo.getStringExtra("avatar_url")).into(userIcon)

    }
}