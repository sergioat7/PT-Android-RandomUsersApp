package com.aragones.sergio.randomusersapp.userlist

import android.app.SearchManager
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aragones.sergio.randomusersapp.R
import com.aragones.sergio.randomusersapp.databinding.FragmentUserListBinding
import com.aragones.sergio.randomusersapp.extensions.hideSoftKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private val viewModel: UserListViewModel by activityViewModels()
    private lateinit var adapter: UserListAdapter
    private var searchView: SearchView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as? AppCompatActivity)?.apply {
            supportActionBar?.apply {
                title = getString(R.string.contacts)
                setDisplayHomeAsUpEnabled(false)
            }
        }

        activity?.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menu.clear()
                menuInflater.inflate(R.menu.user_list_menu, menu)
                setupSearchView(menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner)

        binding = FragmentUserListBinding.inflate(inflater, container, false)

        adapter = UserListAdapter(listOf()) { user ->

            val action =
                UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(user.email)
            findNavController().navigate(action)
        }
        binding.swipeRefreshLayoutUsers.setOnRefreshListener {
            searchUsers("")
        }
        binding.recyclerViewUsers.adapter = adapter
        binding.recyclerViewUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.loadUsers()
                }
            }
        })

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

        adapter.resetUsers()
        viewModel.reloadData()
        viewModel.loadUsers()

        return binding.root
    }

    private fun setupSearchView(menu: Menu) {

        val menuItem = menu.findItem(R.id.action_search)
        this.searchView = menuItem.actionView as SearchView
        this.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                searchUsers(query)
                menuItem.collapseActionView()
                return true
            }
        })

        val color = ContextCompat.getColor(requireActivity(), R.color.white)

        searchView?.let { searchView ->

            (activity?.getSystemService(Context.SEARCH_SERVICE) as? SearchManager)?.let {
                searchView.setSearchableInfo(it.getSearchableInfo(activity?.componentName))
            }

            searchView.setIconifiedByDefault(false)
            searchView.queryHint = resources.getString(R.string.search)
            if (viewModel.query.isNotBlank()) {
                searchView.setQuery(viewModel.query, false)
            }

            searchView.findViewById<AppCompatImageView>(androidx.appcompat.R.id.search_mag_icon)?.imageTintList =
                ColorStateList.valueOf(color)

            searchView.findViewById<View>(androidx.appcompat.R.id.search_plate)
                ?.let { searchPlate ->

                    val searchText =
                        searchPlate.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
                    if (searchText != null) {

                        searchText.setTextColor(color)
                        searchText.setHintTextColor(color)
                    }

                    searchPlate.findViewById<AppCompatImageView>(androidx.appcompat.R.id.search_close_btn)?.imageTintList =
                        ColorStateList.valueOf(color)
                }
        }
    }

    private fun searchUsers(query: String) {

        adapter.resetUsers()
        viewModel.setSearch(query)
        viewModel.reloadData()
        viewModel.loadUsers()
        requireActivity().hideSoftKeyboard()
    }
}