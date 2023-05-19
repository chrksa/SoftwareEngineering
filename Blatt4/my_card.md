//Zusammenarbeit von Lara Schröder, Christopher Liebsch, Rhea Leipnitz

Die Karte soll eine multiple/single choice Karte sein, die es einem
ermöglicht, sich aus mehreren Antworten eine auszusuchen.
Auf der Rückseite kann man dann überprüfen, ob diese
richtig war oder nicht.
Unter der richtige Lösung können(müssen aber nicht) noch Sätze zur näheren
Erklärung stehen.
Man kann sie auch zu einer groben Schätzungskarte umfunktionieren, indem man die Antwortenauswahl
weglässt und auf der Rückseite die Lösung steht.

Eingeleitet wird die Karte immer durch # (hinter der # ist ein Leerzeichen), die Frage und ein {MULTIPLE}.
Danach folgen 1-2 Zeilenumbrüche und dann werden (ggf.) die Antworten zur Auswahl eingetragen.
Die Antwort wird durch ein ## (wieder ein Leerzeichen dahinter), die Antwort und ein {SOLUTION} angekündigt.
Darunter steht entweder nichts oder wieder 1-2 Zeilenumbrüche und Text um die
Antwort zu erklären.

Erlaubt ist im Text selbst alles außer einer # am Zeilenanfang da diese zur
Orientierung für den Parser dient.

