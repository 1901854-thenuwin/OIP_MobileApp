package com.example.ngltube


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.MotionEvent
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var data: Data
    private var progr = 0
    private var temp = ""
    private var humidity = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbRef = FirebaseDatabase.getInstance("https://oip-database-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val dataQuery = dbRef.child("data")
        dataQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    data = Data(dataSnapshot.child("error").value.toString(),
                        dataSnapshot.child("humidity").value.toString(),
                        dataSnapshot.child("stage").value.toString(),
                        dataSnapshot.child("status").value.toString(),
                        dataSnapshot.child("temp").value.toString())
                    // status == on/off
                    var countDownTimer = object: CountDownTimer(1000*30,1000){
                        var tvtimer = findViewById<TextView>(R.id.textCountdownMinsNum)
                        override fun onTick(p0: Long) {
                            tvtimer!!.text = getString(R.string.formatted_time, TimeUnit.MILLISECONDS.toMinutes
                                (p0)%60, TimeUnit.MILLISECONDS.toSeconds(p0)%60)
                        }
                        override fun onFinish() {
                            tvtimer.text == "00:30"
                        }
                    }
                    if (data.getStatus().toString().equals("on")){
                        countDownTimer.start()
                    }else if (data.getStatus().toString().equals("off")){
                        countDownTimer.cancel()
                    }
                    // change the status of the process
                    if (data.getStage().toString().equals("washing") && progr==0){
                        findViewById<ProgressBar>(R.id.circularProgressBar).progress = 33
                        findViewById<TextView>(R.id.textStatus).text = data.getStage()
                    }else if (data.getStage().toString().equals("sterilizing")){
                        findViewById<ProgressBar>(R.id.circularProgressBar).progress = 66
                        findViewById<TextView>(R.id.textStatus).text = data.getStage()
                    }else if (data.getStage().toString().equals("drying")){
                        findViewById<ProgressBar>(R.id.circularProgressBar).progress = 100
                        findViewById<TextView>(R.id.textStatus).text = data.getStage()
                    }
                    // temp
                    temp = data.getTemp().toString()
                    humidity = data.getHumidity().toString()

                    if (data.getTemp().toString() is String){
                        findViewById<TextView>(R.id.textDegrees).text = temp + "°C"
                    }

                    if (data.getHumidity().toString() is String){
                        findViewById<TextView>(R.id.textHumid).text = humidity + "%"
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })


        findViewById<Button>(R.id.buttonTemp).setOnTouchListener(object : View.OnTouchListener {
            val bg: ConstraintLayout = findViewById(R.id.background)
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                if (p1!!.action == MotionEvent.ACTION_DOWN) {
                    bg.alpha = 0.8f
                    findViewById<Button>(R.id.buttonTemp).alpha = 0.8f
                    findViewById<Button>(R.id.buttonHumidity).alpha = 0.8f
                    findViewById<Button>(R.id.buttonCam).alpha = 0.8f
                    findViewById<TextView>(R.id.textStatus).alpha = 0.4f
                    findViewById<TextView>(R.id.textCleaner).alpha = 0.4f
                    findViewById<TextView>(R.id.textCountdownMinsNum).alpha = 0.4f
                    findViewById<TextView>(R.id.textSmartNGL).alpha = 0.4f
                    findViewById<TextView>(R.id.textDegrees).text = temp + "°C"
                }else if (p1!!.action == MotionEvent.ACTION_UP){
                    bg.alpha = 1.0f
                    findViewById<Button>(R.id.buttonTemp).alpha = 1.0f
                    findViewById<Button>(R.id.buttonHumidity).alpha = 1.0f
                    findViewById<Button>(R.id.buttonCam).alpha = 1.0f
                    findViewById<TextView>(R.id.textStatus).alpha = 1.0f
                    findViewById<TextView>(R.id.textCountdownMinsNum).alpha = 1.0f
                    findViewById<TextView>(R.id.textCleaner).alpha = 1.0f
                    findViewById<TextView>(R.id.textSmartNGL).alpha = 1.0f
                }
                return true
            }
        })

        findViewById<Button>(R.id.buttonHumidity).setOnTouchListener(object : View.OnTouchListener {
            val bg: ConstraintLayout = findViewById(R.id.background)
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                if (p1!!.action == MotionEvent.ACTION_DOWN) {
                    bg.alpha = 0.8f
                    findViewById<Button>(R.id.buttonTemp).alpha = 0.8f
                    findViewById<Button>(R.id.buttonHumidity).alpha = 0.8f
                    findViewById<Button>(R.id.buttonCam).alpha = 0.8f
                    findViewById<TextView>(R.id.textCountdownMinsNum).alpha = 0.4f
                    findViewById<TextView>(R.id.textStatus).alpha = 0.4f
                    findViewById<TextView>(R.id.textCleaner).alpha = 0.4f
                    findViewById<TextView>(R.id.textSmartNGL).alpha = 0.4f
                    findViewById<TextView>(R.id.textHumid).text = humidity + "%"
                }else if (p1!!.action == MotionEvent.ACTION_UP){
                    bg.alpha = 1.0f
                    findViewById<Button>(R.id.buttonTemp).alpha = 1.0f
                    findViewById<Button>(R.id.buttonHumidity).alpha = 1.0f
                    findViewById<Button>(R.id.buttonCam).alpha = 1.0f
                    findViewById<TextView>(R.id.textCountdownMinsNum).alpha = 1.0f
                    findViewById<TextView>(R.id.textStatus).alpha = 1.0f
                    findViewById<TextView>(R.id.textCleaner).alpha = 1.0f
                    findViewById<TextView>(R.id.textSmartNGL).alpha = 1.0f
                }
                return true
            }
        })

    }


}