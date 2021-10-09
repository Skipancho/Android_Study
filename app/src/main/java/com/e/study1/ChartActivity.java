package com.e.study1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {
    private PieChart pieChart;
    private BarChart barChart;
    private ArrayList<String> labels = new ArrayList<>();
    private ArrayList<BarEntry> entries = new ArrayList<>();
    private ArrayList<Entry> pie_entries= new ArrayList<>();
    private ArrayList<String> pie_labels = new ArrayList<>();
    private List<Fruit> fruits = new ArrayList<>();
    private int[] colorArray = new int[] {0xffef9a9a,0xffffe082,0xffc5e1a5,0xff80cbc4,0xffb39ddb,0xff9fa8da,0xffffcc80,0xffce93d8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        barChart = findViewById(R.id.bar_chart);
        pieChart = findViewById(R.id.pie_chart);

        fruits.add(new Fruit("사과",12000));
        fruits.add(new Fruit("배",18000));
        fruits.add(new Fruit("딸기",15000));
        fruits.add(new Fruit("수박",20000));
        fruits.add(new Fruit("참외",16000));
        fruits.add(new Fruit("파인애플",10000));
        //fruits.add(new Fruit("멜론",15000));
        //fruits.add(new Fruit("포도",20000));

        for(int i = 0; i < fruits.size(); i++){
            labels.add(fruits.get(i).getName());
            entries.add(new BarEntry(fruits.get(i).getPrice(),i));
        }
        BarDataSet barDataSet = new BarDataSet(entries,"과일 가격");
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);

        BarData barData = new BarData(labels,barDataSet);
        barData.setValueTextSize(20);
        barChart.setData(barData);
        barChart.setDescription("");
        barChart.animateXY(1000,2000);
        barChart.invalidate();

        int sum = fruits.stream().mapToInt(Fruit :: getPrice).sum();
        int i = 0;
        for(Fruit f : fruits){
            //double rate = Math.round((f.getPrice()/sum)*10000)/100.0;
            double rate = ((int)((f.getPrice()/(double)sum)*10000))/100.0;
            pie_entries.add(new Entry((float)rate,i++));
            pie_labels.add(f.getName());
        }
        PieDataSet pieDataSet = new PieDataSet(pie_entries,"");
        pieDataSet.setColors(colorArray);
        PieData pieData = new PieData(pie_labels,pieDataSet);
        pieData.setValueTextSize(20);
        pieChart.setUsePercentValues(true);
        pieChart.setData(pieData);
        pieChart.setCenterText("구매 금액 비율");
        pieChart.setCenterTextSize(20);
        pieChart.setHoleRadius(30);
        //pieChart.animateXY(1000,1000);
        pieChart.setDescription("");
        pieChart.invalidate();



    }

    class Fruit{
        private String name;
        private int price;

        public Fruit(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }
    }
}