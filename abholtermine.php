<?php
$wd = "/var/www/html/erprobe/altpapier";
$zd = "/var/www/html/erprobe/altpapier";
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
