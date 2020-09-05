package com.vesencom.gadsphaseii.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vesencom.gadsphaseii.R
import com.vesencom.gadsphaseii.models.Learner
import kotlinx.android.synthetic.main.top_learners_layout.view.*

class LearnersAdapter : RecyclerView.Adapter<LearnersAdapter.LearnersViewHolder>() {

        private var learnersList: List<Learner> = emptyList()
//
    class LearnersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.top_learners_layout, parent, false)
        return  LearnersViewHolder(itemView)
    }

    override fun getItemCount(): Int = learnersList.size


    override fun onBindViewHolder(holder: LearnersViewHolder, position: Int) {
        val learner = learnersList[position]
        holder.itemView.apply {
            Picasso.get().load(learner.badgeUrl).into(imgViewTopBadge)
            tvName.text = learner.name
            val hoursAndCountry = "${learner.hours} , ${learner.country}"
            tvHoursCountry.text = hoursAndCountry
        }

    }

    fun updateList(learnerList: List<Learner>){
        this.learnersList = learnerList
        Log.d("adapter", learnerList.toString())
        notifyDataSetChanged()
    }

}
