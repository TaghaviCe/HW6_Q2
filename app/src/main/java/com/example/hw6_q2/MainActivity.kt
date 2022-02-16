package com.example.hw6_q2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R
import android.content.Context
import android.view.View
import android.widget.ImageView

import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.hw6_q2.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding
val GREEN_CODE = 0
val RED_CODE = 1
val NOT_PLAYED = 2
val NOT_WINNER = -1
var winner = NOT_WINNER
var status = intArrayOf(
    NOT_PLAYED, NOT_PLAYED, NOT_PLAYED,
    NOT_PLAYED, NOT_PLAYED, NOT_PLAYED,
    NOT_PLAYED, NOT_PLAYED, NOT_PLAYED
)
var activePlayer = RED_CODE
var winnerPosition = arrayOf(
    intArrayOf(0, 1, 2),
    intArrayOf(0, 3, 6),
    intArrayOf(2, 5, 8),
    intArrayOf(6, 7, 8),
    intArrayOf(0, 4, 8),
    intArrayOf(2, 4, 6),
    intArrayOf(3, 4, 5),
    intArrayOf(1, 4, 7)
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

    }
    fun dropIn(view: View) {
        val tag: Int = view.getTag().toString().toInt()
        if (status[tag] !== NOT_PLAYED) {
            return
        }
        val img: ImageView = view as ImageView
        img.setTranslationY(-2000f)
        if (activePlayer === RED_CODE) {
         img.setImageResource(android.R.drawable.ic_delete)
          //  img.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.circle))
            img.animate().translationY(0f).rotationBy(360f).setDuration(300)
            status[tag] = RED_CODE
            activePlayer = GREEN_CODE
        } else if (activePlayer === GREEN_CODE) {
            img.setImageResource(android.R.drawable.ic_input_add)
            img.animate().translationY(0f).rotationBy(360f).setDuration(300)
            status[tag] = GREEN_CODE
            activePlayer = RED_CODE
        }
        winner = checkWinner()
        if (winner !== NOT_WINNER) {
            Toast.makeText(
                this,
                "winner:" + if (winner === RED_CODE) "red" else "green",
                Toast.LENGTH_LONG
            ).show()
            binding.button.setOnClickListener(::gameReset)
        }
    }

    fun checkWinner(): Int {
        for (positions in winnerPosition) {
            if (status[positions[0]] === status[positions[1]] && status[positions[1]] === status[positions[2]] && status[positions[0]] !== NOT_PLAYED) {
                return status[positions[0]]
            }
        }
        return NOT_WINNER
    }
    fun gameReset(view: View) {
        activePlayer = 0;
        binding.imageView1.setImageResource(0)
        binding.imageView2.setImageResource(0)
        binding.imageView3.setImageResource(0)
        binding.imageView4.setImageResource(0)
        binding.imageView5.setImageResource(0)
        binding.imageView6.setImageResource(0)
        binding.imageView7.setImageResource(0)
        binding.imageView8.setImageResource(0)
        binding.imageView9.setImageResource(0)
        for (i in 0 until status.size) {
            status[i]=2
        }

    }
}