package com.example.hw4database.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

class DialogDelete : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(requireContext())
            .setTitle("Delete user")
            .setMessage("Do you want delete the user?")
            .setPositiveButton("Yes") { _, which ->
                parentFragmentManager.setFragmentResult(
                    REQUEST_KEY,
                    bundleOf(KEY_RESPONSE to which)
                )
            }
            .setNegativeButton("No", null)
            .create()
    }

    companion object {
        const val TAG = "DeleteDialog"
        const val REQUEST_KEY = "${TAG}REQUEST_KEY"
        const val KEY_RESPONSE = "KEY_RESPONSE"
    }
}