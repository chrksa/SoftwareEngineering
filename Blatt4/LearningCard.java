import java.util.Arrays;
import java.util.List;
//Zusammenarbeit von Lara Schröder, Christopher Liebsch, Rhea Leipnitz
// ------------------------------------------------------ //
public abstract class LearningCard {

  /**
   * return the front site of the card
   */
  public abstract List<String> getFrontContent();

  /**
   * return the back site of the card
   */
  public abstract List<String> getBackContent();

  /**
   * return combined content of front and back
   */
  public abstract List<String> getContent();

  /**
   * write the content to the console in a meaningful way
   */
  public abstract void printToConsole();
}

// ------------------------------------------------------ //
class SimpleCard extends LearningCard {
  private String _front;
  private String _back;

  public SimpleCard(String front, String back) {
    _front = front;
    _back = back;
  }

  //(?<simple>(?:# (?<front>[^\\{\\n]*)\\n)(?:\\n?(?<back>(?:[^\\n]+\\n?)*))) //ganzer regex String

  public static String getRegex() {                         //damit man im md Loader an den Ausdruck kommt
    String regex = "";                                      //Initialisierung
    String frontSymbols = "[^\\{\\n]";                      //ein Zeichen, alles außer geschweifter Klammer(zur Differenzierung von qcard) und Zeilenumbruch auf der Vorderseite
    String backSymbols = "[^\\n]";                          //ein Zeichen, alles außer Zeilenumbruch

    regex += "(?<simple>";                                  // SimpleCard Capturing Group öffnen -> gruppiert das ganze Match
    regex += "(?:# (?<front>" + frontSymbols + "*)\\n)";    // Vorderseiten-Gruppe benennen, frontSymbols hinzufügen, 0-* Zeichen, Zeilenumbruch
    regex += "(?:\\n?";                                     //ggf. doppelter Zeilenumbruch
    regex += "(?<back>(?:" + backSymbols + "+\\n?)*))";     //Rückseiten-Gruppe benennen, back Symbols hinzufügen, 1-* Zeichen, danach 0-1 Zeilenumbrüche (das alles 0-* Mal)
    regex += ")";                                           // SimpleCard Capturing Group schließen
    return regex;
  }

  @Override
  public List<String> getFrontContent() {
    return Arrays.asList(_front.split("\n"));           //String zu ner String Liste umwandeln, an dem Zeilenumbruch cutten
  }

  @Override
  public List<String> getBackContent() {
    return Arrays.asList(_back.split("\n"));            //String zu ner String Liste umwandeln, an dem Zeilenumbruch cutten
  }

  @Override
  public List<String> getContent() {
    String content = _front + "\n" + _back;                   //front und back zu einem content String zusammenfügen
    return Arrays.asList(content.split("\n"));          //String zu ner String Liste umwandeln, an dem Zeilenumbruch cutten
  }

  @Override
  public void printToConsole() {
    getContent().forEach(System.out::println);               //jede Zeile aus der content List printen
  }
}

// ------------------------------------------------------ //
class QuestionCard extends LearningCard {
  //regex code
  /*(?<qcard>(?:# (?<quest>.*)\{QUESTION\}\n\n?(?<qtext>[^#\n].*\n?)*\n?)(?:(?:## (?<back1>.+)?\{BACK\}\n\n?(?<btext1>(?:[^#\n].*(?:\n|$))*)\n?
  ## (?<front1>.+)?\{FRONT\}\n\n?(?<ftext1>(?:[^#\n].*\n?)*))|(?:## (?<front2>.+)?\{FRONT\}\n\n?(?<ftext2>(?:[^#\n].*(?:\n|$))*)\n?
  ## (?<back2>.+)?\{BACK\}\n\n?(?<btext2>(?:[^#\n].*(?:\n|$))*\n?))))*/
  private String _front;
  private String _back;

  public QuestionCard(String quest, String qtext, String front, String ftext, String back, String btext) {
    //falls qtext, front oder back null -> nix dranhängen, ansonsten Text und Zeilenumbruch
    //sortieren nach Vorder-und Rückseite
    _front = quest + (qtext == null ? "" : "\n" + qtext) + (front == null ? "" : front + "\n") + ftext;
    _back = (back == null ? "" : back + "\n") + btext;
  }

