package com.jackle.pokemon

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import com.jackle.pokemon.databinding.CustomDialogViewBinding

class TrainerNameDialog : DialogFragment() {

    private lateinit var binding: CustomDialogViewBinding

    private var trainerName: String? = null

    var onOkClickListener: (() -> Unit)? = null

    companion object {
        fun newInstance(): TrainerNameDialog = TrainerNameDialog()
        const val KEY_TRAINER_NAME = "pokemon_trainer_name"
    }

    private var sharedPref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CustomDialogViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        sharedPref =
//            activity?.getSharedPreferences(KEY_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

        initial()
        bindingAction()
        bindingCurrentName()
    }

    private fun initial() {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun bindingAction() = with(binding) {
        saveButton.setOnClickListener {
            saveTrainerName()
            onOkClickListener?.invoke()
            dismiss()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }

        editText.doOnTextChanged { text, _, _, _ ->
            trainerName = text.toString()
        }
    }

    private fun bindingCurrentName() {
        val currentTrainerName = sharedPref?.getString(KEY_TRAINER_NAME, "")
        if (!currentTrainerName.isNullOrEmpty()) {
            binding.editText.setText(currentTrainerName)
        }
    }

    private fun saveTrainerName() {
        if (trainerName.isNullOrEmpty()) return

//        val sharedPref = activity?.getSharedPreferences(KEY_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE) ?: return
//        with(sharedPref.edit()) {
//            putString(KEY_TRAINER_NAME, trainerName)
//            apply()
//        }
    }
}