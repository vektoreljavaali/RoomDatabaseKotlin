package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.repository.MusteriDAO
import com.example.myapplication.repository.entity.Musteri
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var IslemDb: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Bu DB Create için bu nedenle işleme kapalı
//        var db = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "DbMusteri"
//        ).build()

        IslemDb =  Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "DbMusteri"
        ).allowMainThreadQueries().build()

        btnkayit.setOnClickListener {
            kaydet()
        }

 listele()


    }

    fun listele(){

        val myliste: ArrayList<String> = ArrayList()
        for (item in IslemDb.userDao().getAll()) {
            myliste.add(item.firstName+ " "+ item.lastName)
        }

        liste.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,myliste)

    }

    fun kaydet(){
        var ad = txtad.text.toString()
        var soyad = txtsoyad.text.toString()
        var musteri= Musteri(UUID.randomUUID().toString(), ad, soyad)

        IslemDb.userDao().insertAll(musteri)
    listele()
    }
}

@Database(entities = arrayOf(Musteri::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): MusteriDAO
}