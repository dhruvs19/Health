package com.myapps.dhruv.health;


import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.docViewHolder> {

    private ArrayList<doctor> arrList = new ArrayList<>();

    RVAdapter(ArrayList<doctor> arrList){

        this.arrList = arrList;
        notifyItemChanged(0,arrList.size());
    }

    public static class docViewHolder extends RecyclerView.ViewHolder{
        public View itemview;
        CardView cv;
        TextView personName;
        TextView personFee;
        ImageView personPhoto;
        TextView personLocation;
        /*TextView personTime;*/
        Button btn;




        public docViewHolder(final View itemView) {
            super(itemView);
            this.itemview = itemView;
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.doc_name);
            personFee = (TextView)itemView.findViewById(R.id.doc_fee);
            personPhoto = (ImageView)itemView.findViewById(R.id.doc_photo);
            personLocation = (TextView)itemView.findViewById(R.id.doc_loc);
            /*personTime = (TextView)itemView.findViewById(R.id.doc_time);*/
            btn = (Button)itemView.findViewById(R.id.book);




            }

    }


    @Override
    public docViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
        docViewHolder pvh = new docViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(docViewHolder DocViewHolder, final int i) {
         DocViewHolder.personName.setText(arrList.get(i).name);
        DocViewHolder.personFee.setText(arrList.get(i).fee);
        DocViewHolder.personLocation.setText(arrList.get(i).location);
      /*  DocViewHolder.personTime.setText(arrList.get(i).timings);*/
        DocViewHolder.personPhoto.setImageResource(arrList.get(i).photoid);
        DocViewHolder.btn.setId(i);


        DocViewHolder.itemview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                Intent intent = new Intent(v.getContext(),specinfo.class);

                intent.putExtra("name",arrList.get(i).name);
                intent.putExtra("fee",arrList.get(i).fee);
                intent.putExtra("loc",arrList.get(i).location);
                intent.putExtra("photoid",arrList.get(i).photoid);
                intent.putExtra("time",arrList.get(i).timings);
                intent.putExtra("num", arrList.get(i).num);
                intent.putExtra("about",arrList.get(i).about);
                Log.v("num2", arrList.get(i).num);



                v.getContext().startActivity(intent);


            }

        });



    }

    @Override
    public int getItemCount() {

        return arrList.size();
    }


}





