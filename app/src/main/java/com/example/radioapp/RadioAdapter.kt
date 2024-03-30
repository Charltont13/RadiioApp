package com.example.radioapp

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RadioAdapter(private val radios: ArrayList<Radio>) :
    RecyclerView.Adapter<RadioAdapter.RadioViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null

    inner class RadioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvTitle)
        val streamUrl: TextView = itemView.findViewById(R.id.tvFrequency)
        val imageView: ImageView = itemView.findViewById(R.id.ivRadio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.radio_item, parent, false)
        return RadioViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        val radio = radios[position]

        holder.name.text = radio.name
        holder.streamUrl.text = radio.streamUrl
        holder.imageView.setImageResource(getImageResource(radio.name))

        holder.itemView.setOnClickListener {
            mediaPlayer?.reset()
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(radio.streamUrl)
                setOnPreparedListener { start() }
                prepareAsync()
            }
            Toast.makeText(holder.itemView.context, "Playing ${radio.name}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImageResource(radioName: String): Int {
        return when (radioName) {
            "Radio 1: Lite FM" -> R.drawable.radio1
            "Radio 2: WNYC-FM" -> R.drawable.radio2
            "Radio 3: HEART FM" -> R.drawable.radio3
            "Radio 4: Smooth Radio" -> R.drawable.radio4
            "Radio 5: TalkSPORT" -> R.drawable.radio5
            else -> R.drawable.ic_launcher_background // Placeholder for default image
        }
    }

    override fun getItemCount(): Int {
        return radios.size
    }

    override fun onViewRecycled(holder: RadioViewHolder) {
        super.onViewRecycled(holder)
        // Stop and release media player when the view is recycled
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
