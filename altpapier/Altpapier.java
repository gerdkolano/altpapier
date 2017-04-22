// hanno@zoe:~/erprobe/java$ javac altpapier/Altpapier.java 
// hanno@zoe:~/erprobe/java$ java altpapier.Altpapier > /daten/srv/www/htdocs/erprobe/altpapier/abholtermine.html

package altpapier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by hanno on 27.08.14.
 * <p/>
 * Altpapier -- compute the difference between two dates.
 */
public class Altpapier {
  public static void main(String[] av) {
    /** The date at the end of the last century */
    Date d1 = new GregorianCalendar(2000, 11, 31, 23, 59).getTime();

    /** Today's date */
    Date today = new Date();

    // Get msec from each, and subtract.
    long diff = today.getTime() - d1.getTime();

    // Construct a new GregorianCalendar initialized to the current date
    Calendar heute = new GregorianCalendar(); // auf null Uhr stellen
    heute.set(Calendar.DAY_OF_MONTH, 1);
    heute.set(Calendar.HOUR, 0);
    heute.set(Calendar.MINUTE, 0);
    heute.set(Calendar.SECOND, 0);
    heute.set(Calendar.MILLISECOND, 0);

    Calendar jetzt = new GregorianCalendar(); // auf null Uhr stellen
    jetzt.set(Calendar.DAY_OF_MONTH, 1);
    jetzt.set(Calendar.HOUR, 0);
    jetzt.set(Calendar.MINUTE, 0);
    jetzt.set(Calendar.SECOND, 0);
    jetzt.set(Calendar.MILLISECOND, 0);
    System.out.println( heute.compareTo( jetzt));

    Holtermine holtermine = new Holtermine(heute);

    GregorianCalendar ersterTagGelb = new GregorianCalendar(2014, 0, 2);
    GregorianCalendar ersterTagBerl = new GregorianCalendar(2014, 0, 22);
    GregorianCalendar ersterTagVeol = new GregorianCalendar(2014, 0, 14);
    GregorianCalendar ersterTagAlba = new GregorianCalendar(2014, 0, 8);
    //GregorianCalendar letzterTag = new GregorianCalendar( heute.get(Calendar.YEAR)+1, heute.get(Calendar.MONTH),  heute.get(Calendar.DAY_OF_MONTH));
    GregorianCalendar letzterTag = new GregorianCalendar( heute.get(Calendar.YEAR)+1, heute.get(Calendar.MONTH),  1);

    holtermine.fügeAbholtermineEinerFirmaHinzu(2, "Gelber Sack", ersterTagGelb, letzterTag);
    holtermine.fügeAbholtermineEinerFirmaHinzu(4, "Berlin Recycling", ersterTagBerl, letzterTag);
    holtermine.fügeAbholtermineEinerFirmaHinzu(4, "Veolia", ersterTagVeol, letzterTag);
    holtermine.fügeAbholtermineEinerFirmaHinzu(4, "Alba Pappy", ersterTagAlba, letzterTag);

    holtermine.zeigeAbholtermine();

    System.out.println("Das 21ste Jahrhundert ist am \"" + today + "\" "
        + (diff / (1000 * 60 * 60 * 24)) + " Tage alt.");
    System.out.println(" ");

    holtermine.zeigeMonatstabellen( letzterTag);

    System.out.println("Das 21ste Jahrhundert ist am \"" + today + "\" "
        + (diff / (1000 * 60 * 60 * 24)) + " Tage alt.");
  }
}

class EinTermin implements Comparable<EinTermin> {
  private String firma;
  private GregorianCalendar gZeitpunkt;
  //private long zeitpunkt;

  EinTermin(String firma, GregorianCalendar gZeitpunkt) {
    this.firma = firma;
    this.gZeitpunkt = gZeitpunkt;
  }

  @Override
  public int compareTo(EinTermin n) {
    return this.gZeitpunkt.compareTo(n.gZeitpunkt);
    /*
    int lastCmp = Long.compare(this.zeitpunkt, n.zeitpunkt);
    return (lastCmp != 0 ? lastCmp : this.firma.compareTo(n.firma));
    */
  }

  String[] tagesNam = new String[7];
  String[] tagesName = {"Sa", "So", "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"};

  public String firma() {
    return this.firma/*.substring(0, 2)*/;
  }

  public String toString() {
    return String.format("%d %s %04d-%02d-%02d %s",
        this.gZeitpunkt.get(Calendar.DAY_OF_WEEK),
        tagesName[this.gZeitpunkt.get(Calendar.DAY_OF_WEEK)],
        this.gZeitpunkt.get(Calendar.YEAR),
        1 + this.gZeitpunkt.get(Calendar.MONTH),
        this.gZeitpunkt.get(Calendar.DAY_OF_MONTH),
        firma
    );
  }
}

