package com.aragones.sergio.randomusersapp.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aragones.sergio.randomusersapp.R
import com.aragones.sergio.randomusersapp.databinding.FragmentUserDetailsBinding
import com.aragones.sergio.randomusersapp.extensions.getRoundImageView
import com.aragones.sergio.randomusersapp.extensions.toString
import com.aragones.sergio.randomusersapp.model.User
import com.aragones.sergio.randomusersapp.userlist.UserListViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private val viewModel: UserListViewModel by activityViewModels()
    private val args: UserDetailsFragmentArgs by navArgs()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)

        val email = args.userEmail
        viewModel.users.value?.firstOrNull { it.email == email }?.let {
            user = it
        } ?: run {
            //TODO: show error
            findNavController().popBackStack()
        }

        (activity as? AppCompatActivity)?.apply {
            supportActionBar?.apply {
                title = user?.getFullName()
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
            }
        }
        activity?.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                when (menuItem.itemId) {
                    android.R.id.home -> {
                        findNavController().popBackStack()
                    }
                }
                return false
            }
        }, viewLifecycleOwner)

        Picasso
            .get()
            .load(user.image)
            .apply {
                fit().centerCrop()
            }
            .into(binding.imageViewUser, object : Callback {

                override fun onSuccess() {
                    binding.imageViewUser.apply {
                        this.setImageDrawable(this.getRoundImageView(200F))
                    }
                }

                override fun onError(e: Exception) {
                }
            })

        binding.contentUserInfoName.textViewHeader.text = getString(R.string.name_surname)
        binding.contentUserInfoName.textViewValue.text = user.getFullName()
        binding.contentUserInfoEmail.textViewHeader.text = getString(R.string.email)
        binding.contentUserInfoEmail.textViewValue.text = user.email
        binding.contentUserInfoGender.textViewHeader.text = getString(R.string.gender)
        binding.contentUserInfoGender.textViewValue.text =
            getString(user.getStringResourceForGender())
        binding.contentUserInfoDate.textViewHeader.text = getString(R.string.registration_date)
        binding.contentUserInfoDate.textViewValue.text =
            user.registrationDate.toString(format = "dd/MM/yyyy")
        binding.contentUserInfoPhone.textViewHeader.text = getString(R.string.phone)
        binding.contentUserInfoPhone.textViewValue.text = user.phone

        return binding.root
    }
}