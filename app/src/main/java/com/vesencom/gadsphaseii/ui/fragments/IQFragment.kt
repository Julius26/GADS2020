package com.vesencom.gadsphaseii.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vesencom.gadsphaseii.R
import com.vesencom.gadsphaseii.adapters.SkillIQAdapter
import com.vesencom.gadsphaseii.models.Skill
import com.vesencom.gadsphaseii.network.ApiClients
import com.vesencom.gadsphaseii.network.ApiService
import com.vesencom.gadsphaseii.network.state.NetworkStateIQ
import com.vesencom.gadsphaseii.utils.hide
import com.vesencom.gadsphaseii.utils.show
import kotlinx.android.synthetic.main.fragment_i_q.*
import kotlinx.coroutines.launch
import java.io.IOException


class IQFragment : Fragment(R.layout.fragment_i_q) {

    private val apiService =
        ApiClients().getClient().create(ApiService::class.java)

    private val skillIQViewAdapter: SkillIQAdapter by lazy{ SkillIQAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewSkillIq.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewSkillIq.adapter = skillIQViewAdapter

        getSkillIq()
        lifecycleScope.launchWhenStarted {
            swipeContainer.setOnRefreshListener {
                getSkillIq()
            }
        }
    }

    private fun getSkillIq() {
        lifecycleScope.launch {
            hideEmptyView()
            showRefreshDialog()
            val skillIqResult = fetchSkillIQ()
            handleSkillIQResult(skillIqResult)
        }
    }

    private suspend fun fetchSkillIQ(): NetworkStateIQ {
        return try {
            val response = apiService.getSkillIq()

            if (response.isSuccessful) {
                NetworkStateIQ.Success(response.body()!!)
            } else {
                when (response.code()) {
                    403 -> NetworkStateIQ.HttpErrors.ResourceForbidden(response.message())
                    404 -> NetworkStateIQ.HttpErrors.ResourceNotFound(response.message())
                    500 -> NetworkStateIQ.HttpErrors.InternalServerError(response.message())
                    502 -> NetworkStateIQ.HttpErrors.BadGateWay(response.message())
                    301 -> NetworkStateIQ.HttpErrors.ResourceRemoved(response.message())
                    302 -> NetworkStateIQ.HttpErrors.RemovedResourceFound(response.message())
                    else -> {
                        NetworkStateIQ.Error(response.message())
                    }
                }
            }
        } catch (error: IOException) {
            NetworkStateIQ.NetworkException(error.message!!)
        }
    }

    private fun handleSkillIQResult(skillIqStateIQState: NetworkStateIQ) {
        return when(skillIqStateIQState){
            is NetworkStateIQ.Success -> showSkillIQ(skillIqStateIQState.data)
            is NetworkStateIQ.HttpErrors.ResourceForbidden -> handleError(skillIqStateIQState.exception)
            is NetworkStateIQ.HttpErrors.ResourceNotFound -> handleError(skillIqStateIQState.exception)
            is NetworkStateIQ.HttpErrors.InternalServerError -> handleError(skillIqStateIQState.exception)
            is NetworkStateIQ.HttpErrors.BadGateWay -> handleError(skillIqStateIQState.exception)
            is NetworkStateIQ.HttpErrors.ResourceRemoved -> handleError(skillIqStateIQState.exception)
            is NetworkStateIQ.HttpErrors.RemovedResourceFound -> handleError(skillIqStateIQState.exception)

            is NetworkStateIQ.InvalidData -> showEmptyView()
            is NetworkStateIQ.Error -> handleError(skillIqStateIQState.error)
            is NetworkStateIQ.NetworkException -> handleError(skillIqStateIQState.error)
        }
    }

    private fun showSkillIQ(skills: List<Skill>) {
        hideEmptyView()
        skillIQViewAdapter.updateList(skills)
    }

    private fun showEmptyView() {
        errorMessageText.show()
        recyclerViewSkillIq.hide()
        hideRefreshDialog()
    }

    private fun handleError(message: String) {
        hideRefreshDialog()
        errorMessageText.text = message
    }




    private fun showRefreshDialog() {
        swipeContainer.isRefreshing = true
    }

    private fun hideEmptyView() {
        errorMessageText.hide()
        recyclerViewSkillIq.show()
        hideRefreshDialog()
    }

    private fun hideRefreshDialog(){
        swipeContainer.isRefreshing = false
    }


}