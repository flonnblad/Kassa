/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kassa_ver2;

import java.util.ArrayList;
import java.util.HashMap;
import kassa_ver2.dbObjects.Article;
import kassa_ver2.dbObjects.Receipt;

/**
 *
 * @author Fredrik
 */
public class DataHandler implements DataInterface {

    private ArrayList<String> classes = new ArrayList<String>();
    private ArrayList<Article> articles = new ArrayList<Article>();
    private ArrayList<Receipt> receipts = new ArrayList<Receipt>();
    private ArrayList<Receipt> yreceipts = new ArrayList<Receipt>();

    private HashMap<String, Integer> artnr = new HashMap<String, Integer>();
    private HashMap<String, Integer> ean = new HashMap<String, Integer>();
    private HashMap<String, Integer> stock = new HashMap<String, Integer>();

    private Receipt x;
    
    FileHandler fh;

    public DataHandler() {
        fh = new FileHandler(this);
    }

    public void addArticle(Article a) {
        articles.add(a);
        artnr.put(a.getArtnr(), articles.size() - 1);
        ean.put(a.getArtnr(), articles.size() - 1);
    }

    public void addClass(String[] str) {
        classes.add(Integer.valueOf(str[0]).intValue() -1, str[1]);
    }

    public void addReceipt(Receipt r) {
        receipts.add(r);
    }

    public Article getArticle(String code) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public ArrayList<String> getClasses() {
        return classes;
    }

    public void turnXtoY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updArticle(String[] str) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getStock(String code) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updStock(String[] str) {
        Integer j = stock.get(str[0]);
        if (j == null) {
            j = 0;
        }
        stock.put(str[0], j + Integer.valueOf(str[2]));
    }

    public void addToY(Receipt r) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setX(Receipt r) {
        x = r;
    }
}
