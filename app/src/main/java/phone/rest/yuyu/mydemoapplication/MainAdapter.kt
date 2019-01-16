package phone.rest.yuyu.mydemoapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import java.util.ArrayList

/**
 * @author yujialing
 * @date 2019/1/12
 */
class MainAdapter(context: Context, items: ArrayList<String>) : BaseAdapter() {

    private var context: Context? = context

    private var mDatas = items

    override fun getCount(): Int {
        return mDatas.size
    }

    override fun getItem(position: Int): Any {
        return mDatas[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        var view: View? = convertView
        var viewHolder: ViewHolder

        if (view == null) {
            viewHolder = ViewHolder()
            view = LayoutInflater.from(context).inflate(R.layout.main_layout_item, null)
            viewHolder.title = view.findViewById(R.id.text_item)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.title.text = mDatas[position]

        return view
    }

    class ViewHolder {
        lateinit var title: TextView
    }
}
