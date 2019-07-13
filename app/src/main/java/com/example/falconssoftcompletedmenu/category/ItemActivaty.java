package com.example.falconssoftcompletedmenu.category;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.falconssoftcompletedmenu.R;
import com.example.falconssoftcompletedmenu.SettingOrder;
import com.example.falconssoftcompletedmenu.models.Items;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

public class ItemActivaty extends AppCompatActivity {

    TextView catName;
    ImageView catPic, orderImage;
    LinearLayout swipeRefresh;
    RecyclerView recyclerView;
    List<Items>itemList;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activaty);
        Intent userName = getIntent();
        String categoryName = userName.getStringExtra("categoryName");
        String CatPic = userName.getStringExtra("catPic");

        catName = (TextView) findViewById(R.id.catName);
        catPic = (ImageView) findViewById(R.id.catImage);
        orderImage = (ImageView) findViewById(R.id.orderIcon);

        itemList=new ArrayList<>();

        catName.setText(categoryName);
        catPic.setBackgroundResource(getImage(CatPic));
//        baseHandler=new DatabaseHandler(CategoryActivity.this);
//        SettingOrder.Item=baseHandler.getAllItems();
//        swipeRefresh = findViewById(R.id.swipeRefresh);



for(int i=0;i<10;i++) {
    itemList.clear();
    itemList.add(new Items("Burger1", "Burger1", -1, null, "fw", 0.0, null, -1, -1));
    itemList.add(new Items("Burger2", "Burger2", -1, null, "coc", 0.0, null, -1, -1));
    itemList.add(new Items("Burger3", "Burger3", -1, null, "mozaral", 0.0, null, -1, -1));
    itemList.add(new Items("Burger4", "Burger4", -1, null, "der", 0.0, null, -1, -1));
    itemList.add(new Items("Burger5", "Burger5", -1, null, "coc", 0.0, null, -1, -1));
    itemList.add(new Items("Burger6", "Burger6", -1, null, "fe", 0.0, null, -1, -1));
    itemList.add(new Items("Burger7", "Burger7", -1, null, "san", 0.0, null, -1, -1));
    itemList.add(new Items("Burger8", "Burger8", -1, null, "botato", 0.0, null, -1, -1));
    itemList.add(new Items("Burger9", "Burger9", -1, null, "burger", 0.0, null, -1, -1));
    itemList.add(new Items("Burger10","Burger10",-1, null, "botato", 0.0, null, -1, -1));

    SettingOrder.Item.add(i,itemList);
}

        orderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderListDialog();
            }
        });


        for(int x=0;x<SettingOrder.ItemsOrder.size();x++){
            if(SettingOrder.ItemsOrder.get(x).getIndexOfItem()!=-1) {
                if(SettingOrder.indexCat==SettingOrder.ItemsOrder.get(x).getIndexOfCat()){//this for ----
                SettingOrder.Item.get(SettingOrder.indexCat).get(SettingOrder.ItemsOrder.get(x).getIndexOfItem()).setPrice(SettingOrder.ItemsOrder.get(x).getPrice());
            }}
        }

        final LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(HORIZONTAL);
        recyclerView = (RecyclerView) findViewById(R.id.itemRecycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new TestAdapter(this, SettingOrder.Item.get(SettingOrder.indexCat)));

        recyclerView.setItemViewCacheSize(SettingOrder.Item.size());

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

        TextView ItemName, itemDescription, addQty, subQty, balance, Qty;
        ImageView itemImage;
        public static Button addOrder;

        public CViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemName = itemView.findViewById(R.id.item_text);
            itemDescription = itemView.findViewById(R.id.desicription);
            addQty = itemView.findViewById(R.id.addQty);
            subQty = itemView.findViewById(R.id.subQty);
            balance = itemView.findViewById(R.id.TotalPricre);
            Qty = itemView.findViewById(R.id.Qty);
            itemImage = itemView.findViewById(R.id.item_imge);
            addOrder = itemView.findViewById(R.id.addToOrder);

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
            View view = LayoutInflater.from(context).inflate(R.layout.iteam_row, viewGroup, false);
            return new CViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final CViewHolder cViewHolder, final int i) {
            cViewHolder.ItemName.setText(list.get(i).getItemName());
            cViewHolder.itemImage.setBackgroundResource(getImage(list.get(i).getDescription()));
            cViewHolder.Qty.setText("" + SettingOrder.Item.get(SettingOrder.indexCat).get(i).getPrice());

            cViewHolder.addOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Items item = new Items();
                    boolean isFound = updateIfInList(cViewHolder.ItemName.getText().toString(), Double.parseDouble(cViewHolder.Qty.getText().toString()),i);
                    if (Double.parseDouble(cViewHolder.Qty.getText().toString()) != 0) {
                        if (!isFound) {
                            item.setItemName(cViewHolder.ItemName.getText().toString());
//                    item.setItemPic(pic.get(i));
                            item.setCategoryName(catName.getText().toString());
                            item.setPrice(Double.parseDouble(cViewHolder.Qty.getText().toString()));
                            item.setDescription(list.get(i).getDescription());
                            item.setIndexOfItem(i);
                            item.setIndexOfCat(SettingOrder.indexCat);


                            SettingOrder.Item.get(SettingOrder.indexCat).get(i).setPrice(Double.parseDouble(cViewHolder.Qty.getText().toString()));

                            SettingOrder.ItemsOrder.add(item);

                            Log.e("List" + i, "= " + SettingOrder.ItemsOrder.get(SettingOrder.index).getItemName()
                                    + "\n" + SettingOrder.ItemsOrder.get(SettingOrder.index).getItemBarcode() + "\n" +
                                    SettingOrder.ItemsOrder.get(SettingOrder.index).getCategoryName());

                            SettingOrder.index += 1;
                        } else {
                            Toast.makeText(context, "is Found ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Can't Add  the QTY = 0 ", Toast.LENGTH_SHORT).show();
                    }

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
                    double newQty = 0;
                    double oldQty = Double.parseDouble(cViewHolder.Qty.getText().toString());

                    Log.e("oldQty = ", "" + oldQty);

                    newQty = oldQty;
                    newQty += 1;

                    cViewHolder.Qty.setText("" + newQty);


                }
            });


            cViewHolder.subQty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double newQty = 0;
                    double oldQty = Double.parseDouble(cViewHolder.Qty.getText().toString());

                    Log.e("oldQty = ", "" + oldQty);
                    if (oldQty > 0) {
                        newQty = oldQty;
                        cViewHolder.Qty.setText("0");
                        newQty -= 1;
                        cViewHolder.Qty.setText("" + newQty);
                    } else {
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


    public void orderListDialog() {
        Dialog dialog = new Dialog(ItemActivaty.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.order_list_activaty);
        dialog.setCanceledOnTouchOutside(true);

        final LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(VERTICAL);
        final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.orderRecycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new TestItemAdapter(this, SettingOrder.ItemsOrder));
        recyclerView.setItemViewCacheSize(SettingOrder.ItemsOrder.size());

        dialog.show();

    }


    class CViewItemHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView balance, Qty;
        ImageView itemPic;
        ImageButton delete;

