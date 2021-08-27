package com.example.ngltube

class Data {

    private var error: String? = null
    private var humidity: String? = null
    private var stage: String? = null
    private var status: String? = null
    private var temp: String? = null


    constructor(error: String, humidity: String, stage: String, status: String, temp:String){
        this.error = error
        this.humidity = humidity
        this.stage = stage
        this.status = status
        this.temp = temp
    }

    fun getTemp(): String?{
        return temp
    }

    fun getError(): String? {
        return error
    }

    fun getHumidity(): String? {
        return humidity
    }

    fun getStage(): String? {
        return stage
    }

    fun getStatus(): String? {
        return status
    }

}