package com.example.samplechatapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*

/*
this class is used to bind all the contacts which has been fetched from the contact app
 */
class ContactsAdapter(context: Context, contacts: ArrayList<Contact>, private val listener: ContactClickListener) :
    ArrayAdapter<Contact>(context, 0, contacts) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get the data item
        val contact = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        var view = convertView
        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.contact_list_item, parent, false)
        }
        // Populate the data into the template view using the data object
        val tvName = view?.findViewById(R.id.tvName) as TextView
        val tvEmail = view.findViewById(R.id.tvEmail) as TextView
        val tvPhone = view.findViewById(R.id.tvPhone) as TextView
        tvName.text = contact?.name
        tvEmail.text = ""
        tvPhone.text = ""
        if (contact.emails.size > 0 && contact.emails[0] != null) {
            tvEmail.text = contact.emails[0].address
        }
        if (contact.numbers.size > 0 && contact.numbers[0] != null) {
            tvPhone.text = contact.numbers[0].number
        }
        view.setOnClickListener {
            listener.onContactClick(contact)
        }
        return view
    }
}

interface ContactClickListener {
    fun onContactClick(contact: Contact)
}
