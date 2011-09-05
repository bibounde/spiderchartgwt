package org.bibounde.spiderchartgwt.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SpiderChartApplication implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        SpiderChartWidget chart = new SpiderChartWidget(500, 300);
        
        List<Serie> series = new ArrayList<Serie>();
        series.add(new Serie("Julien", new double[]{4d, 2d, 3d, 5d, 0d, 4d, 5d}, new Color(255, 0, 0)));
        series.add(new Serie("Pierre", new double[]{2d, 1d, 1d, 4d, 5d, 2d, 3.5d}, new Color(0, 0, 255)));
        //series.add(new Serie("Kevin", new double[]{5d, 3d, 4d, 1d, 1d, 5d}, new Color(0, 255, 0)));
        
        chart.setSeries(series);
        chart.setColumns(new String[]{"Java", "C++", "C#", "Python", "PHP", "Protovis", "GWT"});
        chart.setGridStep(1d);
        
        chart.requestPaint();
        
        RootPanel.get().add(chart);
    }
}
