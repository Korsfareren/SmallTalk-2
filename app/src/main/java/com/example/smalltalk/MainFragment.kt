package com.example.smalltalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smalltalk.database.ChatMessage
import com.example.smalltalk.database.viewModels.MainViewModel
import java.util.*

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

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

        adapter = MessageAdapter(viewModel.sentMessages(), "Thomas")
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}


