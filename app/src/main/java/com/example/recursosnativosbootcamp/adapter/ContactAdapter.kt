package com.example.recursosnativosbootcamp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recursosnativosbootcamp.databinding.ItemContactBinding
import com.example.recursosnativosbootcamp.model.Contact

class ContactAdapter(val contactsList : List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private lateinit var binding: ItemContactBinding

    class ViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(contact: Contact) {
            with(binding){
                tvContactName.text = contact.name
                tvContactPhoneNumber.text = contact.phoneNumber
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(contactsList[position])
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }
}