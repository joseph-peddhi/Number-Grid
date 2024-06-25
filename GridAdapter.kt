package com.example.numbergrid

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

class GridAdapter(var list: MutableList<Int>,  intr: Numbers) : RecyclerView.Adapter<ViewHolder>() {




    private var weakDelegate: WeakReference<Numbers> = WeakReference(null)
    private var delegate: Numbers?
        get() = weakDelegate.get()
        set(value) {
            weakDelegate = WeakReference(value)
        }


    init {
        this.delegate=intr
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.number_grid,parent,false)
       return  ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.numFilter(delegate?.isEven(list[position]) == true)
        holder.numFilter(delegate?.isOdd(list[position]) == true)
       // holder.numFilter(delegate?.isPrime(list[position]) == true)
        holder.vi.text = list[position].toString()

    }
}
class ViewHolder(view: View):RecyclerView.ViewHolder(view){

    var vi = view.findViewById<TextView>(R.id.txn)

    fun numFilter(isTrur:Boolean){
        if(isTrur){
            vi.setTextColor(Color.RED)
        }else{
            vi.setTextColor(Color.BLACK)

        }
    }

    }


