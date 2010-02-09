
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fredrik
 */
public class KassaView extends JFrame {

    // Huvudsida
    private JPanel p0 = new JPanel();
    private JButton p0_kas = new JButton("Kassa");
    private JButton p0_lag = new JButton("Lager");
    private JButton p0_admin = new JButton("Admin");
    private JButton p0_avs = new JButton("Avsluta");
    // Admin
    private JPanel pa = new JPanel();
    private JButton pa_x = new JButton("X-kvitto");
    private JButton pa_y = new JButton("Y-kvitto");
    private JButton pa_back = new JButton("Tillbaka");
    // Kassa
    public JPanel pk = new JPanel();
    private JButton pk_back = new JButton("Tillbaka");
    private ArrayList<JButton> pk_buttons = new ArrayList<JButton>();
    private ArrayList<JPanel> pk_panels = new ArrayList<JPanel>();
    private ArrayList<ArrayList<JButton>> pk_Pbuttons =
            new ArrayList<ArrayList<JButton>>();
    private JTextField pk_kod = new JTextField(10);
    private JLabel pk_kvitto = new JLabel("");
    private JButton pk_eanb = new JButton("Lägg till");
    private JButton pk_sla = new JButton("Slå ut");
    private JButton pk_del = new JButton("Ta bort");
    private JLabel pk_summa = new JLabel("0 kr");
    private JButton pk_clear = new JButton("Noll ställ");
    private JTable pk_table = new JTable();
    // Lager
    private JPanel pl = new JPanel();
    private JButton pl_back = new JButton("Tillbaka");
    private JButton pl_ham = new JButton("Hämta");
    private JButton pl_ny = new JButton("Lägg till / Ändra");
    private JTextField pl_artnr = new JTextField(10);
    private JTextField pl_ean = new JTextField(10);
    private JTextField pl_namn = new JTextField(20);
    private JTextField pl_info = new JTextField(30);
    private JTextField pl_pris = new JTextField(10);
    private JTextField pl_lager = new JTextField(10);
    private JLabel pl_lartnr = new JLabel("Artnr");
    private JLabel pl_lean = new JLabel("EAN");
    private JLabel pl_lnamn = new JLabel("Namn");
    private JLabel pl_linfo = new JLabel("Info");
    private JLabel pl_lpris = new JLabel("Pris");
    private JLabel pl_llager = new JLabel("Lager");
    private JButton pl_cl = new JButton("Clear");
    //**********
    private DataModel data;

    //======================================================= constructor
    /** Constructor */
    KassaView(DataModel model) {
        //... Set up the logic
        data = model;
        this.setPreferredSize(null);
        makeP0(); // Huvudsida


        this.setContentPane(p0);
        this.pack();

        this.setTitle(p0.getName());

        // The window closing event should probably be passed to the
        // Controller in a real program, but this is a short example.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void moreBear(String s) {
        int j = Integer.valueOf(s).intValue() - 1;
        setContent(pk_panels.get(j));
    }

    public void setContent(JPanel p) {
        this.setContentPane(p);
        this.pack();
        this.setTitle(p.getName());

    }

