package com.example.myapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter (private val people: List<CauHoi>) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cauhoi: TextView = itemView.findViewById(R.id.cauhoi)
        val dapan1: TextView = itemView.findViewById(R.id.dapan1)
        val dapan2: TextView = itemView.findViewById(R.id.dapan2)
        val dapan3: TextView = itemView.findViewById(R.id.dapan3)
        val dapan4: TextView = itemView.findViewById(R.id.dapan4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = people[position]
        holder.cauhoi.text = person.CAUHOI
        holder.dapan1.text = person.DAPAN1
        holder.dapan2.text = person.DAPAN2
        holder.dapan3.text = person.DAPAN3
        holder.dapan4.text = person.DAPAN4

        when (person.DUNG) {
            1 -> holder.dapan1.setTextColor(Color.RED)
            2 -> holder.dapan2.setTextColor(Color.RED)
            3 -> holder.dapan3.setTextColor(Color.RED)
            4 -> holder.dapan4.setTextColor(Color.RED)

            else -> println("Invalid day")
        }
    }

    override fun getItemCount(): Int {
        return people.size
    }
}