package com.example.hw4database.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hw4database.Repositories
import com.example.hw4database.databinding.FragmentMenuBinding
import com.example.hw4database.model.User

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val userRepository by lazy {
        Repositories.userRepository
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMenuBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnAdd.setOnClickListener {
                if (!edFirstName.text.isNullOrBlank() && !edSecondName.text.isNullOrBlank()) {
                    val firstName = edFirstName.text.toString().trim()
                    val secondName = edSecondName.text.toString().trim()

                    val user = User(
                        firstName = firstName,
                        secondName = secondName,
                    )
                    userRepository.insertUser(user)
                    edFirstName.setText("")
                    edSecondName.setText("")

                    showToast("User $firstName $secondName added")
                } else {
                    showToast("Incorrect values")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            .show()

    }
}