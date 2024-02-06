package com.sokoban;
// Importation du package Image d'Awt pour manipuler des images
import java.awt.Image;
// Importation de la classe Acteur du même package
import javax.swing.ImageIcon;

// Classe représentant un joueur, étendue de la classe Acteur du package com.zetcode
public class Joueur extends Acteur {

    // Constructeur prenant les coordonnées x et y du joueur comme paramètres
    public Joueur(int x, int y) {
        super(x, y); // Appel au constructeur de la classe parente Acteur
        initialiserJoueur(); // Appel à une méthode pour initialiser l'objet joueur
    }

    // Méthode privée pour initialiser l'image du joueur
    private void initialiserJoueur() {
        ImageIcon icone = new ImageIcon("src/resources/joueurHaut.png"); // Chargement de l'image du joueur depuis le fichier "joueurHaut.png"
        Image image = icone.getImage(); // Obtention de l'objet Image à partir de l'ImageIcon
        setImage(image); // Définition de l'image pour l'objet Joueur (méthode de la classe parente Acteur)
    }

    // Méthode pour déplacer le joueur en fonction des coordonnées fournies
    public void deplacer(int x, int y) {
        // Calcul des nouvelles positions x et y du joueur
        int dx = x() + x;
        int dy = y() + y;
        
        // Mise à jour des positions x et y du joueur
        setX(dx);
        setY(dy);
    }
}