class HTML {
  static public String utf_8 = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">";
  static public String tdaspan = "<td colspan=\"7\" align=\"center\" >";
  static public String tda = "<td>", tde = "</td>";
  static public String tra = "<tr>", tre = "</tr>";
  static public String tablea = "<table border=\"2\">", tablee = "</table>";
}

class Holtermine {
  private List<EinTermin> abholterminliste;
  Calendar abHeute;

  Holtermine() {
    abholterminliste = new ArrayList<EinTermin>();
  }

  Holtermine(Calendar abHeute) {
    this();
    this.abHeute = abHeute;
  }

  int marke = 0;
  String formatFirma = "%-6.6s ";
  String formatNummer = "%2d     ";

  /**
   * Schiebe eventuell "marke" vorwärts und liefere die Tagesnummer oder den Firmennamen.
   *
   * @param laufenderTag
   * @return
   */
  String nächsterTagOderFirma(GregorianCalendar laufenderTag) {
    EinTermin suchMarke = new EinTermin("", laufenderTag);
    //if (laufenderTag.compareTo( abHeute) > 0) {
    if (abHeute.get(Calendar.YEAR) == laufenderTag.get(Calendar.YEAR)
       && abHeute.get(Calendar.MONTH) == laufenderTag.get(Calendar.MONTH)
       && abHeute.get(Calendar.DAY_OF_MONTH) == laufenderTag.get(Calendar.DAY_OF_MONTH)) {
      formatFirma = "<strong>%-6.6s </strong>";
    } else {
      //formatFirma = "%-6.6s ";
    }
    while (marke < abholterminliste.size()) {
      EinTermin heuMarke = abholterminliste.get(marke);
      int vergleichsergebnis = heuMarke.compareTo(suchMarke);
      if (vergleichsergebnis < 0)
        marke++;
      if (vergleichsergebnis == 0)
        return String.format(formatFirma, abholterminliste.get(marke).firma());
      if (vergleichsergebnis > 0)
        return String.format(formatNummer, laufenderTag.get(Calendar.DAY_OF_MONTH));

    }
    //return String.format(formatNummer, laufenderTag.get(Calendar.DAY_OF_MONTH));
    return String.format("##%02d", laufenderTag.get(Calendar.DAY_OF_MONTH));
  }

  public void fügeAbholtermineEinerFirmaHinzu(int intervall, String firma,
                                              GregorianCalendar laufenderMonat, GregorianCalendar ende) {
    GregorianCalendar zeitpunkt;
    for (;
         laufenderMonat.compareTo(ende) < 0;
         laufenderMonat.add(Calendar.DAY_OF_MONTH, intervall * 7)) {
      GregorianCalendar neuerTermin = new GregorianCalendar(
          laufenderMonat.get(Calendar.YEAR),
          laufenderMonat.get(Calendar.MONTH),
          laufenderMonat.get(Calendar.DAY_OF_MONTH)
      );
      if (neuerTermin.compareTo(abHeute) >= 0)
        zurListe(new EinTermin(firma, neuerTermin));
    }
  }

  public void zurListe(EinTermin einTermin) {
    //System.out.println(einTermin.toString());
    abholterminliste.add(einTermin);
    //zeigeAbholtermine();System.out.println();
  }

  public void zeigeAbholtermine() {
    Collections.sort(abholterminliste);
    System.out.println( "<pre>\n");
    for (EinTermin einTermin : abholterminliste) {
      System.out.println(einTermin.toString());
    }
    System.out.println( "</pre>\n");
  }

  String[] monatsName = {
      "Januar", "Februar", "März", "April",
      "Mai", "Juni", "Juli", "August",
      "September", "Oktober", "November", "Dezember"};

