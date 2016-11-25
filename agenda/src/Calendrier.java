import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * @author Jérémy Salvador & Nina Williams
 *
 */
public class Calendrier extends JApplet {
	
	public void init(){
		
	//Création de l'objet associé à la classe GregorianCalendar
	GregorianCalendar cal = new GregorianCalendar();
	
	String nomsJours[] = { "Lu", "Ma", "Me", "Je", "Ve", "Sa", "Di" };
	
	//Initialisation du panneau
	JPanel panCal = new JPanel(new GridLayout(7,7));
	 
	//Initialisation de l'etiquette
	JLabel[][] calendrier = new JLabel[7][7];
	
	//Etablissement des etiquettes
	for (int i = 0; i < 7; i++) {
		for (int j = 0; j < 7; j++) {
			calendrier[i][j] = new JLabel("",SwingConstants.CENTER);
		}
	}
	
	//Etablissement du conteneur et de sa localisation
	Container contenu = getContentPane();
	contenu.add(panCal, BorderLayout.CENTER);
	contenu.add(panCal, BorderLayout.NORTH);
	
	//Ajout des etiquettes dans le calendrier
	for (int i = 0; i < 7; i++) {
		for (int j = 0; j < 7; j++) {
			panCal.add(calendrier[i][j]);
		}
	}
	
	//Afficher le nom de chaque jour d'une semaine
	for (int i = 0; i < 7; i++) {
		calendrier[0][i].setText(nomsJours[i]);
	}
	
	//Obtenir le premier jour de la semaine
	int annee = cal.get(GregorianCalendar.YEAR);
	int mois = cal.get(GregorianCalendar.MONTH);
	int premierJour = cal.getActualMinimum(GregorianCalendar.DAY_OF_MONTH);
	premierJour = new GregorianCalendar(annee, mois, 0).get(Calendar.DAY_OF_WEEK)-1;
	
	//Obtenir et afficher l'heure exacte actuellement
	Date heure = new Date();
	SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
	Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
	JLabel  Heure = new JLabel();  
	Heure.setBorder(border);
	Heure = new JLabel("Heure :  [ "+formater.format(heure)+" ]", SwingConstants.CENTER);
	Heure.setBorder(border);
	
	
	//Affichage de l'etiquette Heure
	JPanel panHeure = new JPanel(new GridLayout(1,1));
	Container contenuHeure = getContentPane();
	contenuHeure.add(panHeure);
	panHeure.add(Heure);
	
	//Connaitre le nombre de jours dans le mois avec le dernier jour
	int nbDeJoursMois = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	
	//Obtenir le jour du mois actuel
	int jourActuel = cal.get(GregorianCalendar.DAY_OF_MONTH);
	
	//Affichage des jours de la premiere semaine et le jour actuel avec "*"
	int jourMois = 1;
	
	for (int j = premierJour; j < 7; j++) {
		if(jourMois == jourActuel){
			calendrier[1][j].setText(jourMois+"*");
		}
		else{
		calendrier[1][j].setText(jourMois+" ");
		}
		jourMois++;
	}
	
	//Condition annee bissextile
	if(annee%4 ==0 && mois == 1)
		nbDeJoursMois = 29;

	//Affichage jours restant et le jour actuel avec "*"
	for (int i = 2; i < 7; i++) {
		for (int j = 0; j < 7; j++) {
			if(jourMois == jourActuel){
				calendrier[i][j].setText(jourMois+"*");
			}
			else if(jourMois <= nbDeJoursMois){
				calendrier[i][j].setText(jourMois+" ");
			}				
			jourMois++;
		}
		
	}
	}
}
