package com.example.hw4database.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.hw4database.databinding.ItemUserBinding
import com.example.hw4database.model.User

class UserViewHolder(
    private val binding: ItemUserBinding,
    private val onClick: (User, Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.textView.text = user.toString()
        binding.root.setOnLongClickListener {
            onClick(user, BUTTON_DELETE)
            true
        }
        binding.btnEdit.setOnClickListener {
            onClick(user, BUTTON_EDIT)
        }
    }

    companion object {
        const val BUTTON_DELETE = 0
        const val BUTTON_EDIT = 1
    }
}