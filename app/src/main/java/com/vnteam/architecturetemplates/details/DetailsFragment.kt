package com.vnteam.architecturetemplates.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import com.vnteam.architecturetemplates.AppApplication
import com.vnteam.architecturetemplates.databinding.FragmentDetailsBinding
import com.vnteam.architecturetemplates.models.Fork
import javax.inject.Inject

class DetailsFragment : Fragment(), DetailsViewContract {

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

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
        val forkId =  arguments?.getLong(FORK_ID)
        detailsPresenter.getForkById(forkId)
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
            Picasso.get().load(fork.owner?.avatarUrl).into(ownerAvatar)
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