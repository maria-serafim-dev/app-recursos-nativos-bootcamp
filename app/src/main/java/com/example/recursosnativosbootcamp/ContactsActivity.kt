package com.example.recursosnativosbootcamp

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recursosnativosbootcamp.adapter.ContactAdapter
import com.example.recursosnativosbootcamp.databinding.ActivityContactsBinding
import com.example.recursosnativosbootcamp.model.Contact

class ContactsActivity : AppCompatActivity() {
    private val REQUEST_CONTACT = 1
    private lateinit var binding: ActivityContactsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        checkPermission()
        setContact()
    }



    private fun checkPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CONTACT)
        }else{
            setContact()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CONTACT) setContact()
    }


    private fun setContact() {
        val listPhone = mutableListOf<Contact>()

        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)

        if(cursor != null){
            while(cursor.moveToNext()){
                listPhone.add(Contact(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)))
                )
            }
            cursor.close()
        }
        val adapter = ContactAdapter(listPhone)
        binding.rvContacts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false )
        binding.rvContacts.adapter = adapter

    }

}