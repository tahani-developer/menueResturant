package com.example.falconssoftcompletedmenu.category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.falconssoftcompletedmenu.R;
import com.example.falconssoftcompletedmenu.SendSocket;

import java.util.ArrayList;
import java.util.List;

import cdflynn.android.library.turn.TurnLayoutManager;

public class CategoryActivity extends AppCompatActivity {

    TextView UserNameText;
    LinearLayout swipeRefresh;
    Button CallCaptain;


    public List<String> list = new ArrayList<>();
    //    public List<Items> categoryList = new ArrayList<>();
    List<String> pic = new ArrayList<>();
    int po;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_listview);
        Intent userName = getIntent();
        String users = userName.getStringExtra("userName");

        UserNameText=(TextView)findViewById(R.id.userName);

        UserNameText.setText(users);
//        baseHandler=new DatabaseHandler(CategoryActivity.this);
//        categoryList=baseHandler.getAllItems();
        swipeRefresh = findViewById(R.id.swipeRefresh);
        CallCaptain=findViewById(R.id.call);

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
        pic.add("der");
        pic.add("mozaral");
        pic.add("der");
        pic.add("coc");
        pic.add("fe");
        pic.add("san");
        pic.add("botato");
        pic.add("burger");
        pic.add("botato");

        // vertical and cycle layout
        final TurnLayoutManager layoutManager;
        layoutManager = new TurnLayoutManager(this,
                TurnLayoutManager.Gravity.START,
                TurnLayoutManager.Orientation.HORIZONTAL,
                300,
                300,
                false);


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.categoryRecycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new TestAdapter(this, list));

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("itemRec", "");

            }
        });
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


        CallCaptain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendSocket sendSocket=new SendSocket(CategoryActivity.this);
                sendSocket.sendMessage();

            }
        });
    }



    class CViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;
        ImageView categoryImage;
        LinearLayout layMain;


        public CViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_text);
            categoryImage = itemView.findViewById(R.id.category_imge);
            layMain = itemView.findViewById(R.id.layMain);
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
            View view = LayoutInflater.from(context).inflate(R.layout.categoty_layout, viewGroup, false);
            return new CViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final CViewHolder cViewHolder, final int i) {
            cViewHolder.categoryName.setText(list.get(i));
//            cViewHolder.layMain.setId(i);
//        cViewHolder.categoryName.setText(list.get(i).getCategoryName());
        cViewHolder.categoryImage.setBackgroundResource(getImage(pic.get(i)));


            cViewHolder.layMain.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    Log.e("item ...", "i" + v.getId() + "-->" + i + "===>" + list.get(i));

                Intent itemIntent=new Intent(context,ItemActivaty.class);
                itemIntent.putExtra("categoryName",list.get(i));
                itemIntent.putExtra("catPic",pic.get(i));
                context.startActivity(itemIntent);
//                CustomIntent.customType(context,"left-to-right");
//             //   bottom-to-up "left-to-right"
//                /**left-to-right
//                 *right-to-left
//                 *bottom-to-up
//                 *up-to-bottom
//                 *fadein-to-fadeout
//                 *rotateout-to-rotatein*/
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

        int drawableResourceId =CategoryActivity.this.getResources().getIdentifier(imageName, "drawable", CategoryActivity.this.getPackageName());

        return drawableResourceId;
    }

}