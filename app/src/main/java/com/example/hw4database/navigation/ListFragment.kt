package com.example.hw4database.navigation

import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw4database.R
import com.example.hw4database.Repositories
import com.example.hw4database.adapter.UserAdapter
import com.example.hw4database.adapter.UserViewHolder
import com.example.hw4database.databinding.FragmentListBinding
import com.example.hw4database.dialog.DialogDelete
import com.example.hw4database.dialog.DialogUpdate
import com.example.hw4database.model.User

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val userRepository by lazy {
        Repositories.userRepository
    }
    private val adapter by lazy {
        UserAdapter(requireContext()) { user, btn ->
            when (btn) {
                UserViewHolder.BUTTON_DELETE -> {
                    DialogDelete().show(parentFragmentManager, DialogDelete.TAG)
                    dialogDeleteListener(user)
                }
                UserViewHolder.BUTTON_EDIT -> {
                    DialogUpdate(user).show(parentFragmentManager, DialogUpdate.TAG)
                    dialogUpdateListener(user)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val linearLayout = LinearLayoutManager(requireContext())

        with(binding) {
            recyclerView.layoutManager = linearLayout
            recyclerView.adapter = adapter
        }

        rebuildUserList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val manager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(activity?.componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                adapter.filter.filter(text)
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun rebuildUserList() {
        val userList = userRepository.getAllUsers()
        adapter.submitList(userList)
    }

    private fun deleteUser(user: User) {
        userRepository.deleteUser(user).run {
            showToast("$user deleted")
        }
    }

    private fun dialogDeleteListener(user: User) {
        parentFragmentManager.setFragmentResultListener(
            DialogDelete.REQUEST_KEY,
            this
        ) { _, result ->
            val which = result.getInt(DialogDelete.KEY_RESPONSE)
            if (which == DialogInterface.BUTTON_POSITIVE) {
                deleteUser(user)
                rebuildUserList()
            }
        }
    }

    private fun dialogUpdateListener(oldUser: User) {
        parentFragmentManager.setFragmentResultListener(
            DialogUpdate.REQUEST_KEY,
            this
        ) { _, result ->
            val id = oldUser.id
            val firstName = result.getString(DialogUpdate.KEY_FIRST_NAME)
            val secondName = result.getString(DialogUpdate.KEY_SECOND_NAME)


            val newUser = User(
                id = id,
                firstName = firstName ?: oldUser.firstName,
                secondName = secondName ?: oldUser.secondName,
            )
            println(firstName)
            println(secondName)
            userRepository.updateUser(oldUser, newUser)

            binding.recyclerView.adapter = adapter
            rebuildUserList()

        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            .show()
    }
}