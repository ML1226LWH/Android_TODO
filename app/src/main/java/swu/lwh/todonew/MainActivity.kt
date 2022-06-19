package swu.lwh.todonew

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val todoList=ArrayList<Todo>()
    val dbHelper=MyDatabaseHelper(this,"TODOList.db",1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper.writableDatabase
        val db=dbHelper.writableDatabase
        initTodo(1,"2022-01-03","work","完成量子力学的实验报告","实验报告")
        initTodo(2,"2022-01-04","study","学习吐火罗文","学习语言")
        initTodo(3,"2022-01-07","life","学习满汉全席的制作","做菜")



        createdb()
        val cursor=db.rawQuery("select * from todo",null)
        if(cursor.moveToFirst()){
            do{
                val PID=cursor.getInt(cursor.getColumnIndexOrThrow("PID"))
                val time=cursor.getString(cursor.getColumnIndexOrThrow("time"))
                val classify=cursor.getString(cursor.getColumnIndexOrThrow("classify"))
                val content=cursor.getString(cursor.getColumnIndexOrThrow("content"))
                val title=cursor.getString(cursor.getColumnIndexOrThrow("title"))
                initTodo(PID,time, classify, content, title)
            }while (cursor.moveToNext())
        }
        add_btn.setOnClickListener {
            val intent= Intent(this,AddActivity::class.java)
            startActivity(intent)
        }
        val layoutManager= LinearLayoutManager(this)
        recycler.layoutManager=layoutManager
        val adapter=TodoAdapter(todoList,this)
        recycler.adapter=adapter
        /*refresh_Layout.setOnRefreshListener{
            thread{

            }
        }*/
        //downPullRefresh()
    }
    private fun initTodo(PID:Int,time:String,classify:String,content:String,title:String){
        todoList.add(Todo(PID,time, classify, content, title))
    }
    private fun createdb(){
        val db=dbHelper.writableDatabase
        val values1= ContentValues().apply{
            put("PID",1)
            put("time","2022-01-03")
            put("classify","work")
            put("content","从多方面分析体会并完成三科的实验报告")
            put("title","实验报告")
        }
        val values2= ContentValues().apply{
            put("PID",2)
            put("time","2022-01-04")
            put("classify","study")
            put("content","学习吐火罗文告")
            put("title","学习语言")
        }
        val values3= ContentValues().apply{
            put("PID",3)
            put("time","2022-01-07")
            put("classify","life")
            put("content","学习满汉全席的制作")
            put("title","做菜")
        }
        db.execSQL("insert into todo (PID,time,classify,content,title) values(?,?,?,?,?)",
            arrayOf(1,"2022-01-03","work","从多方面分析体会并完成三科的实验报告"))
        db.insert("todo",null,values1)
        db.insert("todo",null,values2)
        db.insert("todo",null,values3)
    }

   /* private fun downPullRefresh() {
        refresh_Layout.setOnRefreshListener {
            var handler = object : Handler(Looper.getMainLooper()){
                override fun handleMessage(msg: Message) {
                    when(msg.what){
                            val adapter=TodoAdapter(todoList,this)
                        adapter.notifyDataSetChanged()
                                refresh_Layout.isRefreshing = false
                    }
                }
            }
            handler.postDelayed({

            }, 2000)
        }
    }*/

}