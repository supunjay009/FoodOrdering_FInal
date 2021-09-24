package com.example.foodordering.Activity.Adapter;

import static java.lang.String.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Activity.Activity.MainActivity;
import com.example.foodordering.Activity.Activity.cartlist;
import com.example.foodordering.Activity.Domain.Cart;
import com.example.foodordering.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class cartviewAdapter extends RecyclerView.Adapter<cartviewAdapter.ViewHolder>
{
    int totalprice,toteach=0;
    Context context;
    ArrayList<Cart> cartArrayList;
    DatabaseReference reference;
    private Intent MainActivity;

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
    public void onBindViewHolder(@NonNull cartviewAdapter.ViewHolder holder, @SuppressLint("RecyclerView")   final int position) {

        final Cart cart = cartArrayList.get(position);


        //get img frm db to cart
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("images");;
        StorageReference photoRef = storageReference.child(cart.getImages());
        photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //holder.imgFood.setImageURI(uri);
                Picasso.get().load(uri).into(holder.img);
            }
        });

        holder.txtname.setText(valueOf(cart.getFname()));
        holder.txtqty.setText(valueOf("Quantity : "+cart.getQty()));
        holder.txtprice.setText(valueOf("LKR "+cart.getPrice()+".00"));
        holder.fid.setText(valueOf(cart.getFid()));


        caltot(position);

         holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fid =String.valueOf(cartArrayList.get(position).getFid());
                cartArrayList.remove(position);
                notifyDataSetChanged();
               deletefid(fid,position);




            }
        });


 cartlist.orderbtn.setOnClickListener(new View.OnClickListener() {
        @Override
          public void onClick(View v) {

              placeorder(holder);

          }   });
    }

    private void placeorder(ViewHolder holder) {
        final DatabaseReference orderlistref = FirebaseDatabase.getInstance().getReference().child("orders");
        String saveCurrentTime,saveCurrentDate;

        Calendar calForDate =  Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());
        String id = saveCurrentDate.toString()+saveCurrentTime.toString();
        int tno = Integer.parseInt(holder.tableno.getText().toString());
        final HashMap<String,Object> cartmap = new HashMap<>();
        cartmap.put("id",id);
        cartmap.put("tableNo",tno);
        cartmap.put("served",false);

        orderlistref.child(String.valueOf(id)).updateChildren(cartmap).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                  // Toast.makeText("Your order is placed.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        final HashMap<String,Object> itemmap = new HashMap<>();
        for (int i=0;i<cartArrayList.size();i++) {


            itemmap.put("id",cartArrayList.get(i).getFid());
            itemmap.put("image",cartArrayList.get(i).getImages());
            itemmap.put("name",cartArrayList.get(i).getFname());
            itemmap.put("qty",cartArrayList.get(i).getQty());
            itemmap.put("totprice",totalprice);

            orderlistref.child(String.valueOf(id)).child("items").child(String.valueOf(cartArrayList.get(i).getFid())).updateChildren(itemmap).addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                    }
                }
            });
        }


    }

    private void caltot(int position) {


            int qty =Integer.valueOf(cartArrayList.get(position).getQty());
            int price =Integer.valueOf(cartArrayList.get(position).getPrice());
            toteach=qty*price;
            totalprice=totalprice+toteach;
            Intent intent = new Intent("MyTotalAmmount");
            intent.putExtra("totalAmmount",totalprice);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);




    }

    private void deletefid(String fid,int position) {
        reference = FirebaseDatabase.getInstance().getReference().child("CartList");
        reference.child("foodlist").child(fid).removeValue();
        totalprice=0;

    }

    public void setData(ArrayList<Cart> citems) {
        this.cartArrayList = citems;
        notifyDataSetChanged();
    }




    @Override
    public int getItemCount() {

        return cartArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtname,txtqty,txtprice,fid,tableno;
        ImageView delete,img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.title2Txt);
            txtqty = itemView.findViewById(R.id.txtqtyincart);
            txtprice = itemView.findViewById(R.id.txtpriceincart);
            delete = itemView.findViewById(R.id.imageView8);
            fid = itemView.findViewById(R.id.fidtxtincart);
            img = itemView.findViewById(R.id.picCard);
            tableno = itemView.findViewById(R.id.textView16);
        }
    }



}
