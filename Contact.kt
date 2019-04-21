package com.example.samplechatapplication

import java.util.ArrayList

class Contact(var id: String, var name: String) {
    var emails: ArrayList<ContactEmail> = ArrayList()
    var numbers: ArrayList<ContactPhone> = ArrayList()

    override fun toString(): String {
        var result = name
        if (numbers.size > 0) {
            val number = numbers[0]
            result += " (" + number.number + " - " + number.type + ")"
        }
        if (emails.size > 0) {
            val email = emails[0]
            result += " [" + email.address + " - " + email.type + "]"
        }
        return result
    }

    fun addEmail(address: String, type: String) {
        emails.add(ContactEmail(address, type))
    }

    fun addNumber(number: String, type: String) {
        numbers.add(ContactPhone(number, type))
    }
}
