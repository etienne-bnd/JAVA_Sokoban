package com.zetcode;

import java.util.ArrayList;

// Classe représentant les niveaux du jeu Sokoban
public class Niveau {

    // Liste de chaînes de caractères représentant les différents niveaux du jeu
    private static ArrayList<String> niveaux = new ArrayList<String>() {{
        // Niveau 1
        add("####\n" +
            "#  #\n" +
            "#  ###\n" +
            "#  $ #\n" +
            "## #.#\n" +
            "#  @ #\n" +
            "#    #\n" +
            "#  ###\n" +
            "####\n"
        );

        // Niveau 2
        add("####\n" +
            "#  #####\n" +
            "#    @ ##\n" +
            "# . .$  #\n" +
            "##  #$  #\n" +
            " ##   ###\n" +
            "  #####\n"
        );

        // Niveau 3
        add("#####\n" +
        "#   #\n" +
        "#  ###\n" +
        "#  $ #\n" +
        "## #.#\n" +
        "#  @ #\n" +
        "# $ .#\n" +
        "#  ###\n" +
        "#####\n"
        );

        // Niveau 4
        add("######\n" +
        "#   .###\n" +
        "#  #   #\n" +
        "#@$ $  #\n" +
        "#  #.###\n" +
        "# $  #\n" +
        "#### #\n" +
        "# .   #\n" +
        "### $ #\n" +
        "### .#\n" +
        "######\n"
        );

        // Niveau 5
        add("                     \n" +
        "                     \n" +
        "                     \n" +
        "                     \n" +
        "                     \n" +
        "                     \n" +
        "                     \n" +
        "                     \n" +
        "                     \n" 
        
        );

        // Ajoutez d'autres niveaux ici si nécessaire

    }};
    
    // Nombre total de niveaux dans la liste
    public static final int nombreNiveau = niveaux.size();

    // Niveau courant (indice dans la liste)
    private static int niveauCourant = 0;

    // Méthode pour obtenir le niveau actuel sous forme de chaîne de caractères
    public static String getNiveauActuel() {
        if (niveaux.size() > niveauCourant) {
            return niveaux.get(niveauCourant);
        } else {
            // Si on dépasse la taille de la liste, retourner le dernier niveau
            return niveaux.get(niveaux.size() - 1);
        }
    }

    // Méthode pour obtenir l'indice du niveau courant
    public static int getNiveauCourant() {
        return niveauCourant;
    }

    // Méthode pour augmenter le niveau courant
    public static void augmenterNiveau() {
        if (niveauCourant < niveaux.size()) {
            niveauCourant += 1;
        }
    }
    public static void finalNiveau(){
        niveauCourant = niveaux.size() + 1;
    }
}
