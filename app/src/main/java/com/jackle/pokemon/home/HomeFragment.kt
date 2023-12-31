package com.jackle.pokemon.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jackle.pokemon.TrainerNameDialog
import com.jackle.pokemon.MainViewModel
import com.jackle.pokemon.R
import com.jackle.pokemon.databinding.FragmentHomeBinding
import com.jackle.pokemon.detail.PokemonDetailActivity
import com.jackle.pokemon.model.Pokemon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeUiState()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClickView()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collect {
                if (it == null) return@collect

                context?.toast("GOTCHA!")
                binding.pokeBallAnimate.visibility = View.GONE
                navigateToDetail(it)
            }
        }
    }

    private fun handleClickView() {
        binding.pokeBall.setOnClickListener {
            binding.pokeBallAnimate.visibility = View.VISIBLE
            viewModel.getPokemonList()
        }

        binding.bagPack.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_pokemonCollectionFragment)
        }

        binding.trainer.setOnClickListener {
            TrainerNameDialog.newInstance().apply {
                onOkClickListener = { getTrainerName() }
            }.show(parentFragmentManager, TrainerNameDialog::class.java.simpleName)
        }
    }

    private fun navigateToDetail(detail: Pokemon) {
        Log.d(">>>", "navigate")
        findNavController().navigate(
            R.id.action_homeFragment_to_pokemonDetailFragment,
            bundleOf(
                PokemonDetailActivity.POKEMON_DETAIL to detail
            )
        )
    }

    private fun getTrainerName() {
//        val sharedPref =
//            context?.getSharedPreferences(
//                CustomDialog.KEY_SHARED_PREFERENCES_NAME,
//                Context.MODE_PRIVATE
//            ) ?: return
//        val trainerName = sharedPref.getString(CustomDialog.KEY_TRAINER_NAME, "")
//
//        if (!trainerName.isNullOrEmpty()) {
//            context?.toast("Trainer name is $trainerName")
//        }
    }

    private fun Context.toast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}