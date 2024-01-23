package com.aragones.sergio.randomusersapp.userdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aragones.sergio.randomusersapp.R
import com.aragones.sergio.randomusersapp.databinding.FragmentUserDetailsBinding
import com.aragones.sergio.randomusersapp.databinding.FragmentUserListBinding
import com.aragones.sergio.randomusersapp.userlist.UserListViewModel

class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private val viewModel: UserListViewModel by activityViewModels()
    private val args: UserDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)

        val email = args.userEmail
        val user = viewModel.users.value?.firstOrNull { it.email == email }

        return binding.root
    }
}