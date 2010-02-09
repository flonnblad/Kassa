/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fredrik
 */
public class Artikel {
    private String artnr;
    private String ean;
    private String namn;
    private String info;
    private int pris;
    private int lager;

    public Artikel (String artnr, String ean, String namn, String info,
            int pris, int lager) {
        this.artnr = artnr;
        this.ean = ean;
        this.namn = namn;
        this.info = info;
        this.pris = pris;
        this.lager = lager;
    }

    public int getPris() {
        return pris;
    }

    public String getArtnr() {
        return artnr;
    }

    public String getEan() {
        return ean;
    }

    public int getLager() {
        return lager;
    }

    public String getNamn() {
        return namn;
    }

        public String getInfo() {
        return info;
    }

    public void updLager(int lager) {
        this.lager += lager;
    }
}
