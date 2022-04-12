package com.example.servicedemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.example.servicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var connection: ServiceConnection
    lateinit var musicService: MusicService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playBtn.setOnClickListener{
            musicService.playMusic()
        }
        binding.stopBtn.setOnClickListener{
            musicService.stopMusic()
        }

        connection = object: ServiceConnection{
            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                val binder: MusicService.MyBinder = p1 as MusicService.MyBinder
                musicService = binder.getService()
            }

            override fun onServiceDisconnected(p0: ComponentName?) {

            }

            override fun onBindingDied(name: ComponentName?) {
                super.onBindingDied(name)
            }

            override fun onNullBinding(name: ComponentName?) {
                super.onNullBinding(name)
            }
        }
    }

    override fun onStart() {
       super.onStart()

        val intent = Intent(this, MusicService::class.java)
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
}