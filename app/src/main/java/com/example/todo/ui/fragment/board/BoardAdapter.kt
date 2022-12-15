package com.example.todo.ui.fragment.board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todo.R
import com.example.todo.databinding.ItemBoardBinding

class BoardAdapter :
    Adapter<BoardAdapter.BoardViewHolder>() {

    val titleList = listOf("Notes", "Contacts", "End")
    val desList = listOf("Add something for notes", "View all contacts", "The end")
    val animationList = listOf(
        R.raw.animation_notes,
        R.raw.animation_contacts,
        R.raw.animation_end
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        return BoardViewHolder(ItemBoardBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = titleList.size

    inner class BoardViewHolder(val binding: ItemBoardBinding) : ViewHolder(binding.root) {
        fun onBind(position: Int) {
            binding.avItemBoard.setAnimation(animationList[position])
            binding.tvItemTitleBoard.text = titleList[position]
            binding.tvItemDescriptionBoard.text = desList[position]
        }

    }


}