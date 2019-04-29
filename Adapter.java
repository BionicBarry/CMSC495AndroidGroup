package com.example.samplechatapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
This class is to bind the data for the chat screens
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ChatViewHolder> {

    public List<Chat> list;
    public ArrayList<Message> messageList;

    @NonNull
    @Override
    public Adapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflater = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_single_item, viewGroup, false);
        return new ChatViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ChatViewHolder chatViewHolder, int i) {
        chatViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Chat> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setMessageList(ArrayList<Message> message) {
        this.messageList = message;
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView messageTextView;
        ImageView imageView;
        View itemView;


        public ChatViewHolder(View inflater) {
            super(inflater);
            itemView = inflater;
            nameTextView = inflater.findViewById(R.id.nameTextView);
            messageTextView = inflater.findViewById(R.id.messageTextView);
            imageView = inflater.findViewById(R.id.imageView);
        }

        void bind(final int position) {
            nameTextView.setText(list.get(position).name);
            messageTextView.setText(list.get(position).message);
            imageView.setBackgroundResource(list.get(position).imagePath);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), ChatActivity.class);
                    intent.putExtra("messageList", messageList);
                    intent.putExtra("name", list.get(position).name);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}