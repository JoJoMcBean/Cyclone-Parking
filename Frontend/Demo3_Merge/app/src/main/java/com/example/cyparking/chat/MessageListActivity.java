//https://blog.sendbird.com/android-chat-tutorial-building-a-messaging-ui
//https://github.com/square/okhttp/blob/master/CHANGELOG.md#version-350
//https://medium.com/@ssaurel/learn-to-use-websockets-on-android-with-okhttp-ba5f00aea988

package com.example.cyparking.chat;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyparking.DefaultUserSchema;
import com.example.cyparking.LoginActivity;
import com.example.cyparking.R;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import com.example.cyparking.chat.Socket.OnEventListener;
import com.example.cyparking.chat.Socket.OnEventResponseListener;

import com.example.cyparking.parkinglog.ParkingLog;

public class MessageListActivity extends AppCompatActivity {
    private static String URL = "ws://cs309-yt-2.misc.iastate.edu:8080";
    private static String WEBSOCKET_URL = URL + "/websocket/chat";

    private Button mChatBoxSend;
    private EditText mChatBox;


    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private ArrayList<Message> messageList = new ArrayList<>();

    public static ChatUser thisUser = new ChatUser("poop");
    public static ChatUser testUser = new ChatUser("guy");

    private OkHttpClient client;
    private Listener socketOpenListener;
    private ResponseListener socketResponseListener;


    Socket webSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        //Init
        client = new OkHttpClient();
        socketOpenListener = new Listener();
        socketResponseListener = new ResponseListener();
        start();

        //Message View
        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMessageRecycler.setLayoutManager(linearLayoutManager);
        mMessageAdapter = new MessageListAdapter(messageList);
        mMessageRecycler.setAdapter(mMessageAdapter);

        //Send Message action
        mChatBox= findViewById(R.id.edittext_chatbox);
        mChatBoxSend= findViewById(R.id.button_chatbox_send);
        mChatBoxSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(mChatBox.getText().toString());
                mChatBox.setText("");
            }
        });
    }

    private class Listener extends OnEventListener {
        @Override
        public void onMessage (String event) {
            Toast.makeText(getBaseContext(), event + "WEBSOCKET OPENED" , Toast.LENGTH_SHORT).show();
        }
    }

    private class ResponseListener extends OnEventResponseListener {
        @Override
        public void onMessage(String event, String data) {
            Toast.makeText(getBaseContext(), data , Toast.LENGTH_SHORT).show();
            Message message = new Message(thisUser, data, 0000);
            messageList.add(message);
        }
    }

    public static ChatUser getCurrentUser() {
        return thisUser;
    }

    private void start() {
        webSocket = Socket.Builder.with(WEBSOCKET_URL).build();
        webSocket.connect();
        webSocket.onEvent(Socket.EVENT_OPEN, socketOpenListener);
        //webSocket.onEvent(Socket.EVENT_RECONNECT_ATTEMPT, .....);
        //webSocket.onEvent(Socket.EVENT_CLOSED, .....);
        webSocket.onEventResponse("Message Recieved", socketResponseListener);
        //webSocket.send("Some event", "{"some data":"in JSON format"}");
        //webSocket.sendOnOpen("Some event", "{"some data":"in JSON format"}");
    }

    private void sendMessage(String rawMsg) {
        Log.i("Message", rawMsg);
        //LocalDateTime now = LocalDateTime.now();
        Message message = new Message(thisUser, rawMsg, 0000);
        messageList.add(message);
        webSocket.send(message.getMessage());
    }

}
