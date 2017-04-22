// hanno@zoe:~$ pushd /home/hanno/erprobe/java
// hanno@zoe:~/erprobe/java$ javac altpapier/Altpapier.java && java altpapier.Altpapier > /daten/srv/www/htdocs/erprobe/altpapier/abholtermine.html
// hanno@zoe:~/erprobe/java$ javac altpapier/Altpapier.java 
// hanno@zoe:~/erprobe/java$ java  altpapier.Altpapier > /daten/srv/www/htdocs/erprobe/altpapier/abholtermine.html

package altpapier;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.text.DateFormatSymbols;
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
    GregorianCalendar jetzt = new GregorianCalendar(); // auf null Uhr stellen
    GregorianCalendar heute, starttag;
    heute = new GregorianCalendar( jetzt.get(GregorianCalendar.YEAR), jetzt.get(GregorianCalendar.MONTH), jetzt.get(GregorianCalendar.DAY_OF_MONTH)); // auf null Uhr stellen
//  starttag = new GregorianCalendar( 2015, 0, 1); // auf null Uhr stellen
    starttag = new GregorianCalendar( jetzt.get(GregorianCalendar.YEAR), jetzt.get(GregorianCalendar.MONTH), 1); // auf null Uhr stellen
    /*
    heute.set(GregorianCalendar.HOUR, 0);        // 12-Stunden-Uhr
    heute.set(GregorianCalendar.HOUR_OF_DAY, 0); // 24-Stunden-Uhr
    heute.set(GregorianCalendar.MINUTE, 0);
    heute.set(GregorianCalendar.SECOND, 0);
    heute.set(GregorianCalendar.MILLISECOND, 0);
*/
    Holtermine holtermine = new Holtermine(starttag, heute);

    GregorianCalendar letzterTag = new GregorianCalendar( starttag.get(GregorianCalendar.YEAR)+1, starttag.get(GregorianCalendar.MONTH),  1);
    //                                                                                                         Jahr Monat Tag
    //                         Abstand in Wochen, Papier                                                            0-11     
    holtermine.fügeAbholtermineEinerFirmaHinzu(2, false, "Bio-Tonne       "         , new GregorianCalendar(2014, 1-1,  7), letzterTag);
    holtermine.fügeAbholtermineEinerFirmaHinzu(2, false, "Graue Tonne     "         , new GregorianCalendar(2014, 1-1,  1), letzterTag);
    holtermine.fügeAbholtermineEinerFirmaHinzu(2, false, "Gelber Sack     "         , new GregorianCalendar(2014, 1-1,  2), letzterTag);
    //ltermine.fügeAbholtermineEinerFirmaHinzu(2, false, "Gelber Sack     "         , new GregorianCalendar(2014, 1-1,  9), letzterTag);
    holtermine.fügeAbholtermineEinerFirmaHinzu(4, true , "L22 28b Berlin Recycling" , new GregorianCalendar(2014, 1-1, 22), letzterTag); // Toscano Klinkhammer
    holtermine.fügeAbholtermineEinerFirmaHinzu(4, true , "L19 19a 24b 28 29 Veolia" , new GregorianCalendar(2015, 1-1, 13), letzterTag); // Mond Schostak Gerhard Kauter Thies
    //ltermine.fügeAbholtermineEinerFirmaHinzu(4, true , "L29 Bartscherer RTB"      , new GregorianCalendar(2014, 1-1,  9), letzterTag);
    //ltermine.fügeAbholtermineEinerFirmaHinzu(4, true , "L29 Bartscherer RTB"      , new GregorianCalendar(2014, 1-1, 23), letzterTag);
    holtermine.fügeAbholtermineEinerFirmaHinzu(4, true , "L29 Bartscherer RTB"      , new GregorianCalendar(2014, 1-1, 30), letzterTag);
    holtermine.fügeAbholtermineEinerFirmaHinzu(4, true , "L21 23b Alba Pappy"       , new GregorianCalendar(2014, 1-1,  8), letzterTag); // Arndt, Löw 23b
    holtermine.fügeAbholtermineEinerFirmaHinzu(4, true , "L24 27 Alba"              , new GregorianCalendar(2014, 1-1,  8), letzterTag); // Grupe, Löw 27vorn
    holtermine.fügeAbholtermineEinerFirmaHinzu(4, true , "Waldblick"                , new GregorianCalendar(2014, 1-1, 24), letzterTag);
    holtermine.fügeAbholtermineEinerFirmaHinzu(4, true , "Goltz"                    , new GregorianCalendar(2015, 8-1, 19), letzterTag);
    // Bartscherer war am 3.März 2016 da
    // Bartscherer war am 20.August 2015 nicht da
    //ltermine.fügeAbholtermineEinerFirmaHinzu(4, "Bartscherer RTB "    , new GregorianCalendar(2014, 1-1,  9), letzterTag);

    holtermine.zeigeMonatstabellen( letzterTag);

    System.out.println("Das 21ste Jahrhundert ist am \"" + today + "\" "
        + (diff / (1000 * 60 * 60 * 24)) + " Tage alt.");

    holtermine.zeigeAbholtermine();

    System.out.println("Das 21ste Jahrhundert ist am \"" + today + "\" "
        + (diff / (1000 * 60 * 60 * 24)) + " Tage alt.");
    System.out.println("<br />");

    System.out.println("Alba<br />");
    System.out.println("wget -O- 'http://trenntstadt-berlin.de/api-abfuhr.php?adrkey=6973669&amp;step=2' | perl -pne 's/([{},])/$1\\n/g'<br />");
    System.out.println("Veolia Tour 2<br />");
    System.out.println("wget -O- 'http://www.veolia-umweltservice.de/assets/Downloads/RegionalerService/Leerungstermine-2016-Blaue-Tonne-Berlin.pdf'<br />");

  }
}

