package com.example.bakhtiyar.simplechatapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 12/19/2016.
 */
public class SentReadMsgsAdapter extends BaseAdapter {

    ArrayList<SentReadMsgs> msgsArrayList;

    ImageButton imageButton;

    Context context;

    LayoutInflater inflater;

    TextView sendRcv, msg, date;

    public SentReadMsgsAdapter(ArrayList<SentReadMsgs> msgsArrayList, Context context) {
        this.msgsArrayList = msgsArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return msgsArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        view = inflater.from(context).inflate(R.layout.sent_read_msgs,viewGroup,false);

        if(msgsArrayList.get(i).isFlag()){

            view = inflater.from(context).inflate(R.layout.sent_read_msgs,viewGroup,false);


            imageButton = (ImageButton) view.findViewById(R.id.sendimgbuttn);

            sendRcv = (TextView) view.findViewById(R.id.username);

            msg = (TextView) view.findViewById(R.id.usermsg);

            date = (TextView) view.findViewById(R.id.dateRcvSend);


            boolean isPhoto = msgsArrayList.get(i).getDownload() != null;
            if (isPhoto) {
                msg.setVisibility(View.GONE);
                date.setVisibility(View.VISIBLE);
                sendRcv.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                date.setText(msgsArrayList.get(i).getFrom() +"\t"+ msgsArrayList.get(i).getDate()+"\n");
                sendRcv.setText(msgsArrayList.get(i).getUser());

                Glide.with(imageButton.getContext())
                        .load(msgsArrayList.get(i).getDownload())
                        .into(imageButton);
            } else {
                msg.setVisibility(View.VISIBLE);

                imageButton.setVisibility(View.GONE);

                sendRcv.setText(msgsArrayList.get(i).getUser());

                msg.setText("\n"+msgsArrayList.get(i).getMsgs());

                date.setText(msgsArrayList.get(i).getFrom() +"\t"+ msgsArrayList.get(i).getDate());
            }



//            sendRcv.setText(msgsArrayList.get(i).getUser());
//
//            msg.setText("\n"+msgsArrayList.get(i).getMsgs());
//
//            date.setText(msgsArrayList.get(i).getFrom() +"\t"+ msgsArrayList.get(i).getDate());



        }
        else if(! (msgsArrayList.get(i).isFlag())) {
            view = inflater.from(context).inflate(R.layout.rcv_msg_list,viewGroup,false);




            imageButton = (ImageButton) view.findViewById(R.id.rcvimgbuttn);



            msg = (TextView) view.findViewById(R.id.sendusermsg);

            date = (TextView) view.findViewById(R.id.senddateRcvSend);



//            msg.setText("\n"+msgsArrayList.get(i).getMsgs());
//
//            date.setText(msgsArrayList.get(i).getFrom() +"\t"+ msgsArrayList.get(i).getDate());


            boolean isPhoto = msgsArrayList.get(i).getDownload() != null;
            if (isPhoto) {
                msg.setVisibility(View.GONE);
                date.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                date.setText(msgsArrayList.get(i).getFrom() +"\t"+ msgsArrayList.get(i).getDate()+"\n");
//                sendRcv.setText(msgsArrayList.get(i).getUser());
                Glide.with(imageButton.getContext())
                        .load(msgsArrayList.get(i).getDownload())
                        .into(imageButton);
            } else {
                msg.setVisibility(View.VISIBLE);

                imageButton.setVisibility(View.GONE);


                msg.setText("\n"+msgsArrayList.get(i).getMsgs());

                date.setText(msgsArrayList.get(i).getFrom() +"\t"+ msgsArrayList.get(i).getDate());
            }




        }
        else{

            return null;

        }



        return view;
    }
}
