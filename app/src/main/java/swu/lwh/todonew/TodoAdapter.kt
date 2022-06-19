package swu.lwh.todonew

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(val todoList: List<Todo>,val context: Context) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    val dbHelper=MyDatabaseHelper(context,"TODOList.db",1)
    val db=dbHelper.writableDatabase
    inner class ViewHolder(view: View) :  RecyclerView.ViewHolder(view){
        val todo_title: TextView =view.findViewById(R.id.it_title)
        val todo_content: TextView =view.findViewById(R.id.it_content)
        val todo_date: TextView =view.findViewById(R.id.it_date)
        val todo_class: TextView =view.findViewById(R.id.it_class)
        val todo_del: Button =view.findViewById(R.id.it_del)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        val holder=ViewHolder(view)
        holder.todo_del.setOnClickListener {
            val position=holder.adapterPosition
            AlertDialog.Builder(context).apply {
                setMessage("确定删除任务吗？")
                setPositiveButton("Yes!"){
                        dialog,which->db.delete("todo","ID = ?", arrayOf(position.toString()))
                }
                setNegativeButton("No!"){
                        dialog,which->}

            }.show()
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo=todoList[position]
        holder.todo_date.text=todo.time
        holder.todo_class.text=todo.classify
        holder.todo_content.text=todo.content
        holder.todo_title.text=todo.title

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

}