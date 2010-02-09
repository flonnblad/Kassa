
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fredrik
 */
public class Kvitto {

    // Final strings
    private final String namn = "";
    private final String adress = "";
    private final String orgnr = "";
    private final String hemsida = "";
    // Variables
    private int nr;
    private String datum_tid; //YYYYMMDDttmm
    private HashMap<String, Integer> art = new HashMap<String, Integer>();

    public void setArt(HashMap<String, Integer> art) {
        this.art = art;
    }
    private int summa;

    public Kvitto(int nr) {
        datum_tid = "";
        int tmp;
        Calendar cal = new GregorianCalendar();
        datum_tid += cal.get(Calendar.YEAR);
        tmp = cal.get(Calendar.MONTH);
        if (tmp < 10) {
            datum_tid += "0"+tmp;
        }
        else {
            datum_tid += tmp;
        }
        tmp = cal.get(Calendar.DATE);
        if (tmp < 10) {
            datum_tid += "0"+tmp;
        }
        else {
            datum_tid += tmp;
        }
        tmp = cal.get(Calendar.HOUR);
        if (cal.get(Calendar.AM) == 1) {
            tmp += 12;
        }
        if (tmp < 10) {
            datum_tid += "0"+tmp;
        }
        else {
            datum_tid += tmp;
        }

        tmp = cal.get(Calendar.MINUTE);
        if (tmp < 10) {
            datum_tid += "0"+tmp;
        }
        else {
            datum_tid += tmp;
        }
        this.nr = nr;
    }

    public Kvitto(int i, String datum) {
        nr = i;
        datum_tid = datum;
    }

    public void updArt(String artnr, Integer antal, int pris) {
        if (art.get(artnr) != null) {
            antal += art.get(artnr);
            
        }
        if (((int) antal) == 0) {
                art.remove(artnr);
            }
        else {
            art.put(artnr, antal);
        }
        summa += antal * pris;
    }

    public String getAdress() {
        return adress;
    }

    public HashMap<String, Integer> getArt() {
        return art;
    }
    
    public int getNr() {
        return nr;
    }

    public String getDatum_tid() {
        return datum_tid;
    }

    public String getHemsida() {
        return hemsida;
    }

    public String getNamn() {
        return namn;
    }

    public String getOrgnr() {
        return orgnr;
    }

    public int getSumma() {
        return summa;
    }
}

