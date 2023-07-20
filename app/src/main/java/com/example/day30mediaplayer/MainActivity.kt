package com.example.day30mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer : MediaPlayer
    var totalTime = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnPlay = findViewById<ImageView>(R.id.play)
        val btnPause = findViewById<ImageView>(R.id.pause)
        val btnStop = findViewById<ImageView>(R.id.stop)
        val seekBarMusic = findViewById<SeekBar>(R.id.seekBar)

        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.setVolume(/* leftVolume = */ 1f,/* rightVolume = */ 1f)
        totalTime = mediaPlayer.duration

        btnPlay.setOnClickListener{
            mediaPlayer.start()
        }
        btnPause.setOnClickListener{
            mediaPlayer.pause()
        }
        btnStop.setOnClickListener{
            mediaPlayer?.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }

//         when user changes time stamp of music , reflect that change

        seekBarMusic.max = totalTime
        seekBarMusic.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    mediaPlayer.seekTo(progress)

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}


        })

        // change the seekbar position based on the music

        val handler = Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {
                try{

                seekBarMusic.progress =mediaPlayer.currentPosition
                handler.postDelayed(this,1000)
                }catch (exception : java.lang.Exception ){
                    seekBarMusic.progress = 0
                }
            }

        },0)

    }


}