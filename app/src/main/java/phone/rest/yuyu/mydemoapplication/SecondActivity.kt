package phone.rest.yuyu.mydemoapplication

import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    companion object {
       var drawable: Drawable? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        drawable = getDrawable(R.mipmap.ic_launcher)

        findViewById<TextView>(R.id.text).background = drawable
    }

    override fun onDestroy() {
        super.onDestroy()
//        drawable = null
    }

}
