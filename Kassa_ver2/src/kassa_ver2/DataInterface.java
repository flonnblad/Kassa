/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kassa_ver2;

import java.util.ArrayList;
import kassa_ver2.dbObjects.Article;
import kassa_ver2.dbObjects.Receipt;

/**
 *
 * @author Fredrik
 */
public interface DataInterface {
    public void addClass(String[] str);
    public ArrayList<String> getClasses();
    public void addArticle(Article a);
    public Article getArticle(String code);
    public ArrayList<Article> getArticles();
    public void turnXtoY();
    public void addReceipt(Receipt r);
    public void updArticle(String[] str);
    public int getStock(String code);
    public void updStock(String[] str);
    public void setX(Receipt r);
    public void addToY(Receipt r);
}
