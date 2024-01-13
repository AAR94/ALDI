package tp5;

import jade.core.Agent;

public class Robot extends Agent{
	public static final String NORD="Nord";
	public static final String EST="Est";
	public static final String SUD="Sud";
	public static final String OUEST="Ouest";

	int h,l;
	int x,y;
	String direction;
	int nbDeplacement;
	
	public void setup() {
		System.out.println("Je suis le robot :"+getLocalName());
		Object[] args=getArguments();
		if(args!=null) {
			h=Integer.parseInt(args[0].toString());
			l=Integer.parseInt(args[1].toString());
			x=Integer.parseInt(args[2].toString());
			y=Integer.parseInt(args[3].toString());
			direction=args[4].toString();
			nbDeplacement=Integer.parseInt(args[5].toString());
			demarrer();
		}

	}
	public void tournerDroite() {
		if(direction.equals(NORD))
			direction=EST;
		else if(direction.equals(EST))
			direction=SUD;
		else if(direction.equals(SUD))
			direction=OUEST;
		else if(direction.equals(OUEST))
			direction=NORD;
	}
	
	public void deplacerEst() {
		while(!direction.equals(EST)) 
			tournerDroite();
		if(x<l)
			x++;
		else
			System.out.println("Limite de la zone atteinte");
			
		
	}
	
	public void deplacerOuest() {
		while(!direction.equals(OUEST)) 
			tournerDroite();
		if(x>0)
			x--;
		else
			System.out.println("Limite de la zone atteinte");
	}
	
	public void deplacerNord() {
		while(!direction.equals(NORD)) 
			tournerDroite();
		if(y<h)
			y++;
		else
			System.out.println("Limite de la zone atteinte");
	}
	
	public void deplacerSud() {
		while(!direction.equals(SUD)) 
			tournerDroite();
		if(y>0)
			y--;
		else
			System.out.println("Limite de la zone atteinte");
	}
	
	public void demarrer() {
		afficheInfo();
		int i=0;
		while(i<nbDeplacement) {
			int d=(int)(Math.random()*4);
			if(d==0) 
				deplacerEst();
			else if(d==1) 
				deplacerSud();
			else if(d==2) 
				deplacerOuest();
			else if(d==3) 
				deplacerNord();
			i++;
			afficheInfo();
		}
		stop();
	}
	
	public void stop() {
		System.out.println("Arrêt de robot "+getLocalName());
	} 
	
	public void afficheInfo() {
		System.out.println("Robot "+getLocalName()+" je suis à la position ("+x+","+y+") direction="+direction);
	}
	
}
