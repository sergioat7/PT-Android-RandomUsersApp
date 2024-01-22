package com.aragones.sergio.randomusersapp.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.aragones.sergio.randomusersapp.R
import com.aragones.sergio.randomusersapp.databinding.FragmentUserListBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private lateinit var binding: FragmentUserListBinding
    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserListBinding.inflate(inflater, container, false)

        viewModel.users.observe(this as LifecycleOwner) { result ->
            result.getOrNull()?.let { users ->

                binding.recyclerViewUsers.apply {
                    adapter = UserListAdapter(users) {
                        //TODO: navigate to details
                    }
                }
            } ?: run {

                Snackbar.make(
                    binding.root,
                    R.string.generic_error,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        return binding.root
    }
}