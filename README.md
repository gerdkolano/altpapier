pushd /var/www/html/erprobe/altpapier

echo '*.class' > .gitignore
git config --global user.email "gerdkolano@wp.pl"
git config --global user.name gerdkolano
git config --global credential.helper 'cache --timeout=360000'

## Im Browser:
## https://github.com/gerdkolano/
## Sign in
## gerdkolano g körperteil
## New
## Altpapier # Repository name
## Abfuhrtermine der Wertstoffe # Description

git init
git add .
git commit -m "erstes commit"
git remote add origin https://github.com/gerdkolano/altpapier.git
git add .
git commit -m "README.md 3"
