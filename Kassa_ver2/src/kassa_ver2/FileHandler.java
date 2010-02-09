/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kassa_ver2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import kassa_ver2.dbObjects.*;

/**
 *
 * @author Fredrik
 */
public class FileHandler implements FileInterface {

    private final String file_a = "a.txt";
    private final String file_c = "c.txt";
    private final String file_r = "r.txt";
    private final String file_s = "s.txt";
    private final String file_x = "x.txt";
    private final String file_y = "y.txt";
    private DataHandler dh;

    public FileHandler(DataHandler dh) {
        this.dh = dh;
        init();
    }

    public void init() {
        readClasses();
        readArticles();
        readReceipts();
        readStock();
        readX();
        readY();
    }

    public void printArticle(String[] str) {
        BufferedWriter bw = null;
        String line = "";
        line += str[0] + "; " //artnr
                + str[1] + "; " //ean
                + str[2] + "; " //name
                + str[3] + "; " //info
                + str[4] + ";";   //price

        try {
            bw = new BufferedWriter(new FileWriter(file_a, true));
            bw.write(line);
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            System.out.println("jahaja: " + e);
        }
    }

    public void printStock(String[] str) {
        BufferedWriter bw = null;
        String line = "";
        line += str[0] + "; " //artnr
                + str[1] + "; " //datum
                + str[2] + ";";   //change

        try {
            bw = new BufferedWriter(new FileWriter(file_s, true));
            bw.write(line);
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            System.out.println("jahaja: " + e);
        }
    }

    public void printClass(String[] str) {

        BufferedWriter bw = null;
        String line = "";
        line += str[0] + "; " //nr
                + str[1] + ";";   //name
        try {
            bw = new BufferedWriter(new FileWriter(file_c, true));
            bw.write(line);
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            System.out.println("jahaja: " + e);
        }
    }

    public void printY(String[] str) {
        BufferedWriter bw = null;
        String line = "";
        line += str[0] + "; " //nr
                + str[1] + "; " //datum
                + str[2] + ";";   //info

        try {
            bw = new BufferedWriter(new FileWriter(file_y, true));
            bw.write(line);
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            System.out.println("jahaja: " + e);
        }
    }

    public void printX(String[] str) {
        BufferedWriter bw = null;
        String line = "";
        line += str[0] + "; " //nr
                + str[1] + "; " //datum
                + str[2] + ";";   //info

        try {
            bw = new BufferedWriter(new FileWriter(file_x));
            bw.write(line);
            bw.flush();

        } catch (IOException e) {
            System.out.println("jahaja: " + e);
        }
    }

    public void printReceipt(String[] str) {
        BufferedWriter bw = null;
        String line = "";
        line += str[0] + "; " //nr
                + str[1] + "; " //datum
                + str[2] + ";";   //info

        try {
            bw = new BufferedWriter(new FileWriter(file_r, true));
            bw.write(line);
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            System.out.println("jahaja: " + e);
        }
    }

    private void readArticles() {

        String line = "";
        String[] parts = {};
        try {
            FileReader fr = new FileReader(file_a);
            BufferedReader br = new BufferedReader(fr);


            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    parts = line.split("; ");


                    parts[4] = parts[4].substring(0, (parts[4].length()) - 1);
                    dh.addArticle(new Article(parts));


                }
            }

            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());
        } catch (IOException e) {
            System.out.println("jahaja: " + e);
        }
    }

    private void readReceipts() {
        try {
            Receipt tmp;
            FileReader fr = new FileReader(file_r);
            BufferedReader br = new BufferedReader(fr);
            String line, art;
            String[] parts;


            int j;


            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    parts = line.split("; ");

                    tmp = new Receipt(Integer.valueOf(parts[0]).intValue(), parts[1]);
                    parts[2] = parts[2].substring(0, parts[2].length() - 1);
                    parts = parts[2].split(", ");

                    for (int i = 0; i
                            < parts.length; i++) {
                        art = (parts[i].split(" "))[0];
                        j = Integer.valueOf((parts[i].split(" "))[1]).intValue();

                        tmp.updArt(art, j, Integer.valueOf((dh.getArticle(art)).getPrice()).intValue());

                    }
                    dh.addReceipt(tmp);
                }
            }
            fr.close();


        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());


        } catch (IOException e) {
            System.out.println("jahaja: " + e);


        }
    }

    private void readX() {
        try {
            Receipt tmp;
            FileReader fr = new FileReader(file_x);
            BufferedReader br = new BufferedReader(fr);
            String line, art;
            String[] parts;


            int j;


            if ((line = br.readLine()) != null) {
                parts = line.split("; ");

                tmp = new Receipt(Integer.valueOf(parts[0]).intValue(), parts[1]);
                parts[2] = parts[2].substring(0, parts[2].length() - 1);
                parts = parts[2].split(", ");

                for (int i = 0; i
                        < parts.length; i++) {
                    art = (parts[i].split(" "))[0];
                    j = Integer.valueOf((parts[i].split(" "))[1]).intValue();

                    tmp.updArt(art, j, Integer.valueOf((dh.getArticle(art)).getPrice()).intValue());

                }
                dh.setX(tmp);
            }
            fr.close();


        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());


        } catch (IOException e) {
            System.out.println("jahaja: " + e);


        }
    }

    private void readY() {
        try {
            Receipt tmp;
            FileReader fr = new FileReader(file_y);
            BufferedReader br = new BufferedReader(fr);
            String line, art;
            String[] parts;


            int j;


            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    parts = line.split("; ");

                    tmp = new Receipt(Integer.valueOf(parts[0]).intValue(), parts[1]);
                    parts[2] = parts[2].substring(0, parts[2].length() - 1);
                    parts = parts[2].split(", ");

                    for (int i = 0; i
                            < parts.length; i++) {
                        art = (parts[i].split(" "))[0];
                        j = Integer.valueOf((parts[i].split(" "))[1]).intValue();

                        tmp.updArt(art, j, Integer.valueOf((dh.getArticle(art)).getPrice()).intValue());

                    }
                    dh.addToY(tmp);
                }
            }
            fr.close();


        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());


        } catch (IOException e) {
            System.out.println("jahaja: " + e);


        }
    }

    private void readClasses() {

        String line = "";
        String[] parts = {};
        try {
            FileReader fr = new FileReader(file_c);
            BufferedReader br = new BufferedReader(fr);


            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {

                    parts = line.split("; ");
                    parts[1] = parts[1].substring(0, (parts[1].length()) - 1);
                    dh.addClass(parts);
                }
            }

            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());
        } catch (IOException e) {
            System.out.println("jahaja: " + e);
        }
    }

    private void readStock() {

        String line = "";
        String[] parts = {};
        try {
            FileReader fr = new FileReader(file_s);
            BufferedReader br = new BufferedReader(fr);


            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    parts = line.split("; ");
                    parts[2] = parts[2].substring(0, (parts[2].length()) - 1);
                    dh.updStock(parts);
                }
            }

            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("något väldigt konstigt har uppstått\n" + e.getLocalizedMessage());
        } catch (IOException e) {
            System.out.println("jahaja: " + e);
        }
    }
}
