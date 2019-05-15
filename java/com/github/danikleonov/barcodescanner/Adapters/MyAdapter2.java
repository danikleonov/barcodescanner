package com.github.danikleonov.barcodescanner.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.danikleonov.barcodescanner.Model.ListItem2;
import com.github.danikleonov.barcodescanner.R;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyAdapterViewHolder> {

    List<ListItem2> listItemsArrayList2;
    Context context;
    public MyAdapter2(List<ListItem2> listItemsArrayList2, Context context){
        this.listItemsArrayList2 = listItemsArrayList2;
        this.context = context;
    }


    @Override
    public MyAdapter2.MyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout2,parent,false);
        return new MyAdapter2.MyAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter2.MyAdapterViewHolder holder, int position) {
        ListItem2 listItem = listItemsArrayList2.get(position);

        holder.textViewType2.setText(listItem.getType());
        holder.textViewCode2.setText(listItem.getCode());
        holder.textViewClass2.setText(listItem.getClas());
        holder.textViewDescr2.setText(listItem.getDescr());
        Linkify.addLinks(holder.textViewCode2,Linkify.ALL);
    }

    @Override
    public int getItemCount() {
        return listItemsArrayList2.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView textViewCode2,textViewType2,textViewClass2,textViewDescr2;
        CardView cardView2;
        public MyAdapterViewHolder(final View itemView){
            super(itemView);
            textViewCode2 = (TextView)itemView.findViewById(R.id.textViewCode2);
            textViewType2 = (TextView)itemView.findViewById(R.id.textViewType2);
            textViewClass2 = (TextView)itemView.findViewById(R.id.textViewClass2);
            textViewDescr2 = (TextView)itemView.findViewById(R.id.textViewDescr2);
            cardView2 = (CardView)itemView.findViewById(R.id.cardView2);

            cardView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String type = listItemsArrayList2.get(getAdapterPosition()).getType();


                    Intent a = new Intent();
                    a.setAction(Intent.ACTION_SEND);
                    a.putExtra(Intent.EXTRA_TEXT,type);
                    a.setType("text/plain");
                    itemView.getContext().startActivity(a);
                }
            });

        }
    }
}