//        List<Items>ListOrder=new ArrayList<>();

        public CViewItemHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            balance = itemView.findViewById(R.id.TotalPricre);
            Qty = itemView.findViewById(R.id.Qty);
            itemPic = itemView.findViewById(R.id.itemPic);
            delete = itemView.findViewById(R.id.delete);
        }
    }


    class TestItemAdapter extends RecyclerView.Adapter<CViewItemHolder> {
        Context context;
        List<Items> list;

        public TestItemAdapter(Context context, List<Items> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public CViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_of_list_order, viewGroup, false);
            return new CViewItemHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull final CViewItemHolder cViewItemHolder, final int i) {
            cViewItemHolder.itemName.setText(list.get(i).getItemName());
            cViewItemHolder.itemPic.setBackgroundResource(getImage(list.get(i).getDescription()));
            cViewItemHolder.Qty.setText("" + list.get(i).getPrice());

            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            cViewItemHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.e("size before ", "" + SettingOrder.ItemsOrder.size() + "    " + i + "     " + list.get(i).getIndexOfItem());
                    SettingOrder.Item.get(SettingOrder.indexCat).get(list.get(i).getIndexOfItem()).setPrice(0.0);

                    TestAdapter Ad = new TestAdapter(ItemActivaty.this, SettingOrder.Item.get(SettingOrder.indexCat));
                    recyclerView.setAdapter(Ad);

                    list.remove(i);
                    Log.e("size after ", "" + SettingOrder.ItemsOrder.size() + "    " + i);
                    SettingOrder.index = SettingOrder.ItemsOrder.size();
                    notifyDataSetChanged();
                }
            });


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

    }


    public int getImage(String imageName) {

        int drawableResourceId = ItemActivaty.this.getResources().getIdentifier(imageName, "drawable", ItemActivaty.this.getPackageName());

        return drawableResourceId;
    }

    public boolean updateIfInList(String namePointer, double itemQty,int pointer) {
        boolean isFound = false;
        for (int i = 0; i < SettingOrder.ItemsOrder.size(); i++) {

            if (namePointer.equals(SettingOrder.ItemsOrder.get(i).getItemName())) {
                SettingOrder.Item.get(SettingOrder.indexCat).get(pointer).setPrice(itemQty);
                SettingOrder.ItemsOrder.get(i).setPrice(itemQty);
                isFound = true;
                break;
            }

        }
        return isFound;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SettingOrder.Item.clear();
    }
}