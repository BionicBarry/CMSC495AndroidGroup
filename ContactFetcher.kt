package com.example.samplechatapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.support.v4.content.CursorLoader
import java.util.*

class ContactFetcher(private val context: Context) {

    fun fetchAll(
        cursorLoader: CursorLoader,
        phoneCursorLoader: CursorLoader,
        emailCursorLoader: CursorLoader
    ): ArrayList<Contact> {
        val listContacts = ArrayList<Contact>()

        val c = cursorLoader.loadInBackground()

        val contactsMap = HashMap<String, Contact>(c!!.count)

        if (c.moveToFirst()) {

            val idIndex = c.getColumnIndex(ContactsContract.Contacts._ID)
            val nameIndex = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

            do {
                val contactId = c.getString(idIndex)
                val contactDisplayName = c.getString(nameIndex)
                val contact = Contact(contactId, contactDisplayName)
                contactsMap[contactId] = contact
                listContacts.add(contact)
            } while (c.moveToNext())
        }

        c.close()

        matchContactNumbers(phoneCursorLoader, contactsMap)
        matchContactEmails(emailCursorLoader, contactsMap)

        return listContacts
    }

    private fun matchContactNumbers(cursorLoader: CursorLoader, contactsMap: Map<String, Contact>) {
        val phone = cursorLoader.loadInBackground()

        if (phone!!.moveToFirst()) {
            val contactNumberColumnIndex = phone.getColumnIndex(Phone.NUMBER)
            val contactTypeColumnIndex = phone.getColumnIndex(Phone.TYPE)
            val contactIdColumnIndex = phone.getColumnIndex(Phone.CONTACT_ID)

            while (!phone.isAfterLast) {
                val number = phone.getString(contactNumberColumnIndex)
                val contactId = phone.getString(contactIdColumnIndex)
                val contact = contactsMap[contactId] ?: continue
                val type = phone.getInt(contactTypeColumnIndex)
                val customLabel = "Custom"
                val phoneType = Phone.getTypeLabel(context.resources, type, customLabel)
                contact.addNumber(number, phoneType.toString())
                phone.moveToNext()
            }
        }

        phone.close()
    }

    fun matchContactEmails(cursorLoader: CursorLoader, contactsMap: Map<String, Contact>) {
        // Get email

        val email = cursorLoader.loadInBackground()

        if (email!!.moveToFirst()) {
            val contactEmailColumnIndex = email.getColumnIndex(Email.DATA)
            val contactTypeColumnIndex = email.getColumnIndex(Email.TYPE)
            val contactIdColumnsIndex = email.getColumnIndex(Email.CONTACT_ID)

            while (!email.isAfterLast) {
                val address = email.getString(contactEmailColumnIndex)
                val contactId = email.getString(contactIdColumnsIndex)
                val type = email.getInt(contactTypeColumnIndex)
                val customLabel = "Custom"
                val contact = contactsMap[contactId] ?: continue
                val emailType = Email.getTypeLabel(context.resources, type, customLabel)
                contact.addEmail(address, emailType.toString())
                email.moveToNext()
            }
        }

        email.close()
    }


    companion object {
        fun fetchAllAsync(context: Context, contactListener: ContactListener) {
            ContactReaderTask(context.applicationContext, contactListener).execute()
        }
    }
}

private class ContactReaderTask(@SuppressLint("StaticFieldLeak") private val context: Context, private val contactListener: ContactListener) :
    AsyncTask<Void, Void, ArrayList<Contact>>() {
    lateinit var cursorLoader: CursorLoader
    lateinit var emailCursorLoader: CursorLoader
    lateinit var phoneCursorLoader: CursorLoader
    override fun onPreExecute() {
        val projectionFields = arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME)
        cursorLoader = CursorLoader(
            context,
            ContactsContract.Contacts.CONTENT_URI,
            projectionFields, null, null, null// the sort order (default)
        )
        val emailProjection = arrayOf(Email.DATA, Email.TYPE, Email.CONTACT_ID)
        emailCursorLoader = CursorLoader(
            context,
            Email.CONTENT_URI,
            emailProjection, null, null, null
        )
        val numberProjection = arrayOf(Phone.NUMBER, Phone.TYPE, Phone.CONTACT_ID)
        phoneCursorLoader = CursorLoader(
            context,
            Phone.CONTENT_URI,
            numberProjection, null, null, null
        )
    }

    override fun doInBackground(vararg params: Void?): ArrayList<Contact> {
        return ContactFetcher(context).fetchAll(cursorLoader, phoneCursorLoader, emailCursorLoader)
    }

    override fun onPostExecute(list: ArrayList<Contact>) {
        contactListener.onContactRead(list)
    }
}

interface ContactListener {
    fun onContactRead(list: ArrayList<Contact>)
}
