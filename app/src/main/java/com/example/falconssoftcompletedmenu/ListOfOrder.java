package com.example.falconssoftcompletedmenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.falconssoftcompletedmenu.category.ItemActivaty;
import com.example.falconssoftcompletedmenu.models.Items;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

public class ListOfOrder extends AppCompatActivity {

    TextView catName;
    ImageView catPic;



    //public List<String> list = new ArrayList<>();
    //    public List<Items> categoryList = new ArrayList<>();
    List<String> pic = new ArrayList<>();


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list_activaty);


        final LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(VERTICAL);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.orderRecycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new TestAdapter(this, SettingOrder.ItemsOrder));
        recyclerView.setItemViewCacheSize(SettingOrder.ItemsOrder.size());


    }


    class CViewHolder extends RecyclerView.ViewHolder {

//        TextView categoryName,itemDescription;
        TextView addQty,subQty,balance,Qty;
        ImageView categoryImage;
        Button addOrder;
//        LinearLayout layMain;

//        List<Items>ListOrder=new ArrayList<>();

        public CViewHolder(@NonNull View itemView) {
            super(itemView);
//            categoryName = itemView.findViewById(R.id.item_text);
//            itemDescription = itemView.findViewById(R.id.desicription);
            addQty = itemView.findViewById(R.id.addQty);
            subQty = itemView.findViewById(R.id.subQty);
            balance = itemView.findViewById(R.id.TotalPricre);
            Qty = itemView.findViewById(R.id.Qty);


            categoryImage = itemView.findViewById(R.id.item_imge);
//            addOrder=itemView.findViewById(R.id.addToOrder);
//            layMain = itemView.findViewById(R.id.layMain);
        }
    }



    class TestAdapter extends RecyclerView.Adapter<CViewHolder> {
        Context context;
        List<Items> list;


//DatabaseHandler db;

        public TestAdapter(Context context, List<Items> list) {
            this.context = context;
            this.list = list;
//        db=new DatabaseHandler(this.context);
        }

        @NonNull
        @Override
        public CViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_of_list_order, viewGroup, false);
            return new CViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final CViewHolder cViewHolder, final int i) {
//            cViewHolder.categoryName.setText(list.get(i).getItemName());
//            cViewHolder.layMain.setId(i);
//        cViewHolder.categoryName.setText(list.get(i).getCategoryName());
//            cViewHolder.categoryImage.setBackgroundResource(getImage(pic.get(i)));
//            cViewHolder.addOrder.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Items item=new Items();
//
////                    item.setItemName(cViewHolder.categoryName.getText().toString());
////                    item.setItemPic(pic.get(i));
//                    item.setItemBarcode(Integer.parseInt(cViewHolder.Qty.getText().toString()));
//                    item.setCategoryName(catName.getText().toString());
//
//                    SettingOrder.ItemsOrder.add(item);
//
//                    Log.e("List"+i,"= "+SettingOrder.ItemsOrder.get(SettingOrder.index).getItemName()
//                            +"\n"+SettingOrder.ItemsOrder.get(SettingOrder.index).getItemBarcode()+"\n"+
//                            SettingOrder.ItemsOrder.get(SettingOrder.index).getCategoryName());
//
//                    SettingOrder.index+=1;
//
//
//                }
//            });

            cViewHolder.Qty.setText(""+list.get(i).getPrice());


            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            cViewHolder.addQty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double newQty=0;
                    double oldQty= Double.parseDouble(cViewHolder.Qty.getText().toString());

                    Log.e("oldQty = ",""+oldQty);

                    newQty=oldQty;
                    newQty+=1;

                    cViewHolder.Qty.setText(""+newQty);


                }
            });


            cViewHolder.subQty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double newQty=0;
                    double oldQty= Double.parseDouble(cViewHolder.Qty.getText().toString());

                    Log.e("oldQty = ",""+oldQty);
                    if(oldQty>0)
                    {
                        newQty=oldQty;
                        cViewHolder.Qty.setText("0");
                        newQty-=1;
                        cViewHolder.Qty.setText(""+newQty);
                    }else{
                        Toast.makeText(context, "The Qty Value = 0 ", Toast.LENGTH_SHORT).show();
                    }




                }
            });



        }

        @Override
        public int getItemCount() {
            return list.size();
//            return Integer.MAX_VALUE;
        }

    }

    public int getImage(String imageName) {

        int drawableResourceId = ListOfOrder.this.getResources().getIdentifier(imageName, "drawable",ListOfOrder.this.getPackageName());

        return drawableResourceId;
    }

}