package com.ex.pojos;

import org.bson.types.ObjectId;

public class Paint {
    private String colorName;
    private String color;
    private String finish;
    private float price;

    private ObjectId id;

    public Paint() {
    }

    public Paint(String colorName, String color, String finish, float price) {
        this.colorName = colorName;
        this.color = color;
        this.finish = finish;
        this.price = price;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Paint{" +
                "colorName='" + colorName + '\'' +
                ", color='" + color + '\'' +
                ", finish='" + finish + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
