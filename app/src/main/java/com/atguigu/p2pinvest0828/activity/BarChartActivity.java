package com.atguigu.p2pinvest0828.activity;

import androidx.appcompat.app.AppCompatActivity;

// by HLP
public class BarChartActivity extends AppCompatActivity {

//    ImageView ivTitleBack;
//    TextView tvTitle;
//    ImageView ivTitleSetting;
//    BarChart barChart;
//    private Typeface mTf;//声明字体库
//    @Override
//    protected void initData() {
//        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
//        // apply styling
//        barChart.setDescription("三星note7爆炸门事件后，三星品牌度");
//        barChart.setDrawGridBackground(false);
//        //是否绘制柱状图的背景
//        barChart.setDrawBarShadow(false);
//
//        //获取x轴对象
//        XAxis xAxis = barChart.getXAxis();
//        //设置x轴的显示位置
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        //设置x轴的字体
//        xAxis.setTypeface(mTf);
//        //是否绘制x轴的网格线
//        xAxis.setDrawGridLines(false);
//        //是否绘制x轴的轴线
//        xAxis.setDrawAxisLine(true);
//
//        //获取左边y轴对象
//        YAxis leftAxis = barChart.getAxisLeft();
//        //设置y轴的字体
//        leftAxis.setTypeface(mTf);
//        //参数1：左边y轴提供的区间的个数。 参数2：是否均匀分布这几个区间。 false：均匀。 true：不均匀
//        leftAxis.setLabelCount(5, false);
//        //设置最大值距离顶部的距离
//        leftAxis.setSpaceTop(0);
//
//        YAxis rightAxis = barChart.getAxisRight();
//        rightAxis.setEnabled(false);
//
//        //提供柱状图的数据
//        BarData barData = generateDataBar();
//        barData.setValueTypeface(mTf);
//
//        // set data
//        barChart.setData(barData);
//
//        // 设置y轴方向的动画
//        barChart.animateY(700);
//    }
//
//    @Override
//    protected void initTitle() {
//        ivTitleBack.setVisibility(View.VISIBLE);
//        tvTitle.setText("柱状图demo");
//        ivTitleSetting.setVisibility(View.INVISIBLE);
//    }
//
//    @OnClick(R.id.iv_title_back)
//    public void back(View view){
//        removeCurrentActivity();
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_bar_chart;
//    }
//
//    /**
//     * generates a random ChartData object with just one DataSet
//     *
//     * @return
//     */
//    private BarData generateDataBar() {
//
//        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
//
//        for (int i = 0; i < 12; i++) {
//            entries.add(new BarEntry((int) (Math.random() * 70) + 30, i));
//        }
//
//        BarDataSet d = new BarDataSet(entries, "New DataSet 1" );
//        //设置相邻的柱状图之间的距离
//        d.setBarSpacePercent(40f);
//        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
//        //设置高亮的透明度
//        d.setHighLightAlpha(255);
//
//        BarData cd = new BarData(getMonths(), d);
//        return cd;
//    }
//
//    private ArrayList<String> getMonths() {
//
//        ArrayList<String> m = new ArrayList<String>();
//        m.add("Jan");
//        m.add("Feb");
//        m.add("Mar");
//        m.add("Apr");
//        m.add("May");
//        m.add("Jun");
//        m.add("Jul");
//        m.add("Aug");
//        m.add("Sep");
//        m.add("Okt");
//        m.add("Nov");
//        m.add("Dec");
//
//        return m;
//    }
}
