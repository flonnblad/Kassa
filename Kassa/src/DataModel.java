
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fredrik
 */

public class DataModel {

    private final String fil_artiklar = "artiklar.txt";
    private ArrayList<Kvitto> kvitton = new ArrayList<Kvitto>();
    private Kvitto xkvitto;
    private ArrayList<Kvitto> ykvitton = new ArrayList<Kvitto>();
    private ArrayList<Artikel> artiklar = new ArrayList<Artikel>();
    private HashMap<String, Integer> artnr = new HashMap<String, Integer>();
    private HashMap<String, Integer> ean = new HashMap<String, Integer>();
    private ArrayList<String> klasser = new ArrayList<String>();

    public DataModel() {
        readArtiklar();
        readKvitton();
        readXKvitto();
    }

    private void readArtiklar() {

        String line = "";
        String[] parts = {};
        try {
            FileReader fr = new FileReader(fil_artiklar);
            BufferedReader br = new BufferedReader(fr);

            int i;

            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                parts = line.split("; ");

                if (parts.length == 1) {
//                    System.out.println(line);
                    klasser.add(line.substring(0, line.length() - 1));
                } else {

                    line = parts[5].substring(0, (parts[5].length()) - 1);
                    this.addArtikel(parts[0], parts[1], parts[2], parts[3],
                            Integer.valueOf(parts[4]).intValue(),
                            Integer.valueOf(line).intValue());
//                System.out.println(
//                        parts[5].substring(0, (parts[5].length()) - 1));
                }

            }

            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());
        } catch (IOException e) {
            System.out.println("jahaja: " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(line);
            for (int i = 0; i < parts.length - 1; i++) {
                System.out.println(parts[i]);
            }
            System.out.println(e);
            System.exit(0);

        }
    }

    public void printArtiklar() {
        try {
            String source = "";
            Artikel tmp1;



            int j = (-1), t = 0;
            for (int i = 0; i
                    < artiklar.size(); i++) {
                if (i > 0) {
                    source += ";\n";


                }
                tmp1 = artiklar.get(i);

                t = Integer.valueOf(tmp1.getArtnr().substring(0, 2)).intValue();
                if (j < t - 1) {
                    j = t - 1;
//                    System.out.println("index: " + j);
                    source += klasser.get(j);
                    source += ";\n";
                }

                source += tmp1.getArtnr();
                source += "; ";
                source += tmp1.getEan();
                source += "; ";
                source += tmp1.getNamn();
                source += "; ";
                source += tmp1.getInfo();
                source += "; ";
                source += tmp1.getPris();
                source += "; ";
                source += tmp1.getLager();


            }
            source += ";";
//            System.out.println(source);



            char buffer[] = new char[source.length()];
            source.getChars(0, source.length(), buffer, 0);

            FileWriter fw = new FileWriter(fil_artiklar);
            fw.write(buffer);
            fw.close();



        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());


        } catch (IOException e) {
            System.out.println("jahaja: " + e);


        }
    }

    private void readKvitton() {
        try {
            Kvitto tmp;
            FileReader fr = new FileReader("kvitton.txt");
            BufferedReader br = new BufferedReader(fr);
            String line, art;
            String[] parts;


            int j;


            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                parts = line.split("; ");
//                System.out.println(parts[0]);
//                System.out.println(parts[1]);
                tmp = new Kvitto(Integer.valueOf(parts[0]).intValue(), parts[1]);
                parts[2] = parts[2].substring(0, parts[2].length() - 1);
                parts = parts[2].split(", ");

                for (int i = 0; i
                        < parts.length; i++) {
//                    System.out.println((parts[i].split(" "))[0]);
//                    System.out.println((parts[i].split(" "))[1]);
                    art = (parts[i].split(" "))[0];
                    j = Integer.valueOf((parts[i].split(" "))[1]).intValue();
                    tmp.updArt(art, j, getArtikel(art).getPris());



                }
                kvitton.add(tmp);



            }
            fr.close();


        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());


        } catch (IOException e) {
            System.out.println("jahaja: " + e);


        }
    }

    public void addToXKvitto(Kvitto k) {
        HashMap<String, Integer> tmp1;
        Iterator<String> tmp2;
        String index;
        tmp1 = k.getArt();
        tmp2 = tmp1.keySet().iterator();

        while (tmp2.hasNext()) {
            index = tmp2.next();
            xkvitto.updArt(index, tmp1.get(index), getArtikel(index).getPris());
        }
    }

    public void printKvitton() {
        try {
            String source = "", tmp3;
            HashMap<String, Integer> tmp1;
            Iterator<String> tmp2;

            if (kvitton.size() != 0) {

                for (int i = 0; i
                        < kvitton.size(); i++) {
                    if (i > 0) {
                        source += ";\n";


                    }

                    source += kvitton.get(i).getNr();
                    source += "; ";
                    source += kvitton.get(i).getDatum_tid();
                    source += "; ";

                    tmp1 = kvitton.get(i).getArt();
                    tmp2 = tmp1.keySet().iterator();



                    while (tmp2.hasNext()) {
                        tmp3 = tmp2.next();
                        source += tmp3;
                        source += " ";
                        source += tmp1.get(tmp3);


                        if (tmp2.hasNext()) {
                            source += ", ";


                        }
                    }

                }
                source += ";";
                System.out.println(source);



                char buffer[] = new char[source.length()];
                source.getChars(0, source.length(), buffer, 0);

                FileWriter fw = new FileWriter("kvitton.txt");
                fw.write(buffer);
                fw.close();
            }


        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());


        } catch (IOException e) {
            System.out.println("jahaja: " + e);


        }
    }

    public void readXKvitto() {
        try {
            Kvitto tmp;
            FileReader fr = new FileReader("xkvitto.txt");
            BufferedReader br = new BufferedReader(fr);
            String line, art;
            String[] parts;


            int j;


            if ((line = br.readLine()) != null) {
//                System.out.println(line);
                parts = line.split("; ");
//                System.out.println(parts[0]);
//                System.out.println(parts[1]);
                tmp = new Kvitto(Integer.valueOf(parts[0]).intValue(), parts[1]);
                parts[2] = parts[2].substring(0, parts[2].length() - 1);
                parts = parts[2].split(", ");

                for (int i = 0; i
                        < parts.length; i++) {
//                    System.out.println((parts[i].split(" "))[0]);
//                    System.out.println((parts[i].split(" "))[1]);
                    art = (parts[i].split(" "))[0];
                    j = Integer.valueOf((parts[i].split(" "))[1]).intValue();
                    tmp.updArt(art, j, getArtikel(art).getPris());



                }


                xkvitto = tmp;
            }
            fr.close();


        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());


        } catch (IOException e) {
            System.out.println("jahaja: " + e);


        }
    }

    public void turnXtoY() {
        String source = "", tmp3;
        HashMap<String, Integer> tmp1;
        Iterator<String> tmp2;
        source += xkvitto.getNr();
        source += "; ";
        source += xkvitto.getDatum_tid();
        source += "; ";

        tmp1 = xkvitto.getArt();
        tmp2 = tmp1.keySet().iterator();

        while (tmp2.hasNext()) {
            tmp3 = tmp2.next();
            source += tmp3;
            source += " ";
            source += tmp1.get(tmp3);

            if (tmp2.hasNext()) {
                source += ", ";
            }
        }
        System.out.println(source);
        ykvitton.add(xkvitto);
        xkvitto = new Kvitto(xkvitto.getNr() + 1);
        printY();
    }

    public void printXKvitton() {
        try {
            String source = "", tmp3;
            HashMap<String, Integer> tmp1;
            Iterator<String> tmp2;


            if ((xkvitto != null) && (!xkvitto.getArt().isEmpty())) {


                source += xkvitto.getNr();
                source += "; ";
                source += xkvitto.getDatum_tid();
                source += "; ";

                tmp1 = xkvitto.getArt();
                tmp2 = tmp1.keySet().iterator();

                while (tmp2.hasNext()) {
                    tmp3 = tmp2.next();
                    source += tmp3;
                    source += " ";
                    source += tmp1.get(tmp3);

                    if (tmp2.hasNext()) {
                        source += ", ";
                    }
                }

                source += ";";
                System.out.println(source);





                char buffer[] = new char[source.length()];
                source.getChars(0, source.length(), buffer, 0);

                FileWriter fw = new FileWriter("xkvitto.txt");
                fw.write(buffer);
                fw.close();
            }







        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());




        } catch (IOException e) {
            System.out.println("jahaja: " + e);




        }
    }

    public void readYKvitton() {
    }

    public void printYKvitton() {
    }

    public void addArtikel(String artnr, String ean, String namn, String info,
            int pris, int lager) {
        artiklar.add(new Artikel(
                artnr,
                ean,
                namn,
                info,
                pris,
                lager));







        int j = artiklar.size() - 1;






        this.artnr.put(artnr, j);
        /*
        System.out.println("artnr: " + artnr);
        System.out.println("index: " + j + "\n");
         */






        this.ean.put(ean, j);






    }

    public void updArtikel(String artnr, String ean, String namn, String info,
            int pris, int lager) {
        reArtikel(artnr);
        addArtikel(
                artnr, ean, namn, info, pris, lager);






    }

    public void reArtikel(String kod) {
        if (getArtikel(kod) != null) {
            Artikel a = getArtikel(kod);
            artiklar.remove(a);






            this.artnr.remove(a.getArtnr());






            this.ean.remove(a.getEan());






        }
    }

    public void addKvitto(HashMap<String, Integer> lista) {
        if (xkvitto == null) {
            xkvitto = new Kvitto(1);
        }
        Kvitto k = new Kvitto(kvitton.size() + 1);
        Iterator<String> it = lista.keySet().iterator();
        String art;
        while (it.hasNext()) {
            art = it.next();
            k.updArt(art, lista.get(art),
                    artiklar.get(artnr.get(art)).getPris());
            xkvitto.updArt(art, lista.get(art),
                    artiklar.get(artnr.get(art)).getPris());
            artiklar.get(artnr.get(art)).updLager((-1) * lista.get(art));
        }
        kvitton.add(k);
    }

    public void updKvitto(Kvitto k, String kod, int antal) {
        if (artnr.get(kod) == null) {
            kod = artiklar.get(ean.get(kod)).getArtnr();






        }
        k.updArt(kod, antal, artiklar.get(artnr.get(kod)).getPris());






    }

    public Artikel getArtikel(String kod) {

        if (artnr.get(kod) == null) {
            if (ean.get(kod) == null) {
                return null;






            } else {
                return artiklar.get(ean.get(kod));






            }

        }
        return artiklar.get(artnr.get(kod));






    }

    public void printX() {
    }

    public void printY() {
        try {
            String source = "", tmp3;
            HashMap<String, Integer> tmp1;
            Iterator<String> tmp2;


            System.out.println("what what: " + ykvitton.size());
            int i = ykvitton.size() -1;
            if (ykvitton.size() != 0) {
                source += ykvitton.get(i).getNr();
                source += ";\n";
                source += ykvitton.get(i).getDatum_tid();
                source = source.substring(0,source.length()-4);
                source += ";";

                tmp1 = ykvitton.get(i).getArt();
                tmp2 = tmp1.keySet().iterator();

                while (tmp2.hasNext()) {
                    tmp3 = tmp2.next();
                    source += "\n";
                    source += tmp3;
                    source += " ";
                    source += tmp1.get(tmp3);

                    if (tmp2.hasNext()) {
                        source += ", ";
                    }
                }

                source += ";\nsumma: " + ykvitton.get(i).getSumma();
                System.out.println(source);

                char buffer[] = new char[source.length()];
                source.getChars(0, source.length(), buffer, 0);

                FileWriter fw = new FileWriter("./ykvitton/" + ykvitton.size() + ".txt");
                fw.write(buffer);
                fw.close();




            }


        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());






        } catch (IOException e) {
            System.out.println("jahaja: " + e);






        }
    }

    public ArrayList<Artikel> getArtiklar() {
        return artiklar;





    }

    public ArrayList<String> getKlasser() {
        return klasser;


    }
}

