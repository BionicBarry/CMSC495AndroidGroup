package com.example.samplechatapplication

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_contacts.*
import java.util.*

class ContactsActivity : AppCompatActivity() {
    lateinit var listContacts: ArrayList<Contact>
    lateinit var lvContacts: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "My Contacts"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        loadContacts()
        lvContacts = findViewById(R.id.contactList)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadContacts() {
        progressBar?.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS
            )
        } else {
            ContactFetcher.fetchAllAsync(this, object : ContactListener {
                override fun onContactRead(list: ArrayList<Contact>) {
                    listContacts = list
                    val adapterContacts =
                        ContactsAdapter(lvContacts.context, listContacts, object : ContactClickListener {
                            override fun onContactClick(contact: Contact) {
                                val intent = Intent()
                                intent.putExtra("phone", contact.numbers[0].number)
                                intent.putExtra("name", contact.name)
                                setResult(Activity.RESULT_OK, intent)
                                finish()
                            }
                        })
                    lvContacts.adapter = adapterContacts
                    progressBar?.visibility = View.GONE
                }
            })
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts()
            }
        }
    }

    companion object {
        const val PERMISSIONS_REQUEST_READ_CONTACTS = 100
    }
}