class EinTermin implements Comparable<EinTermin> {
  private boolean papierTag;
  private String firma;
  private GregorianCalendar gZeitpunkt;
  //private long zeitpunkt;

  EinTermin( boolean papierTag, String firma, GregorianCalendar gZeitpunkt) {
    this.papierTag  = papierTag;
    this.firma      = firma;
    this.gZeitpunkt = gZeitpunkt;
  }

  public boolean getPapierTag() {
    return this.papierTag;
  }

  public GregorianCalendar getGregorianCalEnder() {
    return gZeitpunkt;
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
        this.gZeitpunkt.get(GregorianCalendar.DAY_OF_WEEK),
        tagesName[this.gZeitpunkt.get(GregorianCalendar.DAY_OF_WEEK)],
        this.gZeitpunkt.get(GregorianCalendar.YEAR),
        1 + this.gZeitpunkt.get(GregorianCalendar.MONTH),
        this.gZeitpunkt.get(GregorianCalendar.DAY_OF_MONTH),
        firma
    );
  }
}

class HTML {
  // <!DOCTYPE html>
  static public String head = "<!DOCTYPE html><html><head>";
  static public String title = "<title>Altpapier</title>";
  static public String favicon = "<link rel=\"shortcut icon\" href=\"favicon.ico\" type=\"image/x-icon\"/>";
  static public String body = "</head><body>";
  
  static public String utf_8 = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">";
  static public String tdAnfangspan = "<td colspan=\"7\" align=\"center\" >";
  static public String tdAnfang = "<td>", tdEnde = "</td>";
  static public String trAnfang = "<tr>", trEnde = "</tr>";
  static public String tableAnfang = "<table style=\"text-align: right\" border=\"2\">", tableEnde = "</table>";
}

class Holtermine {
  private List<EinTermin> abholterminliste;
  private GregorianCalendar abStart, nunHeute;
  private Locale locale = new Locale("de");
  private String[]wochentagsname;

  Holtermine() {
    abholterminliste = new ArrayList<EinTermin>();
  }

  Holtermine(GregorianCalendar abStart, GregorianCalendar nunHeute) {
    this();
    this.abStart = abStart;
    this.nunHeute = nunHeute;
    this.locale = new Locale("de");
    this.wochentagsname = new DateFormatSymbols(locale).getShortWeekdays();
  }