  public static String getRegex() {
    String regex = "";
    regex += "(?<qcard>";                                 //qcard capturing group start
    regex += "(?:# (?<quest>.*)\\{QUESTION\\}\\n\\n?";    //Benennung,die Frage selbst(.*),{QUESTION} und die Zeilenumbrüche
    //falls erst Back dann Front in md Datei
    regex += "(?<qtext>[^#\\n].*\\n?)*\\n?)";             //ggf. Text drunter der aber nicht mit Raute oder \n beginnen darf
    regex += "(?:(?:## (?<back1>.+)?\\{BACK\\}\\n\\n?";   //Rückseitenüberschrift (.+)? ->kann auch leer sein(gibt es mindestens ein Zeichen?) {BACK} und Zeilenumbruch
    regex += "(?<btext1>(?:[^#\\n].*(?:\\n|$))*)\\n?";    //Text drunter der nicht mit Raute oder \n beginnen darf, ansonsten .* und Zeilenumbruch oder Stringende
    regex += "## (?<front1>.+)?\\{FRONT\\}\\n\\n?";       //Vorderseitenüberschrift siehe Rückseitenüberschrift
    regex += "(?<ftext1>(?:[^#\\n].*\\n?)*))";            //Text drunter darf nicht mit Raute oder \n beginen usw.
    regex += "|";                                         //ooooooder
    //erst Front dann Back in md Datei
    regex += "(?:## (?<front2>.+)?\\{FRONT\\}\\n\\n?";    //Vorderseitenüberschrift siehe oben
    regex += "(?<ftext2>(?:[^#\\n].*(?:\\n|$))*)\\n?";    //Text drunter siehe oben
    regex += "## (?<back2>.+)?\\{BACK\\}\\n\\n?";         //Rückseitenüberschrift siehe oben
    regex += "(?<btext2>(?:[^#\\n].*(?:\\n|$))*\\n?)))";  //Text drunter siehe oben
    regex += ")";                                         //gcard capturing group ende
    return regex;
  }

  @Override
  public List<String> getFrontContent() {
    return Arrays.asList(_front.split("\n"));      //String zu ner String Liste umwandeln, an dem Zeilenumbruch cutten
  }

  @Override
  public List<String> getBackContent() {
    return Arrays.asList(_back.split("\n"));       //String zu ner String Liste umwandeln, an dem Zeilenumbruch cutten
  }

  @Override
  public List<String> getContent() {
    String content = _front + _back;                     //front und back zu einem content String zusammenfügen
    return Arrays.asList(content.split("\n"));     //String zu ner String Liste umwandeln, an dem Zeilenumbruch cutten
  }

  @Override
  public void printToConsole() {
    getContent().forEach(System.out::println);           //jede Zeile printen
  }
}

class ChoiceCard extends LearningCard {
  private String _front;
  private String _back;
  public ChoiceCard(String quest, String choices, String answer, String back) {
    _front = quest + (choices == null ? "" : "\n" + choices);    //choices könnten leer sein -> Schätzfragen ohne feste Auswahl?
    _back = answer + (back == null ? "" : "\n" + back);          //answer könnte leer sein
  }

  public static String getRegex() {
    /*(?<mcards># (?<quest>.*)\\{MULTIPLE\\}\\n\\n?(?<choices>(?:[^#\\n].*\\n?)*)\\n?## (?<back>.*)\\{SOLUTION\\}\\n\\n?
    (?<answer>(?:[^#\\n].*\\n?)*)\\n?)*/
    String regex = "";
    regex += "(?<mcards>";                                 //öffnen
    regex += "# (?<mquest>.*)\\{MULTIPLE\\}\\n\\n?";       //Gruppenbenennung, Fragenüberschrift mit {MULTIPLE}, danach Zeilenumbruch (ggf. doppelter)
    regex += "(?<choices>(?:[^#\\n].*\\n?)*)\\n?";         //Gruppenbenennung, die Fragen (dürfen nicht mit # oder \n beginnen)
    regex += "## (?<answer>.*)\\{SOLUTION\\}(?:\\n\\n?";   //Gruppenbenennung, Antwort, dann {SOLUTION} und Zeilebumbruch (ggf. doppelter)
    regex += "(?<mback>(?:[^#\\n].*\\n?)*)\\n?)?";         //Gruppenbenennung, Erklärung (dürfen nicht mit # oder \n beginnen), dürfen leer sein
    regex += ")";                                          //schließen
    return regex;
  }

  @Override
  public List<String> getFrontContent() {
    return Arrays.asList(_front.split("\n"));        //String zu ner String Liste umwandeln, an dem Zeilenumbruch cutten
  }

  @Override
  public List<String> getBackContent() {
    return Arrays.asList(_back.split("\n"));         //String zu ner String Liste umwandeln, an dem Zeilenumbruch cutten
  }

  @Override
  public List<String> getContent() {
    String content = _front + _back;                      //front und back zu einem content String zusammenfügen
    return Arrays.asList(content.split("\n"));      //String zu ner String Liste umwandeln, an dem Zeilenumbruch cutten
  }

  @Override
  public void printToConsole() {
    getContent().forEach(System.out::println);           //jede Zeile printen
  }
}