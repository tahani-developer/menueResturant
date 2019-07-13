package com.example.falconssoftcompletedmenu.category;

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
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.falconssoftcompletedmenu.ListOfOrder;
import com.example.falconssoftcompletedmenu.R;
import com.example.falconssoftcompletedmenu.SettingOrder;
import com.example.falconssoftcompletedmenu.models.Items;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.HORIZONTAL;

public class ItemActivaty extends AppCompatActivity {

    TextView catName;
    ImageView catPic,orderImage;
    LinearLayout swipeRefresh;


    public List<String> list = new ArrayList<>();
    //    public List<Items> categoryList = new ArrayList<>();
    List<String> pic = new ArrayList<>();


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activaty);
        Intent userName = getIntent();
        String categoryName = userName.getStringExtra("categoryName");
        String CatPic = userName.getStringExtra("catPic");
        catName =(TextView)findViewById(R.id.catName);
        catPic =(ImageView)findViewById(R.id.catImage);
        orderImage=(ImageView)findViewById(R.id.orderIcon);


        catName.setText(categoryName);
        catPic.setBackgroundResource(getImage(CatPic));
//        baseHandler=new DatabaseHandler(CategoryActivity.this);
//        categoryList=baseHandler.getAllItems();
//        swipeRefresh = findViewById(R.id.swipeRefresh);

        list.add("Burger1");
        list.add("Burger2");
        list.add("Burger3");
        list.add("Burger4");
        list.add("Burger5");
        list.add("Burger6");
        list.add("Burger7");
        list.add("Burger8");
        list.add("Burger9");
        list.add("Burger10");


        pic.add("fw");
        pic.add("coc");
        pic.add("mozaral");
        pic.add("der");
        pic.add("coc");
        pic.add("fe");
        pic.add("san");
        pic.add("botato");
        pic.add("burger");
        pic.add("botato");

        orderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent order=new Intent(ItemActivaty.this, ListOfOrder.class);
                startActivity(order);

            }
        });



        // vertical and cycle layout
        final LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(HORIZONTAL);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.itemRecycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new TestAdapter(this, list));

        recyclerView.setItemViewCacheSize(list.size());


//


//          swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                           @Override
//                         public void onRefresh() {
//
//                               Toast.makeText(CategoryActivity.this, "refresh ..", Toast.LENGTH_SHORT).show();
//                              swipeRefresh.setRefreshing(false);
//                           }
//        swipeRefresh.setRefreshing(false);
//
//
//    }
    }


    static class CViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName,itemDescription,addQty,subQty,balance,Qty;
        ImageView categoryImage;
      public static   Button addOrder;
//        LinearLayout layMain;

        List<Items>ListOrder=new ArrayList<>();

        public CViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.item_text);
            itemDescription = itemView.findViewById(R.id.desicription);
            addQty = itemView.findViewById(R.id.addQty);
            subQty = itemView.findViewById(R.id.subQty);
            balance = itemView.findViewById(R.id.TotalPricre);
            Qty = itemView.findViewById(R.id.Qty);


            categoryImage = itemView.findViewById(R.id.item_imge);
            addOrder=itemView.findViewById(R.id.addToOrder);
//            layMain = itemView.findViewById(R.id.layMain);
        }
    }


    class TestAdapter extends RecyclerView.Adapter<CViewHolder> {
        Context context;
        List<String> list;


//DatabaseHandler db;

        public TestAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
//        db=new DatabaseHandler(this.context);
        }

        @NonNull
        @Override
        public CViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.iteam_row, viewGroup, false);
            return new CViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final CViewHolder cViewHolder, final int i) {
            cViewHolder.categoryName.setText(list.get(i));
//            cViewHolder.layMain.setId(i);
//        cViewHolder.categoryName.setText(list.get(i).getCategoryName());
            cViewHolder.categoryImage.setBackgroundResource(getImage(pic.get(i)));
            cViewHolder.addOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Items item=new Items();

                    item.setItemName(cViewHolder.categoryName.getText().toString());
//                    item.setItemPic(pic.get(i));
                    item.setItemBarcode(Integer.parseInt(cViewHolder.Qty.getText().toString()));
                    item.setCategoryName(catName.getText().toString());
                    item.setPrice(Double.parseDouble(cViewHolder.Qty.getText().toString()));

                    SettingOrder.ItemsOrder.add(item);

                    Log.e("List"+i,"= "+SettingOrder.ItemsOrder.get(SettingOrder.index).getItemName()
                            +"\n"+SettingOrder.ItemsOrder.get(SettingOrder.index).getItemBarcode()+"\n"+
                            SettingOrder.ItemsOrder.get(SettingOrder.index).getCategoryName());

                    SettingOrder.index+=1;


                }
            });

//            cViewHolder.layMain.setOnClickListener(new View.OnClickListener() {
//                @RequiresApi(api = Build.VERSION_CODES.N)
//                @Override
//                public void onClick(View v) {
//                    Log.e("item ...", "i" + v.getId() + "-->" + i + "===>" + list.get(i));
//
////                Intent itemIntent=new Intent(context,ItemsActivity.class);
////                itemIntent.putExtra("categoryName",list.get(i));
////                context.startActivity(itemIntent);
////                CustomIntent.customType(context,"left-to-right");
////             //   bottom-to-up "left-to-right"
////                /**left-to-right
////                 *right-to-left
////                 *bottom-to-up
////                 *up-to-bottom
////                 *fadein-to-fadeout
////                 *rotateout-to-rotatein*/
//                }
//            });

            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            cViewHolder.addQty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int newQty=0;
                    int oldQty= Integer.parseInt(cViewHolder.Qty.getText().toString());

                    Log.e("oldQty = ",""+oldQty);

                    newQty=oldQty;
                  newQty+=1;

                  cViewHolder.Qty.setText(""+newQty);


                }
            });


            cViewHolder.subQty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int newQty=0;
                    int oldQty= Integer.parseInt(cViewHolder.Qty.getText().toString());

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

        int drawableResourceId = ItemActivaty.this.getResources().getIdentifier(imageName, "drawable", ItemActivaty.this.getPackageName());

        return drawableResourceId;
    }

}