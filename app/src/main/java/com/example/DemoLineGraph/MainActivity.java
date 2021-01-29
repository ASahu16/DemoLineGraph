package com.example.DemoLineGraph;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_month_name;
    private LinearLayout linearLayout;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private TextView textView11;

    private TextView textView12;
    private TextView tv_total_spend;
    private MaterialCardView cv_shopping;
    private ImageView imageView1;
    private TextView tv_shopping_amount;
    private TextView tv_shopping_percent;
    private MaterialCardView cv_food_drink;
    private ImageView imageView2;
    private TextView tv_food_drink_amount;
    private TextView tv_food_drink_percent;
    private MaterialCardView cv_travel;
    private ImageView imageView3;
    private TextView tv_travel_amount;
    private TextView tv_travel_percent;
    private MaterialCardView cv_fuel;
    private ImageView imageView4;
    private TextView tv_fuel_amount;
    private TextView tv_fuel_percent;
    private MaterialCardView cv_emi;
    private ImageView imageView5;
    private TextView tv_emi_amount;
    private TextView tv_emi_percent;
    private MaterialCardView cv_others;
    private ImageView imageView6;
    private TextView tv_others_amount;
    private TextView tv_others_percent;


    private LineChart graph;
    private Typeface mTf = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
    private List<ModelData> modelData;
    private ArrayList<Entry> values2 = new ArrayList<>();
    private LineDataSet set2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initViews();
        calculate();
    }

    private void initViews() {
        tv_month_name = (TextView) findViewById(R.id.tv_month_name);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);
        textView11 = (TextView) findViewById(R.id.textView11);
        graph = findViewById(R.id.graph);
        textView12 = (TextView) findViewById(R.id.textView12);
        tv_total_spend = (TextView) findViewById(R.id.tv_total_spend);
        cv_shopping = (MaterialCardView) findViewById(R.id.cv_shopping);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        tv_shopping_amount = (TextView) findViewById(R.id.tv_shopping_amount);
        tv_shopping_percent = (TextView) findViewById(R.id.tv_shopping_percent);
        cv_food_drink = (MaterialCardView) findViewById(R.id.cv_food_drink);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        tv_food_drink_amount = (TextView) findViewById(R.id.tv_food_drink_amount);
        tv_food_drink_percent = (TextView) findViewById(R.id.tv_food_drink_percent);
        cv_travel = (MaterialCardView) findViewById(R.id.cv_travel);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        tv_travel_amount = (TextView) findViewById(R.id.tv_travel_amount);
        tv_travel_percent = (TextView) findViewById(R.id.tv_travel_percent);
        cv_fuel = (MaterialCardView) findViewById(R.id.cv_fuel);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        tv_fuel_amount = (TextView) findViewById(R.id.tv_fuel_amount);
        tv_fuel_percent = (TextView) findViewById(R.id.tv_fuel_percent);
        cv_emi = (MaterialCardView) findViewById(R.id.cv_emi);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        tv_emi_amount = (TextView) findViewById(R.id.tv_emi_amount);
        tv_emi_percent = (TextView) findViewById(R.id.tv_emi_percent);
        cv_others = (MaterialCardView) findViewById(R.id.cv_others);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        tv_others_amount = (TextView) findViewById(R.id.tv_others_amount);
        tv_others_percent = (TextView) findViewById(R.id.tv_others_percent);

        graph = findViewById(R.id.graph);

        String jsonData = getJsonFromAsset();
        modelData = new Gson().fromJson(jsonData, new TypeToken<List<ModelData>>() {
        }.getType());
        setInitialSet(modelData);

        cv_shopping.setOnClickListener(this);
        cv_food_drink.setOnClickListener(this);
        cv_travel.setOnClickListener(this);
        cv_fuel.setOnClickListener(this);
        cv_emi.setOnClickListener(this);
        cv_others.setOnClickListener(this);
    }

    private void calculate() {
        double total = 0, shopping = 0, food_drink = 0, travel = 0, fuel = 0, emi = 0, others = 0;
        for (int i = 0; i < modelData.size(); i++) {
            total += modelData.get(i).getTotal();
            shopping += modelData.get(i).getExpenses().getShopping();
            food_drink += modelData.get(i).getExpenses().getFood();
            travel += modelData.get(i).getExpenses().getTravel();
            fuel += modelData.get(i).getExpenses().getFuel();
            emi += modelData.get(i).getExpenses().getEMI();
            others += modelData.get(i).getExpenses().getOthers();
        }
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance("INR"));

        tv_total_spend.setText(format.format(total));
        tv_shopping_amount.setText(format.format(shopping));
        tv_food_drink_amount.setText(format.format(food_drink));
        tv_travel_amount.setText(format.format(travel));
        tv_fuel_amount.setText(format.format(fuel));
        tv_emi_amount.setText(format.format(emi));
        tv_others_amount.setText(format.format(others));

        DecimalFormat percentFormat = new DecimalFormat("##.##%");
        tv_shopping_percent.setText(percentFormat.format(shopping/total));
        tv_food_drink_percent.setText(percentFormat.format(food_drink/total));
        tv_travel_percent.setText(percentFormat.format(travel/total));
        tv_fuel_percent.setText(percentFormat.format(fuel/total));
        tv_emi_percent.setText(percentFormat.format(emi/total));
        tv_others_percent.setText(percentFormat.format(others/total));
    }

    @Override
    public void onClick(View v) {
        if (v == cv_shopping) {
            values2.clear();
            for (int i = 0; i < modelData.size(); i++) {
                values2.add(new Entry(i, modelData.get(i).getExpenses().getShopping()));
            }
            set2 = new LineDataSet(values2, "DataSet 2");
            set2.setColor(getResources().getColor(R.color.red));
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
            set2.setFillDrawable(drawable);
            setupChart(graph, modelData, set2);
        } else if (v == cv_food_drink) {
            values2.clear();
            for (int i = 0; i < modelData.size(); i++) {
                values2.add(new Entry(i, modelData.get(i).getExpenses().getFood()));
            }
            set2 = new LineDataSet(values2, "DataSet 2");
            set2.setColor(getResources().getColor(R.color.yellow));
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_yellow);
            set2.setFillDrawable(drawable);
            setupChart(graph, modelData, set2);
        } else if (v == cv_travel) {
            values2.clear();
            for (int i = 0; i < modelData.size(); i++) {
                values2.add(new Entry(i, modelData.get(i).getExpenses().getTravel()));
            }
            set2 = new LineDataSet(values2, "DataSet 2");
            set2.setColor(getResources().getColor(R.color.blue));
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue);
            set2.setFillDrawable(drawable);
            setupChart(graph, modelData, set2);
        } else if (v == cv_fuel) {
            values2.clear();
            for (int i = 0; i < modelData.size(); i++) {
                values2.add(new Entry(i, modelData.get(i).getExpenses().getFuel()));
            }
            set2 = new LineDataSet(values2, "DataSet 2");
            set2.setColor(getResources().getColor(R.color.green));
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_green);
            set2.setFillDrawable(drawable);
            setupChart(graph, modelData, set2);
        } else if (v == cv_emi) {
            values2.clear();
            for (int i = 0; i < modelData.size(); i++) {
                values2.add(new Entry(i, modelData.get(i).getExpenses().getEMI()));
            }
            set2 = new LineDataSet(values2, "DataSet 2");
            set2.setColor(getResources().getColor(R.color.pink));
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_pink);
            set2.setFillDrawable(drawable);
            setupChart(graph, modelData, set2);
        } else if (v == cv_others) {
            values2.clear();
            for (int i = 0; i < modelData.size(); i++) {
                values2.add(new Entry(i, modelData.get(i).getExpenses().getOthers()));
            }
            set2 = new LineDataSet(values2, "DataSet 2");
            set2.setColor(getResources().getColor(R.color.darkblue));
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_dark_blue);
            set2.setFillDrawable(drawable);
            setupChart(graph, modelData, set2);
        }
    }

    private String getJsonFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void setInitialSet(List<ModelData> modelData) {
        values2.clear();
        for (int i = 0; i < modelData.size(); i++) {
            values2.add(new Entry(i, modelData.get(i).getExpenses().getFuel()));
        }
        set2 = new LineDataSet(values2, "DataSet 2");
        set2.setColor(getResources().getColor(R.color.green));
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_green);
        set2.setFillDrawable(drawable);
        setupChart(graph, modelData, set2);
    }

    private void setupChart(LineChart chart, List<ModelData> modelData, LineDataSet set2) {

        LineDataSet set1;
        ArrayList<Entry> values1 = new ArrayList<>();
        for (int i = 0; i < modelData.size(); i++) {
            values1.add(new Entry(i, modelData.get(i).getTotal()));
        }
        set1 = new LineDataSet(values1, "DataSet 1");
        set1.setColor(getResources().getColor(android.R.color.darker_gray));
        set1.setLineWidth(1.75f);
        set1.enableDashedLine(10f, 7f, 0f);
        set1.setDrawValues(false);
        set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        set1.setDrawCircles(false);

        set2.setLineWidth(1.75f);
        set2.setDrawCircles(false);
        set2.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        set2.setDrawFilled(true);
        set2.setDrawValues(false);

        LineData data = new LineData(set1, set2);
        data.setValueTypeface(mTf);

        ((LineDataSet) data.getDataSetByIndex(0)).setCircleHoleColor(Color.WHITE);

        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setViewPortOffsets(10, 0, 10, 0);
        chart.animateX(500);
        chart.setData(data);
        chart.invalidate();
        Legend l = chart.getLegend();
        l.setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setEnabled(false);
    }
}