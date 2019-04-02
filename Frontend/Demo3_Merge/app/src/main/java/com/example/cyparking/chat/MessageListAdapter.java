package com.example.cyparking.chat;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cyparking.R;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder> {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private List<Message> mMessageList;

    public static class MessageListViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout leftMsgLayout;
        ConstraintLayout rightMsgLayout;
        TextView leftMsgTextView;
        TextView rightMsgTextView;

        public MessageListViewHolder(View v) {
            super(v);
            leftMsgLayout = (ConstraintLayout) itemView.findViewById(R.id.chat_left_msg_layout);
            rightMsgLayout = (ConstraintLayout) itemView.findViewById(R.id.chat_right_msg_layout);
            leftMsgTextView = (TextView) itemView.findViewById(R.id.receiver_message_body);
            rightMsgTextView = (TextView) itemView.findViewById(R.id.sender_message_body);
        }
    }

    public MessageListAdapter(List<Message> messageList) {
        this.mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(MessageListViewHolder holder, int position) {
        Message message = (Message) mMessageList.get(position);
        //Log.i("LOOOK AT ME ", mMessageList.get(position) + "");

        if (message.getSender().username.equals(MessageListActivity.getCurrentUser().username)) {
            // Show received message in left linearlayout.
            holder.leftMsgLayout.setVisibility(ConstraintLayout.VISIBLE);
            holder.leftMsgTextView.setText(message.getMessage());
            // Remove left linearlayout.The value should be GONE, can not be INVISIBLE
            // Otherwise each iteview's distance is too big.
            holder.rightMsgLayout.setVisibility(ConstraintLayout.GONE);
        }
        // If the message is a sent message.
        else {
            // Show sent message in right linearlayout.
            holder.rightMsgLayout.setVisibility(ConstraintLayout.VISIBLE);
            holder.rightMsgTextView.setText(message.getMessage());
            // Remove left linearlayout.The value should be GONE, can not be INVISIBLE
            // Otherwise each iteview's distance is too big.
            holder.leftMsgLayout.setVisibility(ConstraintLayout.GONE);
        }
    }

    @Override
    public MessageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.item_message, parent, false);

        return new MessageListViewHolder(view);
    }
}