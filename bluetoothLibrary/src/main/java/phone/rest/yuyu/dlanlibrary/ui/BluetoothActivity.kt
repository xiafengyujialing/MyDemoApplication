package phone.rest.yuyu.dlanlibrary.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import phone.rest.yuyu.dlanlibrary.R
import phone.rest.yuyu.dlanlibrary.service.BluetoothService

/**
 * @author yujialing
 * @date 2019/1/14
 */

class BluetoothActivity : AppCompatActivity() {

    private var mIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening)
        mIntent = Intent(this, BluetoothService::class.java)
        startService(mIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(mIntent)
    }

}