  int marke = 0;
  String formatFirma_Alltag     = "%-8s ";   // %-x.ys - linksbündig x breite y Feldbreite 
  String formatFirma_Heute      =  "<strong style=\"background-color:cyan\">"   + formatFirma_Alltag + "</strong>";
  String formatNummer_Alltag    =  "<span style=\"background-color:yellow\"   >%2d %s</span>";
  String formatNummer_Papiertag =  "<span style=\"background-color:blue\"     >%2d %s</span>";
  String formatNummer_Sonntag   =  "<span style=\"background-color:orangered\">%2d %s</span>";
  String formatNummer_Heute     =  "<span style=\"background-color:lightblue\">%2d %s</span>";

  /**
   * Schiebe eventuell "marke" vorwärts und liefere die Tagesnummer oder den Firmennamen.
   *
   * @param papierTag
   * @param laufenderTag
   * @return
   */
  String nächsterTagOderFirma(GregorianCalendar laufenderTag) {
    String formatFirma, formatNummer;
    //EinTermin suchMarke = new EinTermin( false, "", laufenderTag);
    if (laufenderTag.compareTo( nunHeute) == 0) {
      formatNummer = formatNummer_Heute;
      formatFirma = formatFirma_Heute;
    } else {
        if (laufenderTag.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY) {
           formatNummer = formatNummer_Sonntag;
	} else {
           formatNummer = formatNummer_Alltag;
	}
      formatFirma = formatFirma_Alltag;
    }
    String erg = "";
    boolean einPapierTag = false;
    while (marke < abholterminliste.size()) {
      EinTermin heuMarke = abholterminliste.get(marke);
      GregorianCalendar heuTag = heuMarke.getGregorianCalEnder();
      //int vergleichsergebnis = heuMarke.compareTo(suchMarke);
      int vergleichsergebnis = heuTag.compareTo(laufenderTag);
      if (vergleichsergebnis < 0)
        marke++;
      if (vergleichsergebnis == 0) {
        einPapierTag = heuMarke.getPapierTag();
        erg += String.format(formatFirma, abholterminliste.get(marke).firma());
	marke++;
	continue;
      }
      String tagesname = wochentagsname[laufenderTag.get(GregorianCalendar.DAY_OF_WEEK)];
      if (vergleichsergebnis > 0) {
        if (einPapierTag) {
          formatNummer = formatNummer_Papiertag;
        } else {
        }
        erg += String.format(formatNummer, laufenderTag.get(GregorianCalendar.DAY_OF_MONTH), tagesname);
	return erg;
      }
    }
    //return String.format(formatNummer, laufenderTag.get(GregorianCalendar.DAY_OF_MONTH));
    erg += String.format(formatNummer, laufenderTag.get(GregorianCalendar.DAY_OF_MONTH), "xx");
    return erg;
  }

  public void fügeAbholtermineEinerFirmaHinzu(int intervall, boolean einPapierTag, String firma,
                                              GregorianCalendar laufenderMonat, GregorianCalendar Ende) {
    GregorianCalendar zeitpunkt;
    for (;
         laufenderMonat.compareTo(Ende) < 0;
         laufenderMonat.add(GregorianCalendar.DAY_OF_MONTH, intervall * 7)) {
      GregorianCalendar neuerTermin = new GregorianCalendar(
          laufenderMonat.get(GregorianCalendar.YEAR),
          laufenderMonat.get(GregorianCalendar.MONTH),
          laufenderMonat.get(GregorianCalendar.DAY_OF_MONTH)
      );
      if (neuerTermin.compareTo(abStart) >= 0)
        zurListe(new EinTermin( einPapierTag, firma, neuerTermin));
    }
  }

  public void zurListe(EinTermin einTermin) {
    //System.out.println(einTermin.toString());
    abholterminliste.add(einTermin);
    //zeigeAbholtermine();System.out.println();
  }

