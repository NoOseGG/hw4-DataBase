package com.example.hw4database.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.hw4database.databinding.DialogFragmentUpdateBinding
import com.example.hw4database.model.User

class DialogUpdate(
    private val user: User
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogFragmentUpdateBinding.inflate(layoutInflater)

        return AlertDialog.Builder(requireContext())
            .setTitle("Edit user")
            .setMessage("Enter new first name or(and) second name")
            .setView(binding.root)
            .setPositiveButton("Yes") { _, _ ->
                with(binding) {

                    val firstName = if (!dialogUpdateFirstName.text.isNullOrBlank()) {
                        binding.dialogUpdateFirstName.text.toString()
                    } else {
                        user.firstName
                    }

                    val secondName = if (!dialogUpdateSecondName.text.isNullOrBlank()) {
                        binding.dialogUpdateSecondName.text.toString()
                    } else {
                        user.secondName
                    }

                    parentFragmentManager.setFragmentResult(
                        REQUEST_KEY,
                        bundleOf(
                            KEY_FIRST_NAME to firstName,
                            KEY_SECOND_NAME to secondName,
                        )
                    )
                }
            }
            .setNegativeButton("No", null)
            .create()
    }

    companion object {
        const val TAG = "DialogDelete"
        const val REQUEST_KEY = "${TAG}REQUEST_KEY"
        const val KEY_FIRST_NAME = "KEY_FIRST_NAME"
        const val KEY_SECOND_NAME = "KEY_SECOND_NAME"
    }
}