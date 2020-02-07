package com.example.finn.kotlinapp1

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*
import android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : AppCompatActivity() {

    lateinit var diceImage: ImageView
    lateinit var diceImage2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        createListeners()

        diceImage = findViewById(R.id.dice_image)
        diceImage2 = findViewById(R.id.dice_image2)

    }

    private fun createListeners(){
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener {
            rollDice()
        }

        val imgNumOne: ImageView = findViewById(R.id.numberOne)
        imgNumOne.setOnClickListener{ Toast.makeText(this, "ONE", Toast.LENGTH_SHORT).show() }

        val imgNumTwo: ImageView = findViewById(R.id.numberTwo)
        imgNumTwo.setOnClickListener{ Toast.makeText(this, "TWO", Toast.LENGTH_SHORT).show() }

        val imgNumThree: ImageView = findViewById(R.id.numberThree)
        imgNumThree.setOnClickListener{ Toast.makeText(this, "THREE", Toast.LENGTH_SHORT).show() }

        val imgNumFour: ImageView = findViewById(R.id.numberFour)
        imgNumFour.setOnClickListener{ Toast.makeText(this, "FOUR", Toast.LENGTH_SHORT).show() }

        val imgNumFive: ImageView = findViewById(R.id.numberFive)
        imgNumFive.setOnClickListener{ Toast.makeText(this, "Five", Toast.LENGTH_SHORT).show() }

        val imgNumSix: ImageView = findViewById(R.id.numberSix)
        imgNumSix.setOnClickListener{ Toast.makeText(this, "Six", Toast.LENGTH_SHORT).show() }

        val imgNumSeven: ImageView = findViewById(R.id.numberSeven)
        imgNumSeven.setOnClickListener{ Toast.makeText(this, "Seven", Toast.LENGTH_SHORT).show() }

        val imgNumEight: ImageView = findViewById(R.id.numberEight)
        imgNumEight.setOnClickListener{ Toast.makeText(this, "Eight", Toast.LENGTH_SHORT).show() }

        val imgNumNine: ImageView = findViewById(R.id.numberNine)
        imgNumNine.setOnClickListener{ Toast.makeText(this, "Nine", Toast.LENGTH_SHORT).show() }
    }

    private fun rollDice() {
        val randomInt1 = Random().nextInt(6) + 1
        val randomInt2 = Random().nextInt(6) + 1


        val drawableResource = when (randomInt1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val drawableResource2 = when (randomInt2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }


        diceImage.setImageResource(drawableResource)
        diceImage2.setImageResource(drawableResource2)

        val total = randomInt1 + randomInt2
        possibleChoices(total)
    }

    private fun possibleChoices(total: Int){
        //FIX
        val choicesList = mutableListOf<Int>()
        choicesList.add(total)
        val finalList = mutableListOf<Int>()
        val newList = allChoices(choicesList, 0, total, total, finalList)
        var strCombinations = ""
        for (i in 0 until newList.count()){
            strCombinations = newList.joinToString()
        }
        Toast.makeText(this, strCombinations, Toast.LENGTH_SHORT)

    }

    fun allChoices(arr: MutableList<Int>, index: Int, num: Int, reducedNum: Int, finalList: MutableList<Int>): MutableList<Int> {
        if (reducedNum < 0){
            return finalList
        }
        // If combination is
        // found, print it
        if (reducedNum == 0)
        {
            for (i in 0 until index){
                finalList.add(arr[i])
            }
            return finalList
        }

        // Find the previous number
        // stored in arr[] It helps
        // in maintaining increasing order
        val prev: Int
        if(index == 0){
            prev = 1
        }else{
            prev = arr[index - 1]
        }
//        val prev = (index == 0) ? 1 : arr[index - 1];

        // note loop starts from previous
        // number i.e. at array location
        // index - 1
        for (k in prev until num)
        {
            // next element of array is k
            arr[index] = k

            // call recursively with
            // reduced number
            allChoices(arr, index + 1, num, reducedNum - k, finalList)
        }
        return finalList
    }
}
