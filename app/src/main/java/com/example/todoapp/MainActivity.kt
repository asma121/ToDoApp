package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var rvmain:RecyclerView
    lateinit var flacbutton: FloatingActionButton
    lateinit var todo:ArrayList<todoitem>
    lateinit var adapter: myAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvmain=findViewById(R.id.rvmain)
        flacbutton=findViewById(R.id.floatingActionButton)
        todo= arrayListOf()

        adapter = myAdapter(todo)
        rvmain.adapter = adapter
        rvmain.layoutManager = LinearLayoutManager(this)


        flacbutton.setOnClickListener {
            alertdialog()
           adapter.notifyDataSetChanged()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       var deleteitem=0
                for (i in todo){
                    if (i.checked){
                        deleteitem++
                    }
                }
        adapter.deleteItems()
        return super.onOptionsItemSelected(item)
    }

   fun alertdialog(){
        val builder = AlertDialog.Builder(this)
        val input= EditText(this)

        val layout=LinearLayout(this)
        layout.orientation=LinearLayout.VERTICAL

        layout.addView(input)

        builder.setPositiveButton("Add", DialogInterface.OnClickListener{
                dialog, id ->  todo.add(todoitem(input.text.toString()))
        })
        builder.setNegativeButton("cancel",DialogInterface.OnClickListener(){
                dialog, id -> dialog.cancel()
        })

        val alert = builder.create()
        alert.setTitle("New Item")
        alert.setView(layout)
        alert.show()
    }
}