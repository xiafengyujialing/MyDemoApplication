package phone.rest.yuyu.mydemoapplication

/**
 * @author yujialing
 * @date 2019/1/12
 */
class DlanRotuer {

    companion object {

        val screening_activity: String = "dlanlibrary/screening_activity"

        var rotuer: HashMap<String, String> = HashMap()

        init {
            rotuer[screening_activity] = "phone.rest.yuyu.dlanlibrary.ui.BluetoothActivity"
        }
    }

}