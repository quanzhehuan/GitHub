package com.example.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.example.github.databinding.RecyclerviewRowBinding
class UserInfoFragment ( private val context: MainActivity,
                         private var userData: UserData
): Fragment() {
        private var _binding: RecyclerviewRowBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = RecyclerviewRowBinding.inflate(layoutInflater, container, false)
            binding.nameTextView.text = userData.login
            Picasso.with(context).load(userData.avatar_url).into(binding.thubmImage)
            return binding.root
        }

        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }
    }