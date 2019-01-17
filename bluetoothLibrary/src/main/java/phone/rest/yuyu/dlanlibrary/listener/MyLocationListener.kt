package phone.rest.yuyu.dlanlibrary.listener

import android.location.Location
import android.location.LocationListener
import android.os.Bundle

/**
 * @author yujialing
 * @date 2019/1/15
 */
class MyLocationListener : LocationListener {

    private lateinit var mCurrentLocation: Location

    override fun onLocationChanged(location: Location?) {
        mCurrentLocation = location!!
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    fun getLocation(): Location{
        return mCurrentLocation
    }

}