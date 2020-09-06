package com.vesencom.gadsphaseii.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vesencom.gadsphaseii.R
import com.vesencom.gadsphaseii.adapters.LearnersAdapter
import com.vesencom.gadsphaseii.models.Learner
import com.vesencom.gadsphaseii.network.ApiClients
import com.vesencom.gadsphaseii.network.ApiService
import com.vesencom.gadsphaseii.network.state.NetworkState
import com.vesencom.gadsphaseii.utils.hide
import com.vesencom.gadsphaseii.utils.show
import kotlinx.android.synthetic.main.fragment_top_learners.*
import kotlinx.coroutines.launch
import java.io.IOException

class TopLearners : Fragment(R.layout.fragment_top_learners) {

    private val apiService =
        ApiClients().getClient().create(ApiService::class.java)
    private val learnersAdapter: LearnersAdapter by lazy { LearnersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewId.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewId.adapter = learnersAdapter
        /*lifecycleScope.launch {
            val result = apiService.getHours().body()!!
            learnersAdapter.updateList(result)

        }*/

        getLearners()
        lifecycleScope.launchWhenStarted {
            swipeContainer.setOnRefreshListener {
                getLearners()
            }
        }
    }

    private fun getLearners() {
        lifecycleScope.launch {
            hideEmptyView()
            showRefreshDialog()
            val learnersResult = fetchLearners()
            handleLearnersResult(learnersResult)
        }
    }

    private suspend fun fetchLearners(): NetworkState {
        return try {
            val response = apiService.getHours()
//            val learnerResponseModel = response.body()
            if (response.isSuccessful) {
                NetworkState.Success(response.body()!!)
            } else {
                when (response.code()) {
                    403 -> NetworkState.HttpErrors.ResourceForbidden(response.message())
                    404 -> NetworkState.HttpErrors.ResourceNotFound(response.message())
                    500 -> NetworkState.HttpErrors.InternalServerError(response.message())
                    502 -> NetworkState.HttpErrors.BadGateWay(response.message())
                    301 -> NetworkState.HttpErrors.ResourceRemoved(response.message())
                    302 -> NetworkState.HttpErrors.RemovedResourceFound(response.message())
                    else -> {
                        NetworkState.Error(response.message())
                    }
                }
            }
        } catch (error: IOException) {
            NetworkState.NetworkException(error.message!!)
        }

    }

    private fun handleLearnersResult(networkState: NetworkState){
        return when(networkState) {
            is NetworkState.Success -> showLearners(networkState.data)
            is NetworkState.HttpErrors.ResourceForbidden -> handleError(networkState.exception)
            is NetworkState.HttpErrors.ResourceNotFound -> handleError(networkState.exception)
            is NetworkState.HttpErrors.InternalServerError -> handleError(networkState.exception)
            is NetworkState.HttpErrors.BadGateWay -> handleError(networkState.exception)
            is NetworkState.HttpErrors.ResourceRemoved -> handleError(networkState.exception)
            is NetworkState.HttpErrors.RemovedResourceFound -> handleError(networkState.exception)

            is NetworkState.InvalidData -> showEmptyView()
            is NetworkState.Error -> handleError(networkState.error)
            is NetworkState.NetworkException -> handleError(networkState.error)
        }
    }

    private fun handleError(message : String){
        hideRefreshDialog()
        errorMessageText.text = message
    }

    private fun showLearners(learners: List<Learner>){
        hideEmptyView()
        Log.d("api", learners.toString())
        Log.d("api", learners[1].toString())
        learnersAdapter.updateList(learners)
    }

    private fun showEmptyView(){
        errorMessageText.show()
        recyclerViewId.hide()
        hideRefreshDialog()
    }

    private fun hideEmptyView(){
        errorMessageText.hide()
        recyclerViewId.show()
        hideRefreshDialog()
    }

    private fun showRefreshDialog(){
        swipeContainer.isRefreshing = true
    }

    private fun hideRefreshDialog(){
        swipeContainer.isRefreshing = false
    }


}