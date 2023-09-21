package com.vnstudio.cleanarchitecturedemo.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.vnstudio.cleanarchitecturedemo.*
import com.vnstudio.cleanarchitecturedemo.databinding.FragmentListBinding
import com.vnstudio.cleanarchitecturedemo.details.DetailsFragment
import com.vnstudio.cleanarchitecturedemo.models.Fork
import javax.inject.Inject

class ListFragment : Fragment(), ListViewContract {

    @Inject
    lateinit var listPresenter: ListPresenter

    private var binding: FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListBinding.inflate(LayoutInflater.from(context))
        AppApplication.instance?.appComponent?.injectListFragment(this)
        listPresenter.attachView(this)
        listPresenter.getForksFromApi()
        return binding?.root
    }

    override fun setProgressVisibility(showProgress: Boolean) {
        binding?.progressBar?.isVisible = showProgress
    }

    override fun setForks(forks: List<Fork>) {
        val ormLiteSqliteDBConnector = activity?.let { OrmLiteSqliteDBConnector(it) }
        val jsonConverter = JsonConverter()
        ormLiteSqliteDBConnector?.insertDataAsync(jsonConverter.forkListToForkDBList(forks), {
            val forkList = ormLiteSqliteDBConnector.getTransformedForks()
            val adapter = ForkAdapter(forkList)
            adapter.setOnForkClickListener(object : OnForkClickListener {
                override fun onForkClick(fork: Fork) {
                    val detailsFragment = DetailsFragment.newInstance(fork)
                    parentFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainer, detailsFragment)
                        addToBackStack(null)
                        commit()
                    }
                }
            })
            binding?.recyclerView?.adapter = adapter
            binding?.progressBar?.isVisible = false
        }, { errorText ->
            showError(errorText)
        })
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        listPresenter.detachView()
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}