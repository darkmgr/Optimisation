# Optimisation
Projet d'optimisation stochastique

Utilisation d'algorithme génétique pour faire une selection d'équipes en poules dans un tournoi.

Les critères sont : 1) L'équilibre de niveau des équipes 2) La distance parcourue la plus courte possible.

Individu : 2 poules contenant l'ensemble des équipes, sans doublon
Population : groupe d'individu
Génération : une itération de l'algorithme

https://docs.google.com/document/d/12SjEaztu5xwixqYMH2mxgGNX6M_yuyRAgqwUVrYYMzY/edit

Fonctionnement du programme :

Configuration :

- Path de la Matrice de distance
- Path de la Matrice de temps
- Path du fichier texte de l'ordre de niveau des équipes

-Nombre d'individus dans la population  (int)
-Combien d'individus vont évoluer ? (1 à N) (int)
-Croisement ou mutation (string)
-Nombre de générations (max 500) (int)


-> Population initiale :
    Choix aléatoire de différents individus
    On rempli les poules aléatoirement tant qu'il reste des équipes à placer.
    Critères : pas de doublon, chacune des équipes doit être répartie dans une des deux poules
    
On entre dans la boucle générationnelle :

    1) Evaluation des individus
        On utilise la dominance des individus, différence de niveau entre les deux poules, et la distance totale parcourue pour chacune des poules
        
        Soit X un individu composé de deux poules P1 et P2  : X(P1,P2)
        Soit DN la différence de niveau entre les deux poules
        Soit DT la distance totale parcourue
        
        Pour comparer deux individus, Xa(P1a,P2a,DNa,DTa) et Xb(P1b, P2b, DNb, DTb) un individu domine l'autre SI et SEULEMENT SI sa distance ET son ecart de
        niveau est plus faible.
        
        Xa > Xb <=>   (DNa<DNb) & (DTa<DTb)
        
      Pour leur donner un rang, on note tous les individus qui ne sont pas dominés par des autres et on leur donne le rang 1, on les mets de côté, et on recommence
      avec les individus restants, les non dominés possèdent le rang 2 etc. etc.
        
    2) Selection croisement mutation
    
        Pour la selection on selection les meilleurs individus, la quantitée est laissée en option (de 1 à N) Recommandé N/2 ?
        
        On peut choisir de faire ou un croisement ou une mutation
        Croissement : multi point, on divise un individu en N, et on mélange les parties avec les autres individus.
        Mutation :
        
    
    3) Nouvelle population
    
        On ajoute les nouveaux individus crées, on refait une evaluation des individus (Etape 1) et on ne garde que les N meilleurs (pour eviter une croissance
        exponentielle de la population)
    



Programmation JAVA :

Objets :

Population
    list Individus
    
Individu
    poule Poule1
    poule Poule2
    float DifferenceNiveauPoule
    float DistanceTotale
    
Poule
    float Niveau
    float Distance
    list Equipe

Equipe
    string nom
        
    
