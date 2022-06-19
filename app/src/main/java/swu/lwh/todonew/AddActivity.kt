package swu.lwh.todonew

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    val dbHelper=MyDatabaseHelper(this,"TODOList.db",1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val db=dbHelper.writableDatabase
        add_commit.setOnClickListener {
            val add_title=add_title.toString()
            val add_classifiy=add_class.toString()
            val add_content=add_content.toString()
            val add_date=add_date.toString()
            db.execSQL("insert into todo (time,classify,content,title) values(?,?,?,?)",
                arrayOf(add_title,add_classifiy,add_content,add_date))
        }
    }
}