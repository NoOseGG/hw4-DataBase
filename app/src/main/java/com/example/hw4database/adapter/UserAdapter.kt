package com.example.hw4database.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.hw4database.databinding.ItemUserBinding
import com.example.hw4database.model.User
import java.util.*

class UserAdapter(
    private val context: Context,
    private val onClick: (User, Int) -> Unit,
) : ListAdapter<User, UserViewHolder>(DIFF_UTIL), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            binding = ItemUserBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ),
            onClick = onClick,
        )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<User>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(currentList)
                } else {
                    for (user in currentList) {
                        if (user.toString().lowercase(Locale.getDefault())
                                .startsWith(constraint.toString().lowercase(Locale.getDefault()))
                        ) {
                            filteredList.add(user)
                        }
                    }
                }
                val filterResult = FilterResults()
                filterResult.values = filteredList
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, result: FilterResults?) {
                submitList(result?.values as MutableList<User>)
            }

        }
    }
}


