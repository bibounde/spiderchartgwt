package org.bibounde.spiderchartgwt.client;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class SpiderChartWidget extends Composite {

    private List<Serie> series;
    private String[] columns;
    private double gridStep = 1d;
    private int legendAreaWidth = 150;
    private int width, height;
    private String id;
    
    private StringBuilder jsData, jsSerieNames, jsColumns, jsColors, jsFillColors;
    private int jsValueCount;
    private double jsRuleWidth, jsMaxValue, jsCenterBottom, jsCenterLeft;
    
    
    public SpiderChartWidget(int width, int height) {
        this.width = width;
        this.height = height;
        
        this.id = "spiderchart_" + this.hashCode();
        
        SimplePanel content = new SimplePanel();
        content.getElement().setId(id);
        
        this.initWidget(content);
    }
    
    public void requestPaint() {
        this.updateSerieJSVars();
        this.updateColumnJSVar();
        this.updateOptionJSVars();
        this.render();
    }
    
    public void setSeries(List<Serie> series) {
        this.series = series;
    }
    
    public void setColumns(String[] columns) {
        this.columns = columns;
    }
    
    public void setLegendAreaWidth(int legendAreaWidth) {
        this.legendAreaWidth = legendAreaWidth;
    }
    
    public void setGridStep(double gridStep) {
        this.gridStep = gridStep;
    }
    
    public String getId() {
        return this.id;
    }
    
    private native void render() /*-{
        function theta(angle) {
            return  Math.PI * ((angle + 90)/180);
        }
    
        function createRule(angle, labelText) {
            var axis = vis.add($wnd.pv.Line).data($wnd.pv.range(2)); 
            axis.left(function() {
                return centerLeft + (this.index * Math.cos(theta(angle)) * ruleWidth);
            });
            axis.bottom(function() {
                return centerBottom + (this.index * Math.sin(theta(angle)) * ruleWidth);
            });
            axis.lineWidth(1);
            axis.strokeStyle(axisColor);
            
            var label = axis.anchor("center").add($wnd.pv.Label);
            label.visible(function() {
                return this.index > 0;
            });
            label.text(labelText);
            label.textMargin(2);
            
            if (angle > 0 && angle < 180) {
                label.textAlign("right");
            } else if (angle > 180) {
                label.textAlign("left");
            } else {
                label.textAlign("center");
            }
            
            if ((angle >= 0 && angle <= 45) || (angle >= 315)) {
                label.textBaseline("bottom");
            } else if ((angle > 45 && angle <= 135) || (angle >= 225 && angle < 315)) {
                label.textBaseline("middle");
            } else {
                label.textBaseline("top");
            }
        }
    
        function createGrid(count, step) {
            var axis = vis.add($wnd.pv.Line).data($wnd.pv.range(count)); 
            axis.left(function() {
                return centerLeft + Math.cos(theta(this.index * angle)) * step;
            });
            axis.bottom(function() {
                return centerBottom + Math.sin(theta(this.index * angle)) * step;
            });
            axis.lineWidth(1);
            axis.strokeStyle(axisColor);
        
        }
    
        function createDataLine(data, color, fillColor) {
            var line = vis.add($wnd.pv.Line).data(data);
            line.left( function(d) {
                
                return centerLeft + Math.cos(theta(this.index * angle)) * (step * d);
            });
            line.bottom(function(d) {
                return centerBottom + Math.sin(theta(this.index * angle)) * (step * d);
            });
            line.strokeStyle(color);
            line.fillStyle(fillColor);
            line.lineWidth(2);
        }
    
        function createTicks(range, step) {
            var rule = vis.add($wnd.pv.Rule).data(range);
            rule.width(5);
            rule.left(centerLeft - 2);
            rule.bottom(function(d) {
                return centerBottom + (step * d);
            });
            rule.strokeStyle(axisColor);
            var label = rule.anchor("center").add($wnd.pv.Label);
            label.textAlign("right");
            label.textMargin(5);
            label.textStyle(axisColor);
        }
    
        function createLegend() {
            var legendTop = (chartHeight - (serieNames.length * 18)) / 2;
            
            var legend = vis.add($wnd.pv.Bar).data(serieNames);
            legend.top(function(){
                return legendTop + this.index * 18;
            });
            legend.width(11).height(11).left(chartWidth - legendAreaWidth);
            legend.fillStyle(function() {
                return colors[this.index];
            });
            legend.anchor("left").add($wnd.pv.Label).textBaseline("middle").textMargin(16);
        }
    
        var data = eval(this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSData()());
        var colors = eval(this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSColors()());
        var fillColors = eval(this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSFillColors()());
        var columns = eval(this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSColumns()());
        var serieNames = eval(this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSSerieNames()());
        var ruleWidth = this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSRuleWidth()();
        var centerBottom = this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSCenterBottom()();
        var centerLeft = this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSCenterLeft()();
        var step = (ruleWidth - 10) / (this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSGridStep()() * this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSMaxValue()());
        var axisColor = $wnd.pv.color("#969696");
        var legendAreaWidth = this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSLegendAreaWidth()();
        var chartWidth = this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSWidth()();
        var chartHeight = this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSHeight()();

        var vis = new $wnd.pv.Panel().canvas(this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getId()());
        vis.width(chartWidth);
        vis.height(chartHeight);
 
        //Rule creations
        var angle = 360 / columns.length;
        for (i=0; i< columns.length; i++) {
            createRule(i * angle, columns[i]);
        }

        //Create grid
        for (i=1; i<= this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSMaxValue()(); i++) {
            createGrid(columns.length + 1, i * step);
        }

        //Create ticks
        var rangeStop = this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSMaxValue()() + this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSGridStep()();
        var rangeStep = this.@org.bibounde.spiderchartgwt.client.SpiderChartWidget::getJSGridStep()();
        createTicks($wnd.pv.range(0, rangeStop, rangeStep), step);

        //Display data
        for (i=0; i<data.length; i++) {
            createDataLine(data[i], colors[i], fillColors[i]);
        }

        //Add Legend
        createLegend();

        vis.render();
    }-*/;
    
    private void updateSerieJSVars() {
        if (this.series == null) {
            return;
        }
        this.jsData = new StringBuilder("[");
        this.jsSerieNames = new StringBuilder("[");
        this.jsColors = new StringBuilder("[");
        this.jsFillColors = new StringBuilder("[");
        this.jsMaxValue = 0d;
        this.jsValueCount = 0;
        
        for (int i = 0; i < this.series.size(); i++) {
            Serie s = this.series.get(i);
            if (i > 0) {
                jsData.append(",");
                jsSerieNames.append(",");
                jsColors.append(",");
                jsFillColors.append(",");
            }
            jsData.append("[");
            for (int j = 0; j < s.getValues().length; j++) {
                if (j > 0) {
                    jsData.append(",");
                }
                jsData.append(s.getValues()[j]);
                jsMaxValue = Math.max(jsMaxValue, s.getValues()[j]);
            }
            //Need to add first value for circle completion
            if (s.getValues().length > 0) {
                jsData.append(",").append(s.getValues()[0]);
            }
            jsData.append("]");
            jsSerieNames.append("'").append(s.getName()).append("'");
            jsValueCount = Math.max(jsValueCount, s.getValues().length);
            jsColors.append("'").append(s.getColor().getProtovisColor()).append("'");
            jsFillColors.append("'").append(s.getColor().getProtovisColor(0.2)).append("'");
        }
        
        jsData.append("]");
        jsSerieNames.append("]");
        this.jsColors.append("]");
        this.jsFillColors.append("]");
    }
    
    private void updateColumnJSVar() {
        if (this.columns == null) {
            return;
        }
        this.jsColumns = new StringBuilder("[");
        for (int i = 0; i < this.columns.length; i++) {
            if (i > 0) {
                this.jsColumns.append(",");
            }
            this.jsColumns.append("'").append(this.columns[i]).append("'");
        }
        jsColumns.append("]");
    }
    
    private void updateOptionJSVars() {
        //Calculate rule width
        //TODO: legend area
        int availableWidth = this.width - this.legendAreaWidth ;
        int available = Math.min(this.height, availableWidth);
        //Margin to 20
        this.jsRuleWidth = (Integer.valueOf(available).doubleValue() / 2) - (20 * 2);
        this.jsCenterBottom = this.height / 2;
        this.jsCenterLeft = availableWidth / 2;
    }
    
    public String getJSData() {
        return this.jsData == null ? "[]" : this.jsData.toString();
    }
    
    public String getJSSerieNames() {
        return this.jsSerieNames == null ? "[]" : this.jsSerieNames.toString();
    }
    
    public String getJSColumns() {
        return this.jsColumns == null ? "[]" : this.jsColumns.toString();
    }
    
    public double getJSRuleWidth() {
        return this.jsRuleWidth;
    }
    
    public double getJSCenterBottom() {
        return this.jsCenterBottom;
    }
    
    public double getJSCenterLeft() {
        return this.jsCenterLeft;
    }
    
    public double getJSValueCount() {
        return this.jsValueCount;
    }
    
    public double getJSGridStep() {
        return this.gridStep;
    }
    
    public double getJSMaxValue() {
        return this.jsMaxValue;
    }
    
    public String getJSColors() {
        return this.jsColors == null ? "[]" : this.jsColors.toString();
    }
    
    public String getJSFillColors() {
        return this.jsFillColors == null ? "[]" : this.jsFillColors.toString();
    }
    
    public int getJSLegendAreaWidth() {
        return this.legendAreaWidth;
    }
    
    public int getJSWidth() {
        return this.width;
    }
    
    public int getJSHeight() {
        return this.height;
    }
}
