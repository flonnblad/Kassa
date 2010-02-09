/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kassa_ver2.dbObjects;

/**
 *
 * @author Fredrik
 */
public class Article {
private String artnr;
    private String ean;
    private String name;
    private String info;
    private int price;

    public Article (String[] str) {
        this.artnr = str[0];
        this.ean = str[1];
        this.name = str[2];
        this.info = str[3];
        this.price = Integer.valueOf(str[4]).intValue();
    }

    public String getArtnr() {
        return artnr;
    }

    public String getEan() {
        return ean;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