  void zeigeMonatstabellen( GregorianCalendar allerletzterTag) {
    Collections.sort(abholterminliste);
    GregorianCalendar laufenderMonat = new GregorianCalendar(
        abHeute.get(Calendar.YEAR),
        abHeute.get(Calendar.MONTH),
        1 // abHeute.get(Calendar.DAY_OF_MONTH)
    );
    String erg = "";
    erg += HTML.utf_8;
    erg += "\n";
    erg += HTML.tablea;
    erg += "\n";
    for (
        ;
        laufenderMonat.compareTo(allerletzterTag) < 0;
        laufenderMonat.add(Calendar.MONTH, 1)) {
      int rest;
      GregorianCalendar ultimo = new GregorianCalendar(
          laufenderMonat.get(Calendar.YEAR),
          laufenderMonat.get(Calendar.MONTH),
          laufenderMonat.get(Calendar.DAY_OF_MONTH));
      ultimo.add(Calendar.MONTH, 1);

      GregorianCalendar laufenderTag = new GregorianCalendar(
          laufenderMonat.get(Calendar.YEAR),
          laufenderMonat.get(Calendar.MONTH),
          laufenderMonat.get(Calendar.DAY_OF_MONTH));

      erg += HTML.tra;
      erg += HTML.tdaspan;
      erg += monatsName[laufenderTag.get(Calendar.MONTH)]
          + " " + laufenderTag.get(Calendar.YEAR)
          + HTML.tde
          + HTML.tre
          + "\n";

      rest = (5 + laufenderTag.get(Calendar.DAY_OF_WEEK)) % 7;
      erg += HTML.tra;
      for (int ii = rest; ii > 0; ii--) { // unvollständige Woche am Anfang des Monats
        erg += HTML.tda;
        erg += String.format(formatFirma, "..");
        erg += HTML.tde;
      }
      for (;                             // vollständige Wochen im Inneren des Monats
           laufenderTag.compareTo(ultimo) < 0;
           laufenderTag.add(Calendar.DAY_OF_MONTH, 1)) {
        erg += HTML.tda;
        erg += this.nächsterTagOderFirma(laufenderTag);
        erg += HTML.tde;
        // erg += String.format("%02d ", laufenderTag.get(Calendar.DAY_OF_MONTH));
        if (laufenderTag.get(Calendar.DAY_OF_WEEK) == 1) {
          erg += HTML.tre;
          erg +=
              // " " + (1 + laufenderTag.get(Calendar.MONTH))
              // + " " + laufenderTag.get(Calendar.YEAR)
              // +
              "\n";
          erg += HTML.tra;
        }
      }
      rest = (9 - laufenderTag.get(Calendar.DAY_OF_WEEK)) % 7;
      for (int ii = rest; ii > 0; ii--) { // unvollständige Woche am Ende des Monats
        erg += HTML.tda;
        erg += String.format(formatFirma, "..");
        erg += HTML.tde;
      }
      erg += HTML.tre;
      if (rest > 0) erg += "\n";
    }
    erg += HTML.tablee;
    System.out.println(erg);
  }

  public Date getEasterDate(int year) {
    Date result = null;

    int a = year % 19;
    int b = year / 100;
    int c = year % 100;
    int d = b / 4;
    int e = b % 4;
    int f = (b + 8) / 25;
    int g = (b - f + 1) / 3;
    int h = (19 * a + b - d - g + 15) % 30;
    int i = c / 4;
    int k = c % 4;
    int l = (32 + 2 * e + 2 * i - h - k) % 7;
    int m = (a + 11 * h + 22 * l) / 451;
    int p = (h + l - 7 * m + 114) % 31;

    int month = (h + l - 7 * m + 114) / 31;
    int day = p + 1;

    GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
    result = gc.getTime();

    return result;
  }

  public int getEasterSundayMonth(int y) {
    int a = y % 19;
    int b = y / 100;
    int c = y % 100;
    int d = b / 4;
    int e = b % 4;
    int g = (8 * b + 13) / 25;
    int h = (19 * a + b - d - g + 15) % 30;
    int j = c / 4;
    int k = c % 4;
    int m = (a + 11 * h) / 319;
    int r = (2 * e + 2 * j - k - h + m + 32) % 7;
    int n = (h - m + r + 90) / 25;
    int p = (h - m + r + n + 19) % 32;
    int month = n;
    int day = p;
    return p;
  }

}

/*
http://www.merlyn.demon.co.uk/estralgs.txt
https://de.wikipedia.org/wiki/Gau%C3%9Fsche_Osterformel
Wikipedia Lichtenberg
 1. die Säkularzahl:                                       K(X) = X div 100
 2. die säkulare Mondschaltung:                            M(K) = 15 + (3K + 3) div 4 − (8K + 13) div 25
 3. die säkulare Sonnenschaltung:                          S(K) = 2 − (3K + 3) div 4
 4. den Mondparameter:                                     A(X) = X mod 19
 5. den Keim für den ersten Vollmond im Frühling:        D(A,M) = (19A + M) mod 30
 6. die kalendarische Korrekturgröße:                    R(D,A) = (D + A div 11) div 29[12]
 7. die Ostergrenze:                                    OG(D,R) = 21 + D − R
 8. den ersten Sonntag im März:                         SZ(X,S) = 7 − (X + X div 4 + S) mod 7
 9. die Entfernung des Ostersonntags von der
    Ostergrenze (Osterentfernung in Tagen):           OE(OG,SZ) = 7 − (OG − SZ) mod 7
10. das Datum des Ostersonntags als Märzdatum
    (32. März = 1. April usw.):                              OS = OG + OE
 */
