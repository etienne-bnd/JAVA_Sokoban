package com.sokoban;
import java.awt.Image; // Importation du package Image d'Awt pour manipuler des images
import javax.swing.ImageIcon;

// Classe représentant une zone ou mettre les caisses étendue de la classe Acteur
public class Zone extends Acteur {

    // Constructeur prenant les coordonnées x et y de la zone comme paramètres
    public Zone(int x, int y) {
        super(x, y); // Appel au constructeur de la classe parente Acteur
        initialiserZone(); // Appel à une méthode pour initialiser l'objet zone
    }

    // Méthode privée pour initialiser l'image de la zone
    private void initialiserZone() {
        ImageIcon icone = new ImageIcon("src/resources/zone.png"); // Chargement de l'image de la zone depuis le fichier "zone.png"
        Image image = icone.getImage(); // Obtention de l'objet Image à partir de l'ImageIcon
        setImage(image); // Définition de l'image pour l'objet Zone (méthode de la classe parente Acteur)
    }
}
