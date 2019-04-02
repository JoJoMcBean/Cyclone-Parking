package com.example.cyparking.chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cyparking.R;
import com.github.mikephil.charting.utils.Utils;


public class SentMessageHolder extends RecyclerView.ViewHolder{
        TextView messageText,timeText;

        SentMessageHolder(View itemView){
        super(itemView);
        messageText=(TextView)itemView.findViewById(R.id.text_message_body);
        timeText=(TextView)itemView.findViewById(R.id.text_message_time);
        }

        void bind(Message message){
        messageText.setText(message.getMessage());

        // Format the stored timestamp into a readable String using method.
        timeText.setText(DateUtils.formatDateTime(message.getCreatedAt()));

        }

}