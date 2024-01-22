package com.aragones.sergio.randomusersapp.userlist

import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.RecyclerView
import com.aragones.sergio.randomusersapp.databinding.ItemUserBinding
import com.aragones.sergio.randomusersapp.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListAdapter(
    private val users: List<User>,
    private val listener: (User) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {

        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val user = users[position]
        if (holder is UserListViewHolder) {
            holder.bind(user)
        }
        holder.itemView.setOnClickListener {
            listener(user)
        }
    }

    inner class UserListViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {

            binding.textViewName.text = user.getFullName()
            binding.textViewEmail.text = user.email
            Picasso
                .get()
                .load(user.image)
                .apply {
                    fit().centerCrop()
                }
                .into(binding.userImage, object : Callback {

                    override fun onSuccess() {
                        binding.userImage.apply {
                            this.setImageDrawable(this.getRoundImageView(10F))
                        }
                    }

                    override fun onError(e: Exception) {
                    }
                })
        }
    }
}

fun ImageView.getRoundImageView(radius: Float): RoundedBitmapDrawable {

    val imageBitmap = (drawable as BitmapDrawable).bitmap
    val imageDrawable = RoundedBitmapDrawableFactory.create(context.resources, imageBitmap)
    imageDrawable.isCircular = true
    imageDrawable.cornerRadius = radius
    return imageDrawable
}
