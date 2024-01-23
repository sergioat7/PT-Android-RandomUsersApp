package com.aragones.sergio.randomusersapp.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.aragones.sergio.randomusersapp.R
import com.aragones.sergio.randomusersapp.databinding.FragmentUserListBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private val viewModel: UserListViewModel by activityViewModels()
    private lateinit var adapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserListBinding.inflate(inflater, container, false)

        adapter = UserListAdapter(listOf()) { user ->

            val action =
                UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(user.email)
            findNavController().navigate(action)
        }
        binding.swipeRefreshLayoutUsers.setOnRefreshListener {

            adapter.resetUsers()
            viewModel.reloadData()
            viewModel.loadUsers()
        }
        binding.recyclerViewUsers.adapter = adapter

        viewModel.loader.observe(this as LifecycleOwner) { isLoading ->
            binding.swipeRefreshLayoutUsers.isRefreshing = isLoading
        }

        viewModel.newUsers.observe(this as LifecycleOwner) {
            adapter.addUsers(it)
        }

        viewModel.error.observe(this as LifecycleOwner) { error ->

            Snackbar.make(
                binding.root,
                error?.message ?: getString(R.string.generic_error),
                Snackbar.LENGTH_LONG
            ).show()
        }

        viewModel.loadUsers()

        return binding.root
    }
}