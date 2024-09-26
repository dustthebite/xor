package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val xorList = mutableListOf<Xor>()


        val xorAdapter = XorAdapter(xorList)
        val rvResults = findViewById<RecyclerView>(R.id.rvResults)
        rvResults.adapter = xorAdapter
        rvResults.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.btnXor).setOnClickListener {
            generateData(xorList)
            xorAdapter.notifyDataSetChanged()
        }

    }

    private fun generateData(list : MutableList<Xor>)
    {
        list.clear()
        for (i in 0..100) {
            val x1 = Random.nextDouble(0.0,1.0)
            val x2 = Random.nextDouble(0.0,1.0)
            val x1_norm = if(x1 > 0.5) 0 else 1
            val x2_norm = if(x2 > 0.5) 0 else 1
            val xorResult = XOR(x1_norm, x2_norm)
            val xorClass = if(xorResult == 0) "Class A" else "Class B"
            list.add(Xor(x1, x2, xorClass))
        }
    }

    private fun OR(x1 : Int, x2: Int) : Int
    {
        val w1 = 1
        val w2 = 1
        val w0 : Double = -(1.0 / 2)
        val sum : Double = (w1 * x1) + (w2 * x2) + w0
        if(sum > 0)
            return 1
        return 0
    }

    private fun AND(x1: Int, x2: Int) : Int
    {
        val w1 = 1
        val w2 = 1
        val w0 : Double = -(3.0 / 2)
        val sum : Double = (w1 * x1) + (w2 * x2) + w0
        if(sum > 0)
            return 1
        return 0
    }


    private fun NOT(x : Int) : Int
    {
        if(x == 0)
            return 1
            return 0
    }

    private fun XOR(x1: Int, x2: Int) : Int
    {
        return AND(OR(x1, x2), NOT(AND(x1, x2)))
    }
}