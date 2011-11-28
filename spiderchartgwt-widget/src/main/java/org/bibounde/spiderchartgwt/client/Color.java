package org.bibounde.spiderchartgwt.client;

public class Color {

    private int red, green, blue;

    public Color(int red, int green, int blue) {
        this.checkValue("red", red);
        this.checkValue("green", green);
        this.checkValue("blue", blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    private void checkValue(String colorName, int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException(colorName + " must be an in [0, 255]");
        }
    }
    
    public String getProtovisColor() {
        return this.getProtovisColor(1d);
    }
    
    public String getProtovisColor(double opacity) {
        if (opacity < 0 || opacity > 1) {
            throw new IllegalArgumentException("Opacity mus be a double in [0, 1]");
        }
        StringBuilder pColor = new StringBuilder("rgba(");
        pColor.append(this.red).append(",").append(this.green).append(",").append(this.blue).append(",").append(opacity);
        pColor.append(")");
        
        return pColor.toString();
    }
    
}
