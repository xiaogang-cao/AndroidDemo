package com.example.servicedemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MusicService : Service() {

    companion object{
        private const val TAG = "MusicService"
    }
    
    inner class MyBinder: Binder(){
        fun getService(): MusicService{
            return this@MusicService
        }
    }

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "onCreate: ")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return MyBinder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }
    
    fun playMusic(){
        Log.d(TAG, "playMusic: ")
    }
    
    fun stopMusic(){
        Log.d(TAG, "stopMusic: ")
    }
}