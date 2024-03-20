package com.thecrackertechnology.andrax;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.List;

public class AdapterTutorial extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    DataTutorial current;
    int currentPos = 0;
    List<DataTutorial> data = Collections.emptyList();
    private LayoutInflater inflater;
    /* access modifiers changed from: private */
    public Context mContext;

    AdapterTutorial(Context context, List<DataTutorial> list) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(this.mContext);
        this.data = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyHolder(this.inflater.inflate(R.layout.container_tutorial, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        MyHolder myHolder = (MyHolder) viewHolder;
        final DataTutorial dataTutorial = this.data.get(i);
        myHolder.textTutorialName.setText(dataTutorial.TutorialName);
        myHolder.textTutorialdesc.setText(dataTutorial.Tutorialdesc);
        myHolder.textTutorialLink = dataTutorial.Tutoriallink;
        myHolder.cardtutorial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context access$000 = AdapterTutorial.this.mContext;
                Toast.makeText(access$000, "Tutorial: " + dataTutorial.TutorialName, 0).show();
                AdapterTutorial.this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(dataTutorial.Tutoriallink)));
            }
        });
        RequestManager with = Glide.with(this.mContext);
        with.load("https://andrax.thecrackertechnology.com/app-imgs/" + dataTutorial.TutorialImage).placeholder((int) R.drawable.andrax_banner).error((int) R.drawable.andrax_banner).into(myHolder.imgTutorialimage);
    }

    public int getItemCount() {
        return this.data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        CardView cardtutorial;
        ImageView imgTutorialimage;
        String textTutorialLink;
        TextView textTutorialName;
        TextView textTutorialdesc;

        public MyHolder(View view) {
            super(view);
            this.textTutorialName = (TextView) view.findViewById(R.id.tutorialnome);
            this.imgTutorialimage = (ImageView) view.findViewById(R.id.imagetutorial);
            this.textTutorialdesc = (TextView) view.findViewById(R.id.tutorialdesc);
            this.cardtutorial = (CardView) view.findViewById(R.id.card_view_tutorial);
        }
    }
}
