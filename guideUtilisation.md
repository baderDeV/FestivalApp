# Comment tester le fonctionnment du projet

## Pré-requis
1 - vous devez avoir un ordianteur et un téléphone portable dont l'OS est Android
2 - L'ordinateur et le téléphone devrait utiliser le même WIFI (les deux seront dans un réseau locale)

## Action à faire sur le PC :

1 - installer MySQL (n'oublier surtout pas le nom de user et le mot de passe)
2 - aller à la ligne de commande (ou bien utiliser la version graphique de MySQL) et créer le schéma de la BD et insére des données 
(les scripts et les données sont dans le fichier BDschemeToCreateOnPC/schema.txt)
3 - désactiver les firwall qui sont appliquées sur MySQL 

## Action à faire côté Application mobile
1 - Dans la classe ConnectionDatabase.java, modifier les variables varibales (url (uniquement l'IP où se trouve la BD) + username(de la BD) + password(de la BD))
2 - clean Project + rebuild project + run

