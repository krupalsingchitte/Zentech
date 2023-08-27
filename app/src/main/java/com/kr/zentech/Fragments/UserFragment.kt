package com.kr.zentech.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kr.zentech.LoginActivity
import com.kr.zentech.auth.SaveToken
import com.kr.zentech.databinding.FragmentUserBinding
import com.kr.zentech.model.UserInfo
import com.kr.zentech.viewmodel.UserViewModel


class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val savedId = SaveToken.getId(requireContext())
        viewModel.userInfoLiveData.observe(viewLifecycleOwner) { userInfo ->
            userInfo?.let {
                displayUserInfo(userInfo)
            }
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) { errorMessage ->
            showError(errorMessage)
        }
        viewModel.fetchUserInfo(savedId)
        binding.btn.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    private fun displayUserInfo(userInfo: UserInfo) {
        Glide.with(requireContext()).load(userInfo.image).into(binding.userImage)
        binding.userId.text = "User ID: ${userInfo.id}"
        binding.firstName.text = "First Name: ${userInfo.firstName}"
        binding.lastName.text = "Last Name: ${userInfo.lastName}"
        binding.email.text = "Email: ${userInfo.email}"
        binding.gender.text = "Gender: ${userInfo.gender}"
        binding.username.text = "Username: ${userInfo.username}"
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}




