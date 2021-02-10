

# Praktikum 3: Dynamische Programmierung (DP)

### Sequence Alignment mit DP. Global Alignment mit dem Needleman-Wunsch-Algorithmus

Berechnen Sie mit dem ***Needleman-Wunsch-Algorithmus*** das/ein optimales ***Global Alignment*** zweier Sequenzen.
Verwenden Sie die folgende ***Rekursionsgleichung*** und ***Scoring-Funktion***.
Fragen Sie die ***Werte der Scoring-Funktion*** für
* **Match**,
* **Mismatch** und
* **Gap**

beim Start des Programms interaktiv ab.

### Scoring-Funktion
```sh
                  {  1  falls  a  ==  b
weight(a, b) =    { -1  falls  a  !=  b
                  { -1  falls  a oder b ein GAP
```

### Rekursionsgleichung
```sh
                { D(i-1, j-1) +  equal(ai,  bj)   MATCH bzw. MISMATCH
D(i, j)  =  max { D(i-1, j  ) + weight(ai, '_')   Deletion
                { D(i  , j-1) + weight('_', bj)   Insertion
```

```sh
equal(a, b) =  { m  falls  a  ==  b   (   MATCH)
               { s  falls  a  !=  b   (MISMATCH)
```

Führen Sie den Algorithmus als **Beispiel** mit den folgenden Zeichenketten aus:

```sh
S1 = ACCGGTCGAGTGCGCGGAAGCCGGCCGA
S2 = GTCGTTCGGAATGCCGTTGCTCTGTAAA
```

---

## Bearbeitungshinweise

* Die Tests sind so aufgebaut, dass sie sinnvollerweise in nummerierter Reihenfolge abgearbeitet werden können.
* Sorgen Sie zuerst dafür, dass die
  * Score-Tests                     (01) und
  * Alignment-Tests                 (02) bestanden werden.
* Danach Bearbeiten Sie die einzelnen Teilschritte des NeedlemanWunsch-Algorithmus:
  * Initialisierungs-Tests          (03)
  * Calculation-Tests               (04)
  * Reconstruction-Tests            (05)
  * Reconstr.OperationsString-Tests (06)
* Wenn diese bestanden sind, sollten auch die
  * Align-Tests                     (07) bestanden sein.

---

## Vorgegebene Struktur

Klassen
* Scores
  * Hält die Score-Werte für MATCH, MISMATCH und GAP
  * weight(a, b) - Die Scoring-Funktion - Liefert den Score für den Vergleich der Zeichen a und b
  * equal(a, b)  - Equal - Liefert MATCH oder MISMATCH-Score
* Alignment
  * Alle Daten die für ein Alignment benötigt werden
    * Sequenzen die aligniert werden sollen
    * ScoreMatrix
    * Ergebnis-Sequenzen/Alignment-Strings
    * OperationsString - Folge der Operationen die beim Alignment angewendet wurden
* NeedlemanWunsch
  * Bietet die Methoden zum Alignment zweier Sequenzen mit dem Needleman-Wunsch-Algorithmus an.
    * Align-Methoden
    * Methoden der Einzelschritte des Algorithmus
      * Initialize  ---> Initialisiert die ScoreMatrix
      * Calculate   ---> Berechnet die ScoreMatrix mit den vorgegebenen Scores
      * Reconstruct ---> Rekonstruiert die Alignment-Strings und den OperationsString

ergänzend:
* MatrixUtil
  * Methoden zur Ausgabe:
    * Scorematrix
    * Alignment-Tabelle
    * Alignment-Tabelle in color (experimentell)

