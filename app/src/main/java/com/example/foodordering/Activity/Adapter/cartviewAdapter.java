package com.example.foodordering.Activity.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Activity.Domain.Cart;
import com.example.foodordering.R;

import java.util.ArrayList;


public class cartviewAdapter extends RecyclerView.Adapter<cartviewAdapter.ViewHolder>
{


    ArrayList<Cart> cartArrayList;

    public cartviewAdapter(ArrayList<Cart> cartArrayList) {
        this.cartArrayList = cartArrayList;
    }

    @NonNull
    @Override
    public cartviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cartviewAdapter.ViewHolder holder, @SuppressLint("RecyclerView")  final int position) {

        final Cart cart = cartArrayList.get(position);
        holder.txtname.setText(String.valueOf(cart.getFname()));
        holder.txtqty.setText(String.valueOf("Quantity : "+cart.getQty()));
        holder.txtprice.setText(String.valueOf(cart.getPrice()));


      //  getTotalFee();


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartArrayList.remove(position);
                notifyDataSetChanged();
               // getTotalFee();
            }
        });

    }
    public void setData(ArrayList<Cart> citems) {
        this.cartArrayList = citems;
        notifyDataSetChanged();
    }

   //public  Double  getTotalFee() {

      // Double toteach,total=0.00;;
      //  for (int i = 0; i < cartArrayList.size(); i++) {
           // toteach =Double.valueOf(cartArrayList.get(i).getPrice())*Integer.valueOf(cartArrayList.get(i).getQty());
            //total=total+toteach;
      //  }
        //return total;
   // }


    @Override
    public int getItemCount() {

        return cartArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtname,txtqty,txtprice,totaltxt;
        ImageView delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.title2Txt);
            txtqty = itemView.findViewById(R.id.txtqtyincart);
            txtprice = itemView.findViewById(R.id.txtpriceincart);
            delete = itemView.findViewById(R.id.imageView8);


        }
    }



}
