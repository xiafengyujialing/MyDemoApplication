package phone.rest.yuyu.mydemoapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private val items = arrayListOf("DLAN:"+ DlanRotuer.screening_activity, "-", "-", "-")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.list_item)

        val arrayAdapter = MainAdapter(this, items)
        listView.adapter = arrayAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            var item: String = items[position]
            if (item.isNotEmpty() && item.split(":").size > 1) {
                if (DlanRotuer.rotuer.containsKey(item.split(":")[1])) {
                    // 通过反射 获取跳转类
                    var className = Class.forName(DlanRotuer.rotuer[item.split(":")[1]])
                    var intent = Intent(this, className)
                    startActivity(intent)
                }
            }
        }

    }

}
