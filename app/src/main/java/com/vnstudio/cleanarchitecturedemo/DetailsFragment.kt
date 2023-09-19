package com.vnstudio.cleanarchitecturedemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.vnstudio.cleanarchitecturedemo.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val fork =  arguments?.getSerializable(FORK) as? Fork
        FragmentDetailsBinding.inflate(LayoutInflater.from(context)).apply {
            forkName.text = fork?.fullName
            ownerName.text = fork?.owner?.login
            forkDescription.text = fork?.fullName
            Picasso.get().load(fork?.owner?.avatarUrl).into(ownerAvatar)
            backButton.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            return root
        }
    }

    companion object {
        const val FORK = "fork"

        @JvmStatic
        fun newInstance(fork: Fork) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(FORK, fork)
                }
            }
    }
}