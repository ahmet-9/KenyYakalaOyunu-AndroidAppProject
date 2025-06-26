package com.ahmetbadem.myapplication

import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Looper
import android.renderscript.ScriptGroup.Binding
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ahmetbadem.myapplication.databinding.ActivityMainBinding
import java.lang.Math.random
import java.util.logging.Handler
import kotlin.random.Random as Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable{}
    var handler = android.os.Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

    hideImages()

         object : CountDownTimer(15000, 1000){
             override fun onTick(p0: Long) {
                 binding.timeText.text = "Time:${p0/1000}"
             }

             override fun onFinish() {
                binding.timeText.text = "Time:0"
                 handler.removeCallbacks(runnable)

                 for (image in imageArray){
                     image.visibility = View.INVISIBLE
                 }

                 val alert = AlertDialog.Builder(this@MainActivity)
                 alert.setTitle("Game Over")
                 alert.setMessage("Restart the Game")

                 alert.setPositiveButton("yes", DialogInterface.OnClickListener{dialoInterface, i ->
                    val intentFromMain = intent
                     finish()
                     startActivity(intentFromMain)
                 })

                 alert.setNegativeButton("No",DialogInterface.OnClickListener{dialogInterface, i ->
                     Toast.makeText(this@MainActivity,"Game Over !", Toast.LENGTH_LONG).show()
                 })
                 alert.show()

             }

         }.start()

        runnable = object: Runnable{
            override fun run() {
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val randomIndex = Random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)
            }

        }
        handler.post(runnable)


    }

    fun hideImages(){

    }

    fun increaseScore(view: View){
        score = score + 1
        binding.scoreText.text = " Score: ${score}"

    }



}