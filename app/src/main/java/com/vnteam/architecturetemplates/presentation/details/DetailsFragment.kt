<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/presentation/details/DetailsFragment.kt
package com.vnteam.architecturetemplates.presentation.details
========
package com.vnteam.architecturetemplates.details
>>>>>>>> master:app/src/main/java/com/vnteam/architecturetemplates/details/DetailsFragment.kt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/presentation/details/DetailsFragment.kt
import coil.load
========
import com.bumptech.glide.Glide
>>>>>>>> master:app/src/main/java/com/vnteam/architecturetemplates/details/DetailsFragment.kt
import com.vnteam.architecturetemplates.R
import com.vnteam.architecturetemplates.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val detailsViewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()
    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailsBinding.inflate(LayoutInflater.from(context))
        observeLiveData()
        binding?.backButton?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        detailsViewModel.getForkById(args.forkId)
        return binding?.root
    }

    private fun observeLiveData() {
        with(detailsViewModel) {
            progressVisibilityLiveData.observe(viewLifecycleOwner) { showProgress ->
                binding?.progressBar?.isVisible = showProgress
            }
            errorLiveData.observe(viewLifecycleOwner) { errorMessage ->
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
            forkLiveData.observe(viewLifecycleOwner) { fork ->
                binding?.apply {
                    forkName.text = fork.name
                    ownerName.text = fork.owner?.login
                    forkDescription.text = fork.description
                    ownerAvatar.load(fork.owner?.avatarUrl) {
                        crossfade(true)
                        placeholder(R.drawable.ic_person)
                        error(R.drawable.ic_person)
                    }
                }
            }
        }
    }
}