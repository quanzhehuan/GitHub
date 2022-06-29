package com.example.github

data class UserData (val login: String, val avatar_url: String)

data class Repo (val name: String, val description: String, val html_url: String, val language: String, val created_at: String)