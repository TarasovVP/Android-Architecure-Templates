package com.vnstudio.cleanarchitecturedemo.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.vnstudio.cleanarchitecturedemo.AppApplication
import com.vnstudio.cleanarchitecturedemo.databinding.FragmentDetailsBinding
import com.vnstudio.cleanarchitecturedemo.models.Fork
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment(), DetailsViewContract {

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    private val args: DetailsFragmentArgs by navArgs()

    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailsBinding.inflate(LayoutInflater.from(context))
        binding?.backButton?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        AppApplication.instance?.appComponent?.injectDetailsFragment(this)
        detailsPresenter.attachView(this)
        detailsPresenter.getForkById(args.forkId)
        return binding?.root
    }

    override fun setProgressVisibility(showProgress: Boolean) {
        binding?.progressBar?.isVisible = showProgress
    }

    override fun setForkFromDB(fork: Fork) {
       binding?.apply {
            forkName.text = fork.fullName
            ownerName.text = fork.owner?.login
            forkDescription.text = fork.fullName
           context?.let { Glide.with(it).load(fork.owner?.avatarUrl).into(ownerAvatar) }
        }
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        detailsPresenter.detachView()
        super.onDestroyView()
    }

    companion object {
        const val FORK_ID = "forkId"

        @JvmStatic
        fun newInstance(forkId: Long?) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(FORK_ID, forkId)
                }
            }
    }
}