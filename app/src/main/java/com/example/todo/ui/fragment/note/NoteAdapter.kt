package com.example.todo.ui.fragment.note

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todo.databinding.ItemNoteBinding
import com.example.todo.model.NoteModel
import com.example.todo.ui.App


class NoteAdapter(private val listener: NoteListener) : Adapter<NoteAdapter.NoteViewHolder>() {

    private val list : ArrayList<NoteModel> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list : List<NoteModel>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortList(list : List<NoteModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun deleteNote(position : Int) {
        App.db.getDao().deleteNote(list.removeAt(position))
        notifyItemRemoved(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnClickListener {
            listener.upDateNote(list[position])
            deleteNote(holder.adapterPosition)
        }
    }

    override fun getItemCount() = list.size

    class NoteViewHolder(private val binding: ItemNoteBinding) : ViewHolder(binding.root) {
        fun onBind(model: NoteModel) {
            binding.tvItemTitle.text = model.title
            binding.tvItemDescription.text = model.description
            binding.tvItemTime.text = model.dateTime
        }
    }

    interface NoteListener {
        fun upDateNote(model: NoteModel)
    }


}