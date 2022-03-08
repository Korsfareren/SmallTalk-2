package com.example.smalltalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smalltalk.database.ChatMessage
import java.util.*


val sentMessages = mutableListOf(
    ChatMessage(message = "Hei.", date = Date().toString(), sender = "Thomas"),
    ChatMessage(message = "Hei du..", date = Date().toString(), sender = "David"),
    ChatMessage(message = "Skjera baggera", date = Date().toString(), sender = "Thomas"),
    ChatMessage(message = "Ingenting, Tingeling", date = Date().toString(), sender = "David"),
)


class MainFragment : Fragment() {

    lateinit var chatEditText: EditText
    lateinit var sendMessageBtn: ImageButton
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sendMessageBtn = view.findViewById(R.id.sendMsgBtn)
        chatEditText = view.findViewById(R.id.chatEditText)
        recyclerView = view.findViewById(R.id.recycler_view)

        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        adapter = MessageAdapter(sentMessages, "Thomas")
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