  public void zeigeAbholtermine() {
    Collections.sort(abholterminliste);
    System.out.println( "<pre>");
    for (EinTermin einTermin : abholterminliste) {
      if (abStart.compareTo(einTermin.getGregorianCalEnder()) == 0) {
	      System.out.println( new EinTermin( false, "######################### heute", abStart).toString());
      }
      System.out.println(einTermin.toString());
    }
    System.out.println( "</pre>\n");
  }

  String[] monatsName = {
      "Januar", "Februar", "M&auml;rz", "April",
      "Mai", "Juni", "Juli", "August",
      "September", "Oktober", "November", "Dezember"};

  void zeigeMonatstabellen( GregorianCalendar allerletzterTag) {
    Collections.sort(abholterminliste);
    GregorianCalendar laufenderMonat = new GregorianCalendar(
        abStart.get(GregorianCalendar.YEAR),
        abStart.get(GregorianCalendar.MONTH),
        1 // abStart.get(GregorianCalendar.DAY_OF_MONTH)
    );
    String erg = "";
    erg += HTML.head    + "\n";
    erg += HTML.utf_8   + "\n";
    erg += HTML.title   + "\n";
    erg += HTML.favicon + "\n";
    erg += HTML.body    + "\n";
    erg += "<img src=\"favicon.ico\" />";
    erg += "\n";
    erg += HTML.tableAnfang;
    erg += "\n";
    for (
        ;
        laufenderMonat.compareTo(allerletzterTag) < 0;
        laufenderMonat.add(GregorianCalendar.MONTH, 1)) {
      int rest;
      GregorianCalendar ultimo = new GregorianCalendar(
          laufenderMonat.get(GregorianCalendar.YEAR),
          laufenderMonat.get(GregorianCalendar.MONTH),
          laufenderMonat.get(GregorianCalendar.DAY_OF_MONTH));
      ultimo.add(GregorianCalendar.MONTH, 1);

      GregorianCalendar laufenderTag = new GregorianCalendar(
          laufenderMonat.get(GregorianCalendar.YEAR),
          laufenderMonat.get(GregorianCalendar.MONTH),
          laufenderMonat.get(GregorianCalendar.DAY_OF_MONTH));

      erg += HTML.trAnfang;
      erg += HTML.tdAnfangspan;
      erg += monatsName[laufenderTag.get(GregorianCalendar.MONTH)]
          + " " + laufenderTag.get(GregorianCalendar.YEAR)
          + HTML.tdEnde
          + HTML.trEnde
          + "\n";

      rest = (5 + laufenderTag.get(GregorianCalendar.DAY_OF_WEEK)) % 7;
      erg += HTML.trAnfang;
      for (int ii = rest; ii > 0; ii--) { // leere Kästchen vor Monatsbeginn
        erg += HTML.tdAnfang;
        erg += String.format(formatFirma_Alltag, "..");
        erg += HTML.tdEnde;
      }
      for (;                             // gefüllte Kästchen im Inneren des Monats
           laufenderTag.compareTo(ultimo) < 0;
           laufenderTag.add(GregorianCalendar.DAY_OF_MONTH, 1)) {
        erg += HTML.tdAnfang;
        erg += this.nächsterTagOderFirma(laufenderTag);
        erg += HTML.tdEnde;
        if (laufenderTag.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY) {
          erg += HTML.trEnde;
          erg += "\n";
          erg += HTML.trAnfang;
        }
      }
      rest = (9 - laufenderTag.get(GregorianCalendar.DAY_OF_WEEK)) % 7;
      for (int ii = rest; ii > 0; ii--) { // leere Kästchen nach Monatsende 
        erg += HTML.tdAnfang;
        erg += String.format(formatFirma_Alltag, "..");
        erg += HTML.tdEnde;
      }
      erg += HTML.trEnde;
      if (rest > 0) erg += "\n";
    }
    erg += HTML.tableEnde;
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

wget -O- 'http://trenntstadt-berlin.de/api-abfuhr.php?adrkey=6973669&step=2' | perl -pne 's/([{},])/$1\n/g'

*/
