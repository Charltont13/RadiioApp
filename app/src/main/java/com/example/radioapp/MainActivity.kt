package com.example.radioapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RadioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RadioAdapter(getRadios())
        recyclerView.adapter = adapter
    }

    private fun getRadios(): ArrayList<Radio> {
        val radios = ArrayList<Radio>()

        radios.add(Radio("Radio 1: Lite FM", "http://ais-sa2.cdnstream1.com/2224_192.mp3"))
        radios.add(Radio("Radio 2: WNYC-FM", "http://fm939.wnyc.org/wnycfm"))
        radios.add(Radio("Radio 3: HEART FM", "http://media-the.musicradio.com/HeartLondonMP3"))
        radios.add(Radio("Radio 4: Smooth Radio", "http://media-the.musicradio.com/SmoothUKMP3"))
        radios.add(Radio("Radio 5: TalkSPORT", "https://radio.talksport.com/stream?aw_0_1st.collectionid=4527&aw_0_1st.skey=1619979784"))

        return radios
    }
}
