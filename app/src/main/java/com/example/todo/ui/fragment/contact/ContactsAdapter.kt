package com.example.todo.ui.fragment.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todo.databinding.ItemContactsBinding
import com.example.todo.model.ContactsModel

class ContactsAdapter(private val listener : ClickListener,
                      private val contacts : ArrayList<ContactsModel>)
    : Adapter<ContactsAdapter.ContactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(ItemContactsBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.onBind(position)

        holder.itemView.setOnClickListener {
            if (holder.binding.contactsContainer.isGone) {
                holder.binding.contactsContainer.isVisible = true
            } else holder.binding.contactsContainer.isGone = true
        }

        holder.binding.btnCall.setOnClickListener {
            listener.callListener(position)
        }

        holder.binding.btnWhatsapp.setOnClickListener {
            listener.waListener(position)
        }
    }

    override fun getItemCount() = contacts.size

    inner class ContactsViewHolder(var binding : ItemContactsBinding) : ViewHolder(binding.root){
        fun onBind(position: Int) {
            binding.tvItemContactName.text = contacts[position].name.toString()
            binding.tvItemContactNumber.text = contacts[position].number.toString()
        }


    }

    interface ClickListener {
        fun callListener(position : Int)
        fun waListener(position: Int)
    }

}