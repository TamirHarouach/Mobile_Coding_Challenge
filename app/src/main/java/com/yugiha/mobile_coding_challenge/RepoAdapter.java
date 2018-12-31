package com.yugiha.mobile_coding_challenge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.MViewHolder>{

    private Context mContext;
    private List<RepoClass> mClass;

    public RepoAdapter(Context mContext, List<RepoClass> mClass) {
        this.mContext = mContext;
        this.mClass = mClass;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_layout, parent, false); //inflate the row_layout which gives us a view
        return new MViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int position) {
        RepoClass repoCurrent = mClass.get(position); //get the position
        holder.textViewName.setText(repoCurrent.getRepo_Name()); // set the name into the TextView
        holder.textViewDescription.setText(repoCurrent.getRepo_Description());// set the Description into the TextView
        holder.textViewOwnerName.setText(repoCurrent.getRepo_Owner_Name());// set the Owner name into the TextView
        /* changing the format of number of stars */
        long value = repoCurrent.getStars();
        if (value <= 999) {
            holder.textViewStars.setText(String.valueOf(value));// set the Number of stars  into the TextView if it is <=999
        }else{
         String[] units = new String[]{"", "K", "M"};
        int digitGroups = (int) (Math.log10(value) / Math.log10(1000));
           String val ="" +new DecimalFormat("#,##0.#").format(value / Math.pow(1000, digitGroups)) + "" + units[digitGroups];
           holder.textViewStars.setText(val);//set the Number of stars  into the TextView if it is > 999
        }
        // use the Picasso library to get the avatar from Image URL and set it into the imageView
        Picasso.get()
                .load(repoCurrent.getRepo_ImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageViewOwner);
    }

    @Override
    public int getItemCount() {
        return mClass.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewDescription;
        public TextView textViewOwnerName;
        public TextView textViewStars;
        public ImageView imageViewOwner;
        public ImageView imageViewStar;


        public MViewHolder(View itemView) {
            super(itemView);
            // declaration of the Views
            textViewName = itemView.findViewById(R.id.repo_name);
            textViewDescription = itemView.findViewById(R.id.repo_description);
            textViewOwnerName = itemView.findViewById(R.id.repo_owner_name);
            textViewStars = itemView.findViewById(R.id.number_stars);
            imageViewOwner = itemView.findViewById(R.id.repo_owner_avatar);
            imageViewStar = itemView.findViewById(R.id.star);

    }
}
}





