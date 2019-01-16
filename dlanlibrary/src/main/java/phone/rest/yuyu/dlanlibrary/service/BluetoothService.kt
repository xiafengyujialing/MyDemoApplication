package phone.rest.yuyu.dlanlibrary.service

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.*
import android.location.Location
import android.location.LocationManager
import android.os.IBinder
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import phone.rest.yuyu.dlanlibrary.listener.MyLocationListener
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author yujialing
 * @date 2019/1/14
 */
class BluetoothService : Service() {

    private var mShowDialog: Boolean = false

    private var bluetoothAdapter: BluetoothAdapter? = null

    private val df = SimpleDateFormat("HH:mm", Locale.ENGLISH)//设置日期格式

    private lateinit var mContext: Context

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    override fun onCreate() {
        super.onCreate()

        mContext = this
        // 注册搜索蓝牙广播
        registerBluetoothReceiver()

        // 注册时间广播
        registerReceiver(mTimerReceiver, IntentFilter(Intent.ACTION_TIME_TICK))
    }

    /**
     * 判断是否支持蓝牙，并打开蓝牙
     * 获取到BluetoothAdapter之后，还需要判断是否支持蓝牙，以及蓝牙是否打开。
     * 如果没打开，需要让用户打开蓝牙：
     */
    private fun checkBluetoothDiscovery() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter!!.isEnabled) {
                val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                enableIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(enableIntent)
            }
        } else {
            Log.i("blueTooth", "改手机不支持蓝牙")
        }
    }

    /**
     *  监听搜锁到蓝牙设备
     */
    private fun registerBluetoothReceiver() {
        val intent = IntentFilter()
        // 用BroadcastReceiver来取得搜索结果
        intent.addAction(BluetoothDevice.ACTION_FOUND)//搜索发现设备
        intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)//状态改变
        intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)//行动扫描模式改变了
        intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)//动作状态发生了变化
        registerReceiver(searchDevicesReceiver, intent)
    }

    /**
     * 蓝牙接收广播
     */
    private val searchDevicesReceiver = object : BroadcastReceiver() {
        //接收
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            val device: BluetoothDevice
            // 搜索发现设备时，取得设备的信息；注意，这里有可能重复搜索同一设备
            if (BluetoothDevice.ACTION_FOUND == action) {
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                Log.e(">>>", device.name + "+" + device.address)
                // 指定 设备ip
                if ("D4:63:C6:8E:89:76" == (device.address) || "8C:85:90:41:58:31" == device.address) {
                    if (!mShowDialog) {
                        bluetoothAdapter!!.cancelDiscovery()
                        mShowDialog = true
                        // 做出提醒 可以放音乐 或震动
                        val alertdialog = AlertDialog.Builder(mContext)
                            .setTitle("提示").setMessage("主人，打卡上班吧")
                            .setPositiveButton("去打卡") { dialog, _ -> dialog.dismiss() }
                            .setNegativeButton("5分钟后提醒") { dialog, _ ->
                                mShowDialog = false
                                dialog.dismiss()
                            }.create()
                        alertdialog.window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
                        alertdialog.show()
                    }
                }
            }
        }
    }

    /**
     * 实时获取本地时间
     */
    private val mTimerReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (Intent.ACTION_TIME_TICK == intent!!.action) {
                Log.e("定时任务开启", "每隔5秒刷新列表")
                // 时间是否在设定内
                if (isTimeCheck()) {
                    if (!mShowDialog) {
                        // 地理位置是否在设定内 维度longitude30.295320, 30.296426
                        // 纬度latitude 120.134118，120.120278
                        val location = getLocation() ?: return
                        Log.e("目前纬度", location.latitude.toString())
                        Log.e("目前经度", location.longitude.toString())
                        if (location.longitude > 120.120278 && location.longitude < 120.134118
                            && location.latitude > 30.295320 && location.latitude < 30.298426
                        ) {
                            // 检查蓝牙设备是否开启，没开启提示开启
                            checkBluetoothDiscovery()
                            Log.e("定时任务开启", "重新扫描")
                            // 定时执行蓝牙搜索startDiscovery
                            if (bluetoothAdapter!!.isDiscovering) {
                                bluetoothAdapter!!.cancelDiscovery()
                                bluetoothAdapter!!.startDiscovery()
                            }else {
                                bluetoothAdapter!!.startDiscovery()
                            }
                        }
                    }
                } else {
                    mShowDialog = false
                }
            }
        }
    }

    /**
     * 判断当前时间是否在9：00-10：30
     */
    private fun isTimeCheck(): Boolean {
        val nowTime = df.parse(df.format(Date()))
        val beginAMTime = df.parse("09:00")
        val endAMTime = df.parse("10:30")
        val beginPMTime = df.parse("19:30")
        val endPMTime = df.parse("22:30")

        val now = Calendar.getInstance()
        now.time = nowTime
        val begin = Calendar.getInstance()
        val end = Calendar.getInstance()

        begin.time = beginAMTime
        end.time = endAMTime
        if (now.after(begin) && now.before(end)) {
            return true
        }

        begin.time = beginPMTime
        end.time = endPMTime
        if (now.after(begin) && now.before(end)) {
            return true
        }

        return false
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(): Location? {
        var provider: String?
        //获取定位服务
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        //获取当前可用的位置控制器
        var list = locationManager.getProviders(true)

        if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            //是否为网络位置控制器
            provider = LocationManager.NETWORK_PROVIDER
        }else if (list.contains(LocationManager.GPS_PROVIDER)) {
            //是否为GPS位置控制器
            provider = LocationManager.GPS_PROVIDER
        } else {
            Toast.makeText(this, "请检查网络或GPS是否打开", Toast.LENGTH_LONG).show();
            return null
        }

        locationManager.requestLocationUpdates(provider,1000,0f, MyLocationListener())

        return locationManager.getLastKnownLocation(provider)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mTimerReceiver)
        unregisterReceiver(searchDevicesReceiver)
        if (bluetoothAdapter!!.isDiscovering) {
            bluetoothAdapter!!.cancelDiscovery()
        }
    }
}