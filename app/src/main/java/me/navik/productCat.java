package me.navik;

import java.util.ArrayList;

/**
 * Created by govin on 17-02-2018.
 */

public class productCat {
    private String product;
    private String stalls;
    private String discription;

    public String getDiscription() {
        return discription;
    }

    public String getProduct() {
        return product;

    }

    public  String getStalls() {
        return stalls;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setStalls(String stalls) {
        this.stalls = stalls;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public productCat(String product, String stalls, String discription) {
        this.product = product;
        this.stalls = stalls;
        this.discription = discription;
    }
}
