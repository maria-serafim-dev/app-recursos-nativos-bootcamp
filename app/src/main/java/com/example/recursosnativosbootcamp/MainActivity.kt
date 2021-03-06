package com.example.recursosnativosbootcamp

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import androidx.appcompat.app.AppCompatActivity
import com.example.recursosnativosbootcamp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickListenerCalendar()
        onClickListenerPhoneNumber()
        onClickListenerPhotos()
        onClickListenerLocation()
    }

    private fun onClickListenerCalendar() {
        binding.setEvent.setOnClickListener {
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, "Bootcamp everts")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "on line")
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis())
                .putExtra(
                    CalendarContract.EXTRA_EVENT_END_TIME,
                    System.currentTimeMillis() + (60 * 60 * 1000)
                )

            startActivity(intent)

        }
    }

    private fun onClickListenerPhoneNumber() {
        binding.setContact.setOnClickListener {
            val intent = Intent(this, ContactsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onClickListenerPhotos() {
        binding.setPhoto.setOnClickListener {
            val intent = Intent(this, PhotoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onClickListenerLocation() {
        binding.setLocation.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }
}