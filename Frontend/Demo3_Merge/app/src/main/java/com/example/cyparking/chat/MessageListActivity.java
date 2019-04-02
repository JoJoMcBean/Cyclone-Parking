//https://blog.sendbird.com/android-chat-tutorial-building-a-messaging-ui
//https://github.com/square/okhttp/blob/master/CHANGELOG.md#version-350
//https://medium.com/@ssaurel/learn-to-use-websockets-on-android-with-okhttp-ba5f00aea988

package com.example.cyparking.chat;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.cyparking.DefaultUserSchema;
import com.example.cyparking.LoginActivity;
import com.example.cyparking.R;

import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import com.example.cyparking.chat.Socket.OnEventListener;
import com.example.cyparking.parkinglog.ParkingLog;

public class MessageListActivity extends AppCompatActivity {
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private ArrayList<Message> messageList = new ArrayList<>();

    public static ChatUser thisUser;

    private OkHttpClient client;
    private static String WEBSOCKET_BASE_URL = "ws://echo.websocket.org";
    private Listener socketOpenListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        messageList.add(new Message(thisUser, "hii", 3434));

        thisUser = new ChatUser("poop");
        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new MessageListAdapter(this, messageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));


        client = new OkHttpClient();
        socketOpenListener = new Listener();
        start();
    }

    private class Listener extends OnEventListener {
        @Override
        public void onMessage (String event) {
            Toast.makeText(getBaseContext(), event + " WEBSOCKET OPENED" , Toast.LENGTH_SHORT).show();
        }
    }

    public static ChatUser getCurrentUser() {
        return thisUser;
    }

    private void start() {
        Socket webSocket = Socket.Builder.with(WEBSOCKET_BASE_URL).build();
        webSocket.connect();
        webSocket.onEvent(Socket.EVENT_OPEN, socketOpenListener);
        //webSocket.onEvent(Socket.EVENT_RECONNECT_ATTEMPT, .....);
        //webSocket.onEvent(Socket.EVENT_CLOSED, .....);
        //webSocket.onEventResponse("Some event", socketPairListener);
        //webSocket.send("Some event", "{"some data":"in JSON format"}");
        //webSocket.sendOnOpen("Some event", "{"some data":"in JSON format"}");
    }

}
