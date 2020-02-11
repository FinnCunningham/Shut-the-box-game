package com.example.finn.kotlinapp1

import android.content.pm.ActivityInfo
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import android.support.v4.app.SupportActivity.ExtraData







class MainActivity : AppCompatActivity() {

    lateinit var diceImage: ImageView
    lateinit var diceImage2: ImageView

    lateinit var imgNumOne: ImageView
    lateinit var imgNumTwo: ImageView
    lateinit var imgNumThree: ImageView
    lateinit var imgNumFour: ImageView
    lateinit var imgNumFive: ImageView
    lateinit var imgNumSix: ImageView
    lateinit var imgNumSeven: ImageView
    lateinit var imgNumEight: ImageView
    lateinit var imgNumNine: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.window.decorView.systemUiVisibility = (SYSTEM_UI_FLAG_LAYOUT_STABLE
                or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or SYSTEM_UI_FLAG_FULLSCREEN
                or SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        createListeners()

        diceImage = findViewById(R.id.dice_image)
        diceImage2 = findViewById(R.id.dice_image2)
//        val highlight = resources.getDrawable(R.drawable.highlight)
//        val imgNumOne: ImageView = findViewById(R.id.numberOne)
//
//        imgNumOne.setBackground(highlight)



    }

    private fun createListeners(){
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener {
            rollDice()
        }

        imgNumOne = findViewById(R.id.numberOne)
        imgNumOne.setOnClickListener{ Toast.makeText(this, "ONE", Toast.LENGTH_SHORT).show() }

        imgNumTwo = findViewById(R.id.numberTwo)
        imgNumTwo.setOnClickListener{ Toast.makeText(this, "TWO", Toast.LENGTH_SHORT).show() }

        imgNumThree = findViewById(R.id.numberThree)
        imgNumThree.setOnClickListener{ Toast.makeText(this, "THREE", Toast.LENGTH_SHORT).show() }

        imgNumFour = findViewById(R.id.numberFour)
        imgNumFour.setOnClickListener{ Toast.makeText(this, "FOUR", Toast.LENGTH_SHORT).show() }

        imgNumFive = findViewById(R.id.numberFive)
        imgNumFive.setOnClickListener{ Toast.makeText(this, "Five", Toast.LENGTH_SHORT).show() }

        imgNumSix = findViewById(R.id.numberSix)
        imgNumSix.setOnClickListener{ Toast.makeText(this, "Six", Toast.LENGTH_SHORT).show() }

        imgNumSeven = findViewById(R.id.numberSeven)
        imgNumSeven.setOnClickListener{ Toast.makeText(this, "Seven", Toast.LENGTH_SHORT).show() }

        imgNumEight = findViewById(R.id.numberEight)
        imgNumEight.setOnClickListener{ Toast.makeText(this, "Eight", Toast.LENGTH_SHORT).show() }

        imgNumNine = findViewById(R.id.numberNine)
        imgNumNine.setOnClickListener{ Toast.makeText(this, "Nine", Toast.LENGTH_SHORT).show() }
    }

    private fun rollDice() {
        var imgList : MutableList<ImageView> = mutableListOf(imgNumOne, imgNumTwo, imgNumThree,
                imgNumFour, imgNumFive, imgNumSix, imgNumSeven, imgNumEight, imgNumNine)
        for(i in 0 until imgList.count()){
            imgList[i].setBackground(null)
        }

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

        var total = randomInt1 + randomInt2

        val out = IntArray(total)

//        var combinationList : MutableList<Int> = mutableListOf()

        // print all combination of numbers from 1 to n having sum n
        recur(1, total, out, 0)
        Log.d("myTag", combinationList.toString())

        loopOfDuplicates()


        val rollButton: Button = findViewById(R.id.roll_button)
//        rollButton.isEnabled = false
        checkForLargeNum()
        Log.d("Done", combinationList.toString())
        val txtInfo: TextView = findViewById(R.id.info_txt)
        txtInfo.text = combinationList.toString()

        checkWhichToHighlight()

        combinationList.clear()
    }

    fun checkWhichToHighlight(){
        val highlight = resources.getDrawable(R.drawable.highlight)
        var imgList : MutableList<ImageView> = mutableListOf(imgNumOne, imgNumTwo, imgNumThree,
                imgNumFour, imgNumFive, imgNumSix, imgNumSeven, imgNumEight, imgNumNine)
        for(i in 0 until combinationList[0].count()){
//            val drawableRes = when (combinationList[0][1]) {
//                1 -> R.drawable.number_1
//                2 -> R.drawable.number_2
//                3 -> R.drawable.number_3
//                4 -> R.drawable.number_4
//                5 -> R.drawable.number_5
//                6 -> R.drawable.number_6
//                7 -> R.drawable.number_7
//                8 -> R.drawable.number_8
//                else -> R.drawable.number_9
//            }
            imgList[combinationList[0][i] - 1].setBackground(highlight)
//            drawableRes.setBackground(highlight)
        }
    }

    fun checkForLargeNum(){
        for (i in 0 until combinationList.count()){
            if(i < combinationList.count()) {
                Log.d("Test", combinationList.count().toString())
                if (combinationList[i].contains(10) || combinationList[i].contains(11)
                        || combinationList[i].contains(12)) {
                    combinationList.removeAt(i)
                    checkForLargeNum()
                }
            }
        }

    }

    private fun loopOfDuplicates(){
        for (i in 0 until combinationList.count() - 1){
            if(i < combinationList.count()){
                val checkBool = checkForDuplicates(combinationList[i])
                if(!checkBool){
                    combinationList.removeAt(i)
                    loopOfDuplicates()
                }
            }
        }
    }

    private var combinationList : MutableList<MutableList<Int>> = mutableListOf()

    fun checkForDuplicates(listToCheck : MutableList<Int>) : Boolean{

        for (i in 0 until listToCheck.count() - 1) {
            if (listToCheck[i] === listToCheck[i + 1]) {
                return false
            }
        }
        return true
    }

    fun recur(i: Int, n: Int, out: IntArray, index: Int) {
        // if sum becomes n, print the combination
        var newCombinationList : MutableList<Int> = mutableListOf()
        if (n == 0) {
            //trying to pass combinationList as a mutable list that contains all possible combinations but parameters in kotlin are CONSTANTS for some ungodly reason.
            printCombination(out, index)
        }

        var j: Int

        // start from previous element in the combination till n
        j = i
        while (j <= n) {
            // place current element at current index
            out[index] = j

            // recur with reduced sum
            recur(j, n - j, out, index + 1)
            j++
        }
//        return newCombinationList
    }

    fun printCombination(out: IntArray, n: Int){
        var cobList : MutableList<Int> = mutableListOf()
        for (i in 0 until n) {
//            System.out.printf("%d ", out[i])
            cobList.add(out[i])
        }
        combinationList.add(cobList)
//        combinationList.add(singleCombinationList)
        Log.d("myTag", cobList.toString())
        Log.d("myTag", "-----------------------")

    }
}
