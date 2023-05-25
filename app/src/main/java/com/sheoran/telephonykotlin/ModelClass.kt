package com.sheoran.telephonykotlin

class ModelClass {

    var  name:String?= null
    var  number:String?= null


    fun setNames(name:String){
        this.name = name
    }
    fun setNumbers(number:String){
        this.number = number

    }
    fun getNames():String{
       return name.toString()
    }
    fun getNumbers():String{
        return  number.toString()
    }

}