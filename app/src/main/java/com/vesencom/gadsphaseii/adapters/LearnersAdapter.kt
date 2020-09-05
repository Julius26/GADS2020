package com.vesencom.gadsphaseii.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.vesencom.gadsphaseii.R
import com.vesencom.gadsphaseii.models.Learner
import kotlinx.android.synthetic.main.top_learners_layout.view.*

class LearnersAdapter() : RecyclerView.Adapter<LearnersAdapter.LearnersViewHolder>() {

        private var learnersList: List<Learner> = ArrayList()
//
    class LearnersViewHolder(itemview: View) :
        RecyclerView.ViewHolder(itemview) {
        private val imageViewLearnerBadge: ImageView = itemview.imgViewTopBadge
        private val textVName: TextView = itemview.tvName
        private val hoursCountry: TextView = itemview.tvHoursCountry

        fun bindLearners(learner: Learner) {
            with(learner) {
                imageViewLearnerBadge.load(badgeUrl)
                textVName.text = name
                val hours = hours.toString()
                val country = country
                hoursCountry.text = "$hours , $country"
            }
        }
//
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.top_learners_layout, parent, false)
        return  LearnersViewHolder(itemView)
    }

    override fun getItemCount(): Int = learnersList.size


    override fun onBindViewHolder(holder: LearnersViewHolder, position: Int) {
//        bind with the array
        holder.bindLearners(learnersList[position])
    }

    fun updateList(learnerList: List<Learner>){
        learnersList = learnerList
        notifyDataSetChanged()
    }

}
