package com.example.foodordering.Activity.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Activity.Inteface.ItemClickListner;
import com.example.foodordering.R;


public class cartviewholder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView txttitle,txtprice,txtqty;

    private ItemClickListner itemClickListner;

    public cartviewholder(@NonNull View itemView) {
        super(itemView);

        txttitle = itemView.findViewById(R.id.title2Txt);
        txtqty = itemView.findViewById(R.id.txtqtyincart);
        txtprice = itemView.findViewById(R.id.txtpriceincart);


    }


    @Override
    public void onClick(View view)
    {

itemClickListner.onClick(view,getAdapterPosition(),false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
}
