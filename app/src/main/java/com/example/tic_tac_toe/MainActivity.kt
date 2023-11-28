package com.example.tic_tac_toe

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    var score1 = 0
    var score2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportActionBar?.title = "Tic Tac Toe"

        val saveScoreBtn = findViewById<ImageButton>(R.id.saveScore)
        val scoresBtn = findViewById<ImageButton>(R.id.scores)

        scoresBtn.setOnClickListener {
            displayScore()
        }

        saveScoreBtn.setOnClickListener {
          if(score1 == 0 && score2 == 0){
              Toast.makeText(this,"You need to finish a game before saving a score", Toast.LENGTH_SHORT).show()
          } else{
              saveScore(getWinner(),score1,score2,getCurrentTimestamp())
              score1 = 0
              score2 = 0
              Toast.makeText(this,"Score saved at ${getCurrentTimestamp()}", Toast.LENGTH_SHORT).show()
          }
            score1 = 0
            score2 = 0
        }
        val restartBtn = findViewById<Button>(R.id.restartBtn)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)

        restartBtn.visibility = View.INVISIBLE
        restartBtn.setOnClickListener {
           restart()

        }

        btn1.setOnClickListener {
            selectedButton(it)
        }
        btn2.setOnClickListener {
            selectedButton(it)
        }
        btn3.setOnClickListener {
            selectedButton(it)
        }
        btn4.setOnClickListener {
            selectedButton(it)
        }
        btn5.setOnClickListener {
            selectedButton(it)
        }
        btn6.setOnClickListener {
            selectedButton(it)
        }
        btn7.setOnClickListener {
            selectedButton(it)
        }
        btn8.setOnClickListener {
            selectedButton(it)
        }
        btn9.setOnClickListener {
            selectedButton(it)
        }
    }



    private fun selectedButton(view:View) {
        val selectedBtn = view as Button
        var btnNum = 0
        when(selectedBtn.id){
            R.id.btn1 -> btnNum = 1
            R.id.btn2 -> btnNum = 2
            R.id.btn3 -> btnNum = 3
            R.id.btn4 -> btnNum = 4
            R.id.btn5 -> btnNum = 5
            R.id.btn6 -> btnNum = 6
            R.id.btn7 -> btnNum = 7
            R.id.btn8 -> btnNum = 8
            R.id.btn9 -> btnNum = 9
        }

        game(btnNum,selectedBtn)
    }

    var player = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    private fun game(cell: Int, selectedBtn: Button) {
        val restartBtn = findViewById<Button>(R.id.restartBtn)

        restartBtn.visibility = View.INVISIBLE
        if (player == 1) {
            selectedBtn.text = "O"
            selectedBtn.setBackgroundColor(Color.parseColor("#FF0000"))
            player = 4
            player1.add(cell)
        } else {
            selectedBtn.text = "X"
            selectedBtn.setBackgroundColor(Color.parseColor("#0000FF"))
            player = 1
            player2.add(cell)
        }
        selectedBtn.isEnabled = false
        neutral()
        checkForWinner()
    }

    private fun checkForWinner() {


        val player1Score = findViewById<TextView>(R.id.player1Score)
        val player2Score = findViewById<TextView>(R.id.player2Score)


        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            disableButton()
            score1++
            showAlert("Player1 won")
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            showAlert("Player2 won")
            disableButton()
            score2++
        }

            //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            showAlert("Player1 won")
            disableButton()
            score1++
        }

        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            showAlert("Player2 won")
            disableButton()
            score2++
        }

        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            showAlert("Player1 won")
            score1++
            disableButton()
        }

        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            showAlert("Player2 won")
            disableButton()
            score2++
        }

        //clmn1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            showAlert("Player1 won")
            score1++
            disableButton()
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            showAlert("Player2 won")
            score2++
            disableButton()
        }

        //clmn2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            showAlert("Player1 won")
            score1++
            disableButton()
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            showAlert("Player2 won")
            score2++
            disableButton()
        }

        //clmn3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            showAlert("Player1 won")
            score1++
            disableButton()
        }

        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            showAlert("Player2 won")
            score2++
            disableButton()
        }

        //diagonal1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)){
            showAlert("Player1 won")
            score1++
            disableButton()
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)){
            showAlert("Player1 won")
            disableButton()
            score2++
        }

        //diagonal2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)){
            showAlert("Player1 won")
            disableButton()
            score1++
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)){
            showAlert("Player1 won")
            disableButton()
            score2++
        }
        player1Score.text = score1.toString()
        player2Score.text  = score2.toString()
    }

    private fun showAlert(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Congratulations")
        alertDialogBuilder.setIcon(R.drawable.baseline_check_circle_outline_24)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setPositiveButton("Continue"){ _,_ ->
        }
        alertDialogBuilder.create()
        alertDialogBuilder.show()
    }

    private fun neutral() {
        if (player1.size == 5 && player2.size == 4){
            disableButton()
            Toast.makeText(this,"Draw: Nobody won, click on restart to continue playing",Toast.LENGTH_SHORT).show()
        } else if (player1.size == 4 && player2.size == 5){
            disableButton()
            Toast.makeText(this,"Draw: Nobody won, click on restart to continue playing",Toast.LENGTH_SHORT).show()
        }
    }

    private fun disableButton() {

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        val restartBtn = findViewById<Button>(R.id.restartBtn)


        btn1.isEnabled = false
        btn2.isEnabled = false
        btn3.isEnabled = false
        btn4.isEnabled = false
        btn5.isEnabled = false
        btn6.isEnabled = false
        btn7.isEnabled = false
        btn8.isEnabled = false
        btn9.isEnabled = false

        restartBtn.visibility = View.VISIBLE

    }

    private fun restart() {

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)

        player1.clear()
        player2.clear()

        btn1.setBackgroundColor(Color.parseColor("#DFDEDE"))
        btn2.setBackgroundColor(Color.parseColor("#DFDEDE"))
        btn3.setBackgroundColor(Color.parseColor("#DFDEDE"))
        btn4.setBackgroundColor(Color.parseColor("#DFDEDE"))
        btn5.setBackgroundColor(Color.parseColor("#DFDEDE"))
        btn6.setBackgroundColor(Color.parseColor("#DFDEDE"))
        btn7.setBackgroundColor(Color.parseColor("#DFDEDE"))
        btn8.setBackgroundColor(Color.parseColor("#DFDEDE"))
        btn9.setBackgroundColor(Color.parseColor("#DFDEDE"))

        btn1.text = ""
        btn2.text = ""
        btn3.text = ""
        btn4.text = ""
        btn5.text = ""
        btn6.text = ""
        btn7.text = ""
        btn8.text = ""
        btn9.text = ""

        btn1.isEnabled = true
        btn2.isEnabled = true
        btn3.isEnabled = true
        btn4.isEnabled = true
        btn5.isEnabled = true
        btn6.isEnabled = true
        btn7.isEnabled = true
        btn8.isEnabled = true
        btn9.isEnabled = true


    }

    private fun saveScore(winner:String,player1Score:Int,player2Score:Int,timestamp:String){
        val sharedPref = getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val scoreList = sharedPref.getString("scores", "")?.split(";")?.toMutableList()
            ?: mutableListOf()

        val newScore = " $timestamp -- Winner: $winner, Player1 Score: $player1Score, Player2 Score: $player2Score "
        scoreList.add(newScore)


        scoreList.sortByDescending {
            it.substringAfter("Score: ").substringBefore(" , Timestamp")
        }


        editor.putString("scores", scoreList.joinToString(";"))
        editor.apply()
    }

    private fun displayScore() {
        val sharedPref = getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)

        val scoresString = sharedPref.getString("scores","")
        val scoresList = scoresString?.split(";")?.reversed() ?: emptyList()

        val  dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog,null)
        val textScores = dialogView.findViewById<TextView>(R.id.textScore)

        val scoresText = StringBuilder()
        for((index, score) in scoresList.withIndex()) {
            scoresText.append("${index + 1}.   $score\n \n")
        }
        textScores.text = scoresText.toString()

        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Scores")
            .setPositiveButton("Close"){ dialog,_ ->
                dialog.dismiss()
            }
            builder.create()
        builder.show()
    }

    private fun getCurrentTimestamp() :String{
        val currentTimeMillis = System.currentTimeMillis()
        val date = Date(currentTimeMillis)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun getWinner() : String{
        return if (score1 > score2){
            "Player 1"
        } else if (score1 == score2){
            "Draw"
        } else{
            "Player 2"
        }
    }
}