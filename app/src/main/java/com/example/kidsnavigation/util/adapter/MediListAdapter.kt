package com.example.kidsnavigation.util.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.kidsnavigation.R
import com.example.kidsnavigation.database.entity.MedikamentMitEinnahmeZeit

class MediListAdapter : Adapter<MediListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var mediList: List<MedikamentMitEinnahmeZeit> = ArrayList()

    interface OnItemClickListener {
        fun onItemClick(medikament: MedikamentMitEinnahmeZeit)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout_medikamente,
            parent,
            false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMedikamentMitEinnahmeZeit = mediList[position]
        val itemView = holder.itemView as ConstraintLayout
        val tvTime = itemView.getViewById(R.id.tv_med_time) as TextView
        val tvName = itemView.getViewById(R.id.tv_med_name) as TextView
        val tvDose = itemView.getViewById(R.id.tv_med_dose) as TextView

        tvTime.text = currentMedikamentMitEinnahmeZeit.zeit
        tvDose.text = currentMedikamentMitEinnahmeZeit.dosis.toString()
        tvName.text = currentMedikamentMitEinnahmeZeit.medikament.name
    }

    override fun getItemCount() = mediList.size

    fun setMediList(medis: List<MedikamentMitEinnahmeZeit>) {
        mediList = medis
        notifyDataSetChanged()
    }
}