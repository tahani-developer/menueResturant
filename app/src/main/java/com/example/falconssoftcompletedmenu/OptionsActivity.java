package com.example.falconssoftcompletedmenu;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.falconssoftcompletedmenu.models.Tables;

import java.util.ArrayList;
import java.util.List;

public class OptionsActivity extends AppCompatActivity implements View.OnClickListener {

    private TableLayout floorNo, tableNo;
    private EditText noOfSeats;
    private TextView addFloor, addTable;
    private Button saveOptions;
    private static int flagFloor = 0; // up
    private static int flagTable = 0; // up

    private List<Tables> tablesList = new ArrayList<>();
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        databaseHandler = new DatabaseHandler(this);
        filList();

        floorNo = findViewById(R.id.options_floorNo_layout);
        tableNo = findViewById(R.id.options_tableNo_layout);
        noOfSeats = findViewById(R.id.options_noOfSeats_ET);
        addFloor = findViewById(R.id.options_add_floor);
        addTable = findViewById(R.id.options_add_table);

        saveOptions = findViewById(R.id.options_done);
        addFloor.setOnClickListener(this);
        addTable.setOnClickListener(this);

        saveOptions.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.options_done:
                break;
            case R.id.options_add_floor:
//                if (flagTable == 1)
                    addTable.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_keyboard_arrow_down_yellow_24dp));
                fillAdapters(1);
                break;
            case R.id.options_add_table:
//                if (flagFloor == 1)
                    addFloor.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_keyboard_arrow_down_yellow_24dp));
                fillAdapters(2);
                break;
        }

    }

    void filList() {
        Tables tables = new Tables();
        tables.setSectionNo("Gf");
        tables.setTableNo(1);
        tables.setNoOfSeits(2);
        tablesList.add(tables);

//        databaseHandler.addTable(tables);

        tables.setSectionNo("Gf");
        tables.setTableNo(2);
        tables.setNoOfSeits(3);
        tablesList.add(tables);
//        databaseHandler.addTable(tables);

        tables.setSectionNo("Gf");
        tables.setTableNo(3);
        tables.setNoOfSeits(4);
        tablesList.add(tables);
//        databaseHandler.addTable(tables);

        tables.setSectionNo("floor 1");
        tables.setTableNo(1);
        tables.setNoOfSeits(2);
        tablesList.add(tables);
//        databaseHandler.addTable(tables);

        tables.setSectionNo("floor 1");
        tables.setTableNo(1);
        tables.setNoOfSeits(2);
        tablesList.add(tables);
//        databaseHandler.addTable(tables);

        tables.setSectionNo("floor 1");
        tables.setTableNo(1);
        tables.setNoOfSeits(2);
        tablesList.add(tables);
//        databaseHandler.addTable(tables);

        for (int i = 0; i < tablesList.size(); i++)
            databaseHandler.addTable(tablesList.get(i));

        Log.e("size", "" + tablesList.size());

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void fillAdapters(int type) {
        floorNo.removeAllViews();
        tableNo.removeAllViews();

        if (type == 1) {
            if (flagFloor == 0) {
                addFloor.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_keyboard_arrow_up_yeloow_24dp));
                flagFloor = 1;
                for (int i = 0; i < tablesList.size(); i++) {
                    TableRow row = new TableRow(this);
                    TextView floorNoTV = new TextView(this);
                    floorNoTV.setText(tablesList.get(i).getSectionNo());
                    floorNoTV.setTextSize(18);
                    floorNoTV.setBackgroundColor(ContextCompat.getColor(this, R.color.gray_light));
                    floorNoTV.setGravity(Gravity.CENTER);

                    TableRow.LayoutParams tvParams = new TableRow.LayoutParams(610, TableRow.LayoutParams.WRAP_CONTENT);
                    tvParams.setMargins(20, 2, 0, 0);
                    floorNoTV.setLayoutParams(tvParams);

                    row.addView(floorNoTV);
                    floorNo.addView(row);
                }
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.down_from_bottom);
                floorNo.startAnimation(animation);
            } else {
                addFloor.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_keyboard_arrow_down_yellow_24dp));
//                animation.cancel();
                flagFloor = 0;

            }
        }


        if (type == 2) {
            if (flagTable == 0) {
                addTable.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_keyboard_arrow_up_yeloow_24dp));
                flagTable = 1;
                for (int i = 0; i < tablesList.size(); i++) {
                    TableRow row = new TableRow(this);
                    TextView tableNoTV = new TextView(this);
                    tableNoTV.setText("" + tablesList.get(i).getTableNo());
                    tableNoTV.setTextSize(16);
                    tableNoTV.setBackgroundColor(ContextCompat.getColor(this, R.color.gray_light));
                    tableNoTV.setGravity(Gravity.CENTER);

                    TableRow.LayoutParams tvParams = new TableRow.LayoutParams(610, TableRow.LayoutParams.WRAP_CONTENT);
                    tvParams.setMargins(20, 2, 0, 0);
                    tableNoTV.setLayoutParams(tvParams);

                    row.addView(tableNoTV);
                    tableNo.addView(row);
                }
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.down_from_bottom);
                tableNo.startAnimation(animation);
            } else {
                addTable.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_keyboard_arrow_down_yellow_24dp));
//                animation.cancel();
                flagTable = 0;

            }

        }

    }


}
