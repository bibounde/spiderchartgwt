package org.bibounde.spiderchartgwt.client;


public class Serie {

    private String name;
    private double[] values;
    private Color color;
    
    public Serie(String name, double[] values, Color color) {
        this.name = name;
        this.values = values;
        this.color = color;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the values
     */
    public double[] getValues() {
        return values;
    }
    /**
     * @param values the values to set
     */
    public void setValues(double[] values) {
        this.values = values;
    }
    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
