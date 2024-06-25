package com.example.numbergrid


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(),Numbers {
private lateinit var recyclerView: RecyclerView
    private var highlightEven = false
    private var highlightOdd = false
    private var highlightPrime = false
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_even = findViewById<Button>(R.id.btn_even)
        var btn_odd = findViewById<Button>(R.id.btn_odd)
        var btn_prime = findViewById<Button>(R.id.btn_prime)
        recyclerView= findViewById(R.id.recyclerViewNos)
        var list : MutableList<Int> = mutableListOf()
        for(i in 1..100){
            list.add(i) }

        val adapter = GridAdapter(list,this)
        recyclerView.adapter=adapter
        recyclerView.layoutManager=GridLayoutManager(this,10)
        btn_even.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    highlightEven = true
                    highlightOdd= false
                }
            }
            adapter.notifyDataSetChanged()
        }
        btn_odd.setOnClickListener {lifecycleScope.launch {
            withContext(Dispatchers.IO){
                highlightEven=false
                highlightOdd=true
            }
        }
            adapter.notifyDataSetChanged()
        }

       /* btn_prime.setOnClickListener {lifecycleScope.launch {
            withContext(Dispatchers.IO){
                highlightEven=false
                highlightOdd=false
                highlightPrime = true
            }
        }
            adapter.notifyDataSetChanged()
        }*/

    }

    override fun isEven(number: Int): Boolean {
        return highlightEven && number % 2 == 0
    }

    override fun isOdd(number: Int): Boolean = highlightOdd && number % 2 != 0

    override fun isPrime(number: Int): Boolean {

        var flag = false
        for(i in 2 .. number){

            if(number % i == 0){
                flag = false
            }
            else flag = true
        }
        return highlightPrime && flag
    }
}