    private void makeP0() {

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        p0.setLayout(gbl);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        p0.add(p0_kas, gbc);
        gbc.gridx = 1;
        p0.add(p0_lag, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        p0.add(p0_admin, gbc);
        gbc.gridx = 1;
        p0.add(p0_avs, gbc);




        p0_admin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                makePa();
                setContent(pa);
            }
        });

        p0_lag.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                makePl();
                setContent(
                        pl);


            }
        });



        p0_avs.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                data.printArtiklar();
                data.printKvitton();
                data.printXKvitton();
                System.exit(0);


            }
        });



    }

    private void makePa() {

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        pa.setLayout(gbl);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        pa.add(pa_x, gbc);
        gbc.gridx = 1;
        pa.add(pa_y, gbc);
        gbc.gridy = 1;
        pa.add(pa_back, gbc);



        pa_x.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        });

        pa_y.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                data.turnXtoY();
            }
        });



        pa_back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setContent(p0);
            }
        });
    }

    public void makePk() {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        pk.setLayout(gbl);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;

        ArrayList<Artikel> artiklar = data.getArtiklar();




        int j;


        int i;
        JPanel ptmp;
        JButton btmp;
        ArrayList<JButton> atmp;


        for (j = 0; j < data.getKlasser().size(); j++) {
            ptmp = new JPanel();
            ptmp.setLayout(gbl);
            pk_panels.add(ptmp);
            pk_Pbuttons.add(new ArrayList<JButton>());


        }
        String tmp;


        for (j = 0; j
                < artiklar.size(); j++) {
            tmp = artiklar.get(j).getArtnr().substring(0, 2);
            i = Integer.valueOf(tmp).intValue() - 1;

            btmp = new JButton(artiklar.get(j).getNamn());
            btmp.setName(artiklar.get(j).getEan());
            pk_Pbuttons.get(i).add(btmp);


        }
        System.out.println("hopp");


        for (j = 0; j
                < data.getKlasser().size(); j++) {
            gbc.gridx = j % 2;
            gbc.gridy = j / 2;
            System.out.println("tjolahopp");
            btmp = new JButton(data.getKlasser().get(j));
            btmp.setName(("" + (1 + j)));
            pk_buttons.add(btmp);
            pk.add(pk_buttons.get(j), gbc);
            System.out.println("ny knapp -namn: " + btmp.getName());
            System.out.println("ny knapp -text: " + btmp.getText());
            System.out.println("storlek: " + pk_buttons.size());
            ptmp = pk_panels.get(j);
            atmp = pk_Pbuttons.get(j);


            for (i = 0; i
                    < atmp.size(); i++) {
                gbc.gridx = i % 4;
                gbc.gridy = i / 4;

                ptmp.add(atmp.get(i), gbc);


            }

            gbc.gridy = i / 4 + 1;
            gbc.gridx = 4;
            btmp = new JButton("Tillbaka");
            btmp.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    setContent(pk);


                }
            });

            ptmp.add(btmp, gbc);


        }

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = j / 2 + 1;
        gbc.gridwidth = 2;
        pk.add(pk_kvitto, gbc);
        pk_kvitto.setText("<html></html>");

        gbc.gridx = 3;
        gbc.gridy = j;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        pk.add(pk_summa, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        pk.add(new JLabel("Summa:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = j + 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        pk.add(pk_kod, gbc);







        gbc.gridy = j + 2;
        gbc.gridx = 2;
        pk.add(pk_back, gbc);
        gbc.gridy = j + 1;
        gbc.gridwidth = 1;
        pk.add(pk_clear, gbc);
        gbc.gridx = 3;
        pk.add(pk_sla, gbc);


        gbc.gridy = j + 2;
        gbc.gridx = 0;
        pk.add(pk_eanb, gbc);

        gbc.gridx = 1;
        pk.add(pk_del, gbc);



        getRootPane().setDefaultButton(pk_eanb);
        pk_kod.requestFocusInWindow();



        pk_back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setContent(p0);


            }
        });


    }

    public void makePl() {
        String[] columnNames = {
            "artnr", "ean", "namn",
            "info", "pris", "lager"};

        Artikel a;
        Object[][] t_data = new Object[data.getArtiklar().size()][6];


        for (int i = 0; i
                < data.getArtiklar().size(); i++) {
            a = data.getArtiklar().get(i);
            t_data[i][0] = a.getArtnr();
            t_data[i][1] = a.getEan();
            t_data[i][2] = a.getNamn();
            t_data[i][3] = a.getInfo();
            t_data[i][4] = a.getPris();
            t_data[i][5] = a.getLager();


        }

        JTable table = new JTable(t_data, columnNames);
        table.setEnabled(false);




        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        pl.setLayout(gbl);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        pl.add(new JScrollPane(table), gbc);
        gbc.gridwidth = 1;
        pl_artnr.setEditable(true);
        pl_ean.setEditable(true);
        pl_namn.setEditable(true);
        pl_info.setEditable(true);
        pl_pris.setEditable(true);
        pl_lager.setEditable(true);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        pl.add(pl_lartnr, gbc);
        gbc.gridx = 1;
        pl.add(pl_lean, gbc);
        gbc.gridx = 2;
        pl.add(pl_lnamn, gbc);
        gbc.gridx = 3;
        pl.add(pl_linfo, gbc);
        gbc.gridx = 4;
        pl.add(pl_lpris, gbc);
        gbc.gridx = 5;
        pl.add(pl_llager, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pl.add(pl_artnr, gbc);
        gbc.gridx = 1;
        pl.add(pl_ean, gbc);
        gbc.gridx = 2;
        pl.add(pl_namn, gbc);
        gbc.gridx = 3;
        pl.add(pl_info, gbc);
        gbc.gridx = 4;
        pl.add(pl_pris, gbc);
        gbc.gridx = 5;
        pl.add(pl_lager, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pl.add(pl_ham, gbc);
        gbc.gridx = 1;
        pl.add(pl_ny, gbc);
        gbc.gridx = 2;
        pl.add(pl_cl, gbc);
        gbc.gridx = 5;
        pl.add(pl_back, gbc);


        pl_ham.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Artikel a = null;


                if (data.getArtikel(pl_artnr.getText()) != null) {
                    a = data.getArtikel(pl_artnr.getText());


                } else if (data.getArtikel(pl_ean.getText()) != null) {
                    a = data.getArtikel(pl_ean.getText());


                }
                if (a != null) {
                    pl_artnr.setText(a.getArtnr());
                    pl_ean.setText(a.getEan());
                    pl_namn.setText(a.getNamn());
                    pl_info.setText(a.getInfo());
                    pl_pris.setText("" + a.getPris());
                    pl_lager.setText("" + a.getLager());


                }
            }
        });

        pl_cl.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pl_artnr.setText("");
                pl_ean.setText("");
                pl_namn.setText("");
                pl_info.setText("");
                pl_pris.setText("");
                pl_lager.setText("");



            }
        });

        pl_back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setContent(p0);


            }
        });


    }

    public void resetPk_kod() {
        pk_kod.setText("");
        pk_kod.requestFocusInWindow();
    }

    public String getPkKvitto() {
        return pk_kvitto.getText();

    }

    public String[] getPlInfo() {
        String[] tmp = {pl_artnr.getText(),
            pl_ean.getText(),
            pl_namn.getText(),
            pl_info.getText(),
            pl_pris.getText(),
            pl_lager.getText(),};


        return tmp;



    }

    public String getPkKod() {
        return pk_kod.getText();


    }

    public String getPkSumma() {
        return pk_summa.getText();


    }

    public void initPk() {
        pk_kvitto.setText("<html></html>");
        pk_summa.setText("0 kr");


    }

    public void updPkLabel(HashMap<String, Integer> lista) {

        Iterator<String> tmp2 = lista.keySet().iterator();
        String source = "<html>";
        Artikel a;


        int summa = 0;


        while (tmp2.hasNext()) {
            a = data.getArtikel(tmp2.next());
            source += "art: " + a.getArtnr();
            source += "\tnamn: ";
            source += a.getNamn();
            source += "\tantal: ";
            source += lista.get(a.getArtnr());
            source += "\tpris: ";
            source += a.getPris() + "<br>";
            summa += a.getPris() * lista.get(a.getArtnr());


        }
        source += "</html>";
        System.out.println(source);
        pk_kvitto.setText(source);
        pk_summa.setText("" + summa);



    }

    void addPkSlaListener(ActionListener l) {
        pk_sla.addActionListener(l);


    }

    void addPkDelListener(ActionListener l) {
        pk_del.addActionListener(l);


    }

    void addPkEANbListener(ActionListener l) {
        pk_eanb.addActionListener(l);


    }

    void addPkClearListener(ActionListener l) {
        pk_clear.addActionListener(l);


    }

    void addPkListener(ActionListener l) {
        System.out.println("addPkKlass");
        System.out.println("storlek: " + pk_buttons.size());


        for (int i = 0; i
                < pk_buttons.size(); i++) {
            pk_buttons.get(i).addActionListener(l);
            System.out.println(pk_buttons.get(i).getText());


        }
        ArrayList<JButton> tmp;


        for (int i = 0; i
                < pk_Pbuttons.size(); i++) {
            tmp = pk_Pbuttons.get(i);


            for (int j = 0; j
                    < tmp.size(); j++) {
                tmp.get(j).addActionListener(l);

            }
        }
    }

    void addPkKlassListener(ActionListener l) {
        System.out.println("addPkKlass");
        System.out.println("storlek: " + pk_buttons.size());


        for (int i = 0; i
                < pk_buttons.size(); i++) {
            pk_buttons.get(i).addActionListener(l);
            System.out.println(pk_buttons.get(i).getText());


        }
    }

    void addP0KasListener(ActionListener l) {
        p0_kas.addActionListener(l);
    }

    void addPkButtonsListener(ActionListener l) {
        ArrayList<JButton> tmp;


        for (int i = 0; i
                < pk_Pbuttons.size(); i++) {
            tmp = pk_Pbuttons.get(i);


            for (int j = 0; j
                    < tmp.size(); j++) {
                tmp.get(j).addActionListener(l);

            }
        }
    }
}
