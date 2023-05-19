/* ToDo: import standard java libraries you need e.g. java.io, java.utils, ... */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//Zusammenarbeit von Lara Schröder, Christopher Liebsch, Rhea Leipnitz
public class MarkdownLoader {
  // ---------------------------------------------------- //

  public static List<LearningCard> loadCardFile(String file_path) {
    List<LearningCard> learningList = new ArrayList<LearningCard>();   //Liste erstellen
    Path path = Paths.get(file_path);                                  //filepath erstellen
    try {
      String fileContent = Files.readString(path);                     //file Inhalt in einen String schreiben
      //Pattern schreiben -> durch oder getrennter Regex-code der Kartenklassen
      Pattern pattern = Pattern.compile("(?:" + SimpleCard.getRegex() + "|"  + QuestionCard.getRegex() + "|" + ChoiceCard.getRegex() + ")");
      //pattern matcher für file content
      Matcher match = pattern.matcher(fileContent);
      while (match.find()) {//solange es matches gibt ->
        if (match.group("simple") != null) {         //falls in Gruppe "simple" was steht-> falls das match ein SimpleCard-Format ist
          learningList.add(new SimpleCard(match.group("front"), match.group("back")));
          //Kostruktor-> Karte zur Liste hinzufügen
        }
        if (match.group("qcard") != null) {          //falls Gruppe "qcard" nicht leer ist ->match ist im QuestionCard Format
          if (match.group("btext1") != null) {       //falls btext1 nicht leer ist -> erst back dann front in der md Datei
            //-> zusätzlich zu quest und qtext noch front1, ftext1, btext1 und btext1 in den Konstruktor
            learningList.add(new QuestionCard(match.group("quest"), match.group("qtext"), match.group("front1"),
                match.group("ftext1"), match.group("back1"), match.group("btext1")));
            //-> Karte wird zur Liste hinzugefügt
          }
          //in der Lernkarte steht front dann wieder vor back, printet man das auf die Konsole ist es also nach Vorderseite/Rückseite sortiert

          if (match.group("btext2") != null) {      //falls Gruppe "btext2 nicht leer ist -> erst front dann back in der md Datei
            //-> zusätzlich zu quest und qtext noch front2, ftext2, btext2 und btext2 in den Konstruktor
            learningList.add(new QuestionCard(match.group("quest"), match.group("qtext"), match.group("front2"),
                match.group("ftext2"), match.group("back2"), match.group("btext2")));
            //-> Karte wird zur Liste hinzugefügt
          }
        }
        if (match.group("mcards") != null) {       //falls Gruppe "mcards" nicht leer ist ->match ist im ChoiceCard Format
          learningList.add(new ChoiceCard(match.group("mquest"), match.group("choices"), match.group("answer")
              , match.group("mback")));
          //-> Karte wird zur Listehinzugefügt
        }
      }//alles was kein match ist liegt nicht in einem der geforderten Lernkartenformaten vor und wird deshalb nicht
       //zur Liste hinzugefügt
    } catch (IOException e) {
      throw new RuntimeException(e);                    //z.B. falls keine file existiert oder man sie nicht öffnen kann
    }
    return learningList;
  }

  // ---------------------------------------------------- //
  public static void main(String[] args) {
    List<LearningCard> card = loadCardFile("src/cards.md");
    for (int i = 0; i < card.size(); i++) {
      card.get(i).printToConsole();           //Karten in die Konsole printen
      System.out.println();                   // newline nach jeder Lernkarte damit man erkennt wo eine neue Karte anfängt
    }
    //System.out.println(loadCardFile("src/cards.md").size()); //wie viele Karten sind drin
  }
}