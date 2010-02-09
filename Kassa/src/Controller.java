
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fredrik
 */
public class Controller {
//... The Controller needs to interact with both the Model and View.

    private DataModel data;
    private KassaView view;
    private ArrayList<Artikel> artikelLista = new ArrayList<Artikel>();
    private HashMap<String, Integer> artLista = new HashMap<String, Integer>();
    private ArrayList<Integer> antal = new ArrayList<Integer>();

    //========================================================== constructor
    /** Constructor */
    Controller(DataModel model, KassaView view) {
        this.data = model;
        this.view = view;

        //... Add listeners to the view.

        view.addPkSlaListener(new PkSlaListener());
        view.addPkDelListener(new PkDelListener());
        view.addPkEANbListener(new PkEANbListener());
        view.addPkClearListener(new PkClearListener());
        view.addP0KasListener(new P0KasListener());
    }

    // Lägg till / Ändra artikel
    class PlSubmitListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String[] tmp = view.getPlInfo();
            if (data.getArtikel(tmp[0]) != null) {
                data.updArtikel(tmp[0], tmp[1], tmp[2], tmp[3],
                        Integer.valueOf(tmp[4]).intValue(),
                        Integer.valueOf(tmp[5]).intValue());
            } else if (data.getArtikel(tmp[1]) != null) {
                data.updArtikel(tmp[0], tmp[1], tmp[2], tmp[3],
                        Integer.valueOf(tmp[4]).intValue(),
                        Integer.valueOf(tmp[5]).intValue());
            } else {
                data.addArtikel(tmp[0], tmp[1], tmp[2], tmp[3],
                        Integer.valueOf(tmp[4]).intValue(),
                        Integer.valueOf(tmp[5]).intValue());
            }
        }
    }

    // Slå ut kassan
    class PkSlaListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            data.addKvitto(artLista);
            view.initPk();
            artLista.clear();
            view.resetPk_kod();
        }
    }

    // Ta bort något, retur
    class PkDelListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            updArtLista(data.getArtikel(view.getPkKod()), -1);

        }
    }

    // Lägg till artikel
    class PkEANbListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            updArtLista(data.getArtikel(view.getPkKod()), 1);
            view.resetPk_kod();
        }
    }

    class PkKlassListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            view.moreBear(((JButton) e.getSource()).getName());
        }
    }

    private void updArtLista(Artikel a, int i) {
        if (artLista.get(a.getArtnr()) != null) {
            if (artLista.get(a.getArtnr()) + i == 0) {
                artLista.remove(a.getArtnr());
            } else {
                artLista.put(a.getArtnr(), artLista.get(a.getArtnr()) + i);
            }
        } else {
            artLista.put(a.getArtnr(), i);
        }

        view.updPkLabel(artLista);
    }

    // Lägg till artikel
    class PkClearListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            artLista.clear();
            view.initPk();
        }
    }

    class P0KasListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            view.makePk();
            view.addPkKlassListener(new PkKlassListener());
            view.addPkButtonsListener(new PkButtonsListener());
            view.setContent(view.pk);
        }
    }

    // Lägg till vara
    class PkButtonsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            updArtLista(data.getArtikel(
                    ((JButton) e.getSource()).getName()), 1);
            view.setContent(view.pk);
        }
    }
}
