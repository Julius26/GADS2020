package com.vesencom.gadsphaseii.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.vesencom.gadsphaseii.R
import com.vesencom.gadsphaseii.models.Skill
import kotlinx.android.synthetic.main.top_skill_iq_layout.view.*

class SkillIQAdapter : RecyclerView.Adapter<SkillIQAdapter.SkillIQViewHolder>() {

    private var skillIqList: List<Skill> = emptyList()

    class SkillIQViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillIQViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.top_skill_iq_layout, parent, false)
        return SkillIQViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SkillIQViewHolder, position: Int) {
        val skillIQ = skillIqList[position]
        holder.itemView.apply {
            imgViewTopBadgeIQ.load(skillIQ.badgeUrl){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            tvNameSkillIq.text = skillIQ.name
            val hoursAndCouttry = "${skillIQ.score}, ${skillIQ.country}"
            tvSkillIqCountry.text = hoursAndCouttry
        }
    }

    override fun getItemCount(): Int = skillIqList.size

    fun updateList(skillIqList: List<Skill>){
        this.skillIqList = skillIqList
        Log.d("adapter", skillIqList.toString())
        notifyDataSetChanged()
    }
}