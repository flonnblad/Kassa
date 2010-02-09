
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fredrik
 */
public class Kassa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DataModel model = new DataModel();
        KassaView view = new KassaView(model);
        Controller controller = new Controller(model, view);

        view.setVisible(true);


    }

}
