package swu.lwh.todonew

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, name:String, version:Int):
    SQLiteOpenHelper(context,name,null,version) {
    private val creatTODO="create table todo(PID Int,time text,classify text,title text,content text)"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(creatTODO)
        Toast.makeText(context,"Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {

    }

}