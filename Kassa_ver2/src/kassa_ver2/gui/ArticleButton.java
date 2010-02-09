/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kassa_ver2.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import kassa_ver2.dbObjects.Article;

/**
 *
 * @author Fredrik
 */
public class ArticleButton extends JButton{
    private ArticleInterface callback;

    private Article a;

    private ActionListener myAction = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            callback.actionBuy(a);
        }
    };

    public ArticleButton(Article a, ArticleInterface callback){
        this.a = a;
        this.callback = callback;
        this.setText(a.getName());
        this.addActionListener(myAction);
    }

}
