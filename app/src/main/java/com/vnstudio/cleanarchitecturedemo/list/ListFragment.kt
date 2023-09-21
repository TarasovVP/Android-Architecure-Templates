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

class ListFragment : Fragment() {

    private var binding: FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListBinding.inflate(LayoutInflater.from(context))
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.progressBar?.isVisible = true
        val ormLiteSqliteDBConnector = activity?.let { OrmLiteSqliteDBConnector(it) }
        val httpClientConnector = OkHttpClientConnector()
        httpClientConnector.makeHttpUrlConnection({ responseData ->
            val jsonConverter = JsonConverter()
            responseData?.let {
                val forks = jsonConverter.getForkList(responseData)
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
                    Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
                })
            }
        }, { errorText ->
            Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}