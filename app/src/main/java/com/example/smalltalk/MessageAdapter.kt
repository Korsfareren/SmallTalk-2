package com.example.smalltalk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.smalltalk.database.ChatMessage

class MessageAdapter(
    private val dataSet: MutableList<ChatMessage>,
    private val loggedInUserName: String

) : RecyclerView.Adapter<MessageAdapter.ChatViewHolder>() {

    //Hvordan cellen skal se ut
    open class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open lateinit var container: ConstraintLayout
        open lateinit var sender: TextView
        open lateinit var chat: TextView
        open lateinit var date: TextView
    }

    inner class LeftChatViewHolder(view: View) : ChatViewHolder(view) {
        override var sender: TextView = view.findViewById(R.id.cell_sender_left)
        override var container: ConstraintLayout = view.findViewById(R.id.cell_container_left)
        override var chat: TextView = view.findViewById(R.id.cell_chat_left)
        override var date: TextView = view.findViewById(R.id.cell_date_left)
    }

    inner class RightChatViewHolder(view: View) : ChatViewHolder(view) {
        override var sender: TextView = view.findViewById(R.id.cell_sender_right)
        override var container: ConstraintLayout = view.findViewById(R.id.cell_container_right)
        override var chat: TextView = view.findViewById(R.id.cell_chat_right)
        override var date: TextView = view.findViewById(R.id.cell_date_right)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isChatFromLoggedInUser(position)) 0 else 1
    }

    //Hva skal skje når du oppretter en celle
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val cellView = if (viewType == 0) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.right_chat_cell_item, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.left_chat_cell_item, parent, false)
        }

        val params: ViewGroup.LayoutParams = cellView.layoutParams
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        cellView.layoutParams = params

        return if (viewType == 0) {
            RightChatViewHolder(cellView)
        } else {
            LeftChatViewHolder(cellView)
        }
    }

    //Hva skal skje når du oppdaterer innholdet i en celle
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val sentMessages = dataSet[position].message
        val messageDate = dataSet[position].date
        val senderName = dataSet[position].sender

        holder.chat.text = sentMessages
        holder.sender.text = senderName
        holder.date.text = messageDate.toString()
    }

    //Hvor mange items har du totalt
    override fun getItemCount(): Int {
        return dataSet.size
    }

    private fun isChatFromLoggedInUser(position: Int): Boolean {
        return dataSet[position].sender == loggedInUserName
    }
}