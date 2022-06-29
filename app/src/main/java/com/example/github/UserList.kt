package com.example.github

data class UserData (val login: String, val avatar_url: String, val id: String, val node_id: String, val html_url: String, val type: String, val site_admin: String)

data class Repo (val name: String, val description: String, val html_url: String, val language: String, val created_at: String)