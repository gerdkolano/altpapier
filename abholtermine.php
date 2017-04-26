<?php
$host = trim( `uname -n`);
if ($host == "fadi" or $host == "zoe" or $host == "gerd.dyndns.za.net") {
  $wd = "/daten/srv/www/htdocs/erprobe/altpapier";
  $zd = "/daten/srv/www/htdocs/erprobe/altpapier";
} else {
  if ($host == "franzimint") {
    $wd = "/var/www/html/erprobe/altpapier";
    $zd = "/var/www/html/erprobe/altpapier";
  } else {
    echo "\$host == \"$host\"<br />\n";
    exit( 1);
  }
}
echo "\$host == \"$host\" \$wd == \"$wd\"\n";
system( "java -classpath $wd altpapier.Altpapier");
?>
<p>
<?php
echo ( "java -classpath $wd altpapier.Altpapier  > $zd/abholtermine.html");
?>
<p>
<?php
echo ( "javac $wd/altpapier/Altpapier.java && java -classpath $wd altpapier.Altpapier  > $zd/abholtermine.html");
?>

<p>
<?php
echo ( "vim $wd/altpapier/Altpapier.java<br />\n");
echo ( "vim /zoe$zd/abholtermine.php<br />\n");
echo ( "vim $zd/abholtermine.php<br />\n");
?>
