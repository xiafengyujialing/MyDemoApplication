package phone.rest.yuyu.dlanlibrary.adapter

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import phone.rest.yuyu.dlanlibrary.R

/**
 * @author yujialing
 * @date 2019/1/9
 */
class DeviceAdapter(recycleView: RecyclerView) : RecyclerView.Adapter<DeviceAdapter.DeviveHolder>() {

    private var mContext: Context? = recycleView.context

    var mDevices: ArrayList<BluetoothDevice>? = ArrayList()

    override fun getItemCount(): Int {
        return mDevices!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviveHolder {
        val view: View? = LayoutInflater.from(mContext).inflate(R.layout.device_item, null)
        return DeviveHolder(view!!)
    }

    override fun onBindViewHolder(holder: DeviveHolder, position: Int) {
        holder.title!!.text = mDevices!![position].name?:mDevices!![position].address
    }

    class DeviveHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = itemView.findViewById(R.id.title)
    }

}