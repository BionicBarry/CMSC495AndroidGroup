package com.example.samplechatapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class MessageListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mMessageList = ArrayList<Message>()

    fun setList(list: List<Message>) {
        this.mMessageList = list as ArrayList<Message>
        notifyDataSetChanged()
    }

    fun setMessage(message: Message) {
        mMessageList.add(message)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mMessageList?.size
    }

    // Determines the appropriate ViewType according to the sender of the message.
    override fun getItemViewType(position: Int): Int {
        val message = mMessageList!![position]

        return if (Objects.requireNonNull<Int>(Objects.requireNonNull<User>(message.sender).id) == CURRENT_USER_ID) {
            // If the current user is the sender of the message
            VIEW_TYPE_MESSAGE_SENT
        } else {
            // If some other user sent the message
            VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message_sent, parent, false)
            return SentMessageHolder(view)
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message_received, parent, false)
            return ReceivedMessageHolder(view)
        }

        return null!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = mMessageList!![position]

        when (holder.itemViewType) {
            VIEW_TYPE_MESSAGE_SENT -> (holder as SentMessageHolder).bind(message)
            VIEW_TYPE_MESSAGE_RECEIVED -> (holder as ReceivedMessageHolder).bind(message)
        }
    }

    companion object {
        private const val VIEW_TYPE_MESSAGE_SENT = 1
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 2
        private const val CURRENT_USER_ID = 1234
    }

    private class SentMessageHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val messageText: TextView = itemView.findViewById(R.id.text_message_body)
        internal val timeText: TextView = itemView.findViewById(R.id.text_message_time)

        internal fun bind(message: Message) {
            messageText.text = message.message

            // Format the stored timestamp into a readable String using method.
            timeText.text = message.createdAt
        }
    }

    private class ReceivedMessageHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val messageText: TextView = itemView.findViewById(R.id.text_message_body)
        internal val timeText: TextView = itemView.findViewById(R.id.text_message_time)
        internal val nameText: TextView = itemView.findViewById(R.id.text_message_name)
        internal val profileImage: ImageView = itemView.findViewById(R.id.image_message_profile)

        internal fun bind(message: Message) {
            messageText.text = message.message

            // Format the stored timestamp into a readable String using method.
            timeText.text = message.createdAt

            if (message.sender != null) {
                nameText.text = message.sender!!.nickname
                message.sender?.profileUrl?.let { profileImage.setImageResource(it) }
            }

        }
    }
}