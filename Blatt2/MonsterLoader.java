/* ToDo: import standard java libraries you need e.g. java.io, java.utils, ... */

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.io.*;


public class MonsterLoader {
    // --------------------------------------------------------------- //
    /**
     * Load the monsters from the given file. If something is wrong with a monster, it is not loaded.
     * The loading process continues with the next monster.
     */
    public static List<Monster> loadMonsterFile(String file_path) {
        /* ToDo: implement this method */
        List<String> lines= new ArrayList<>();
        try {
            lines= Files.readAllLines(Path.of(file_path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String input;
        int counter= 0;
        String string_in="";
        String name="";
        int maxHP=1;
        int attack=1;
        float weight=1;
        float multi=1;
        List<Monster> monst= new ArrayList<Monster>();
        for (String line : lines) {
            input = line;
            if (counter == 5) {
                for (int j = 0; j < input.length(); j++) {
                    if (j + 6 >= input.length()-1) {
                        continue;
                    }
                    if (input.charAt(j) == 'm' && input.charAt(j+1) == 'u' && input.charAt(j+2) == 'l' && input.charAt(j+3) == 't' && input.charAt(j+4) == 'i' && input.charAt(j+5) == ' ') {
                        counter++;
                        for (int k=j+6; k< input.length();k++){
                            string_in+=input.charAt(k);
                        }
                        multi=Float.parseFloat(string_in);
                        string_in="";
                        //System.out.println("multi");
                        break;
                    }
                    else {
                        counter=0;
                    }
                }
            }
            if (counter == 4) {

                for (int j = 0; j < input.length(); j++) {
                    if (j + 7 >= input.length()-1) {
                        continue;
                    }
                    if (input.charAt(j) == 'w' && input.charAt(j+1) == 'e' && input.charAt(j+2) == 'i' && input.charAt(j+3) == 'g' && input.charAt(j+4) == 'h' && input.charAt(j+5) == 't'&& input.charAt(j+6) == ' ' ) {
                        counter++;
                        for (int k=j+7; k< input.length();k++){
                            string_in+=input.charAt(k);
                        }
                        weight=Float.parseFloat(string_in);
                        string_in="";
                        //System.out.println("weight");
                        break;
                    }
                    else {
                        counter=0;
                    }
                }
            }
            if (counter == 3) {
                for (int j = 0; j < input.length(); j++) {
                    if (j + 7 >= input.length()-1) {
                        continue;
                    }
                    if (input.charAt(j) == 'a' && input.charAt(j+1) == 't' && input.charAt(j+2) == 't' && input.charAt(j+3) == 'a' && input.charAt(j+4) == 'c' && input.charAt(j+5) == 'k' && input.charAt(j+6) == ' ') {
                        counter++;
                        for (int k=j+7; k< input.length();k++){
                            string_in+=input.charAt(k);
                        }
                        attack=Integer.parseInt(string_in);
                        string_in="";
                        //System.out.println("attack");
                        break;
                    }
                    else {
                        counter=0;
                    }
                }
            }
            if (counter == 2) {
                for (int j = 0; j < input.length(); j++) {
                    if (j + 6 >= input.length()-1) {
                        continue;
                    }
                    if (input.charAt(j) == 'm' && input.charAt(j+1) == 'a' && input.charAt(j+2) == 'x' && input.charAt(j+3) == 'H' && input.charAt(j+4) == 'P' && input.charAt(j+5) == ' ' ) {
                        counter++;
                        for (int k=j+6; k< input.length();k++){
                            string_in+=input.charAt(k);
                        }
                        maxHP=Integer.parseInt(string_in);
                        string_in="";
                        //System.out.println("maxHP");
                        break;
                    }
                    else {
                        counter=0;
                    }
                }
            }
            if (counter == 1) {
                for (int j = 0; j < input.length(); j++) {
                    if (j + 5 >= input.length()-1) {
                        counter=0;
                        break;
                    }
                    if (input.charAt(j) == 'n' && input.charAt(j+1) == 'a' && input.charAt(j+2) == 'm' && input.charAt(j+3) == 'e' && input.charAt(j+4) == ' ' ) {
                        counter++;
                        for (int k=j+5; k< input.length();k++){
                            string_in+=input.charAt(k);
                        }
                        name=string_in;
                        string_in="";
                        //System.out.println("name");
                        break;
                    }
                    else {
                        counter=0;
                    }
                }
            }

            if(counter==0) {
                for (int j = 0; j < input.length(); j++) {
                    if (j + 6 >= input.length()) {
                        continue;
                    }
                    if (input.charAt(j) == 'M' && input.charAt(j+1) == 'o' && input.charAt(j+2) == 'n' && input.charAt(j+3) == 's' && input.charAt(j+4) == 't' && input.charAt(j+5) == 'e' && input.charAt(j+6) == 'r') {
                        counter++;
                        //System.out.println("Monster");

                    }
                }
            }
            if(counter == 6){
                Monster mon= new Monster(name,maxHP,weight,attack,multi);
                monst.add(mon);
                counter=0;
            }

        }
        return monst;
    }

    // --------------------------------------------------------------- //
    public static void main(String[] args) {
        //Path ruta= FileSystems.getDefault().getPath("monster_ok.txt");
        List<Monster> mon= loadMonsterFile("monster_ok.txt");
        System.out.println(mon);


    }
}