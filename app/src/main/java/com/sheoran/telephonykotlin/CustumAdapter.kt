package com.sheoran.telephonykotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustumAdapter(val context: Context, val modelClassaArraylist:
ArrayList<ModelClass>):BaseAdapter() {


    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun getCount(): Int {
      return modelClassaArraylist.size
    }

    override fun getItem(p0: Int): Any {
    return modelClassaArraylist[p0]
    }

    override fun getItemId(p0: Int): Long {
      return 0
    }

    override fun getView(p0: Int, convertView: View?, parent: ViewGroup?): View {
var convertView  = convertView

        val holder : ViewHolder
        if (convertView==null){
            holder = ViewHolder()
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.lv_item,null,true)

            holder.lvname = convertView!!.findViewById(R.id.name)as TextView
            holder.lvnumber = convertView.findViewById(R.id.number)as TextView
            convertView.tag = holder


        }
        else{
            holder= convertView.tag as ViewHolder
        }
        holder.lvname!!.setText(modelClassaArraylist[p0].getNames())
        holder.lvnumber!!.setText(modelClassaArraylist[p0].getNumbers())
        return convertView
    }

    inner  class  ViewHolder(){
        var  lvname:TextView?=null
        var lvnumber:TextView?= null
    }


}