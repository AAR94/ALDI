package tp6;

import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;
import tp5.Point;

public class Robot extends Agent{
	public static final String NORD="Nord";
	public static final String EST="Est";
	public static final String SUD="Sud";
	public static final String OUEST="Ouest";
	
	public static final String DEHORS="DEHORS";
	public static final String DEMANDEUR="DEMANDEUR";
	public static final String DEDANS="DEDANS";

	int h,l;
	int x,y;
	int prochainX,prochainY;
	String direction;
	
	
	int id;
	int ht = 0;
	int last = 0;
	String etat = DEHORS;
	ArrayList<String> attendu = new ArrayList<String>();

	ArrayList<String> RI = new ArrayList<String>();
	ArrayList<String> differe = new ArrayList<String>();
	ArrayList<String> differe_prochain = new ArrayList<String>();

	
																																																																																																																																							
	public void setup() {
		System.out.println("Je suis le robot :"+getLocalName());
		Object[] args=getArguments();
		if(args!=null) {
			h=Integer.parseInt(args[1].toString());
			l=Integer.parseInt(args[2].toString());
			x=Integer.parseInt(args[3].toString());
			y=Integer.parseInt(args[4].toString());
			direction=args[5].toString();
			prochainX=x;
			prochainY=y;
			id = Integer.parseInt(args[0].toString());
			for (int i = args.length-3; i < args.length; i++) {
				if (!(getLocalName().equals(args[i].toString())))
					RI.add(args[i].toString());
			}
			

			SequentialBehaviour comportSeq = new SequentialBehaviour();
			//comportSeq.addSubBehaviour(new dehors());
			comportSeq.addSubBehaviour(new demanderDeplacer());
			comportSeq.addSubBehaviour(new libererCase());
			comportSeq.addSubBehaviour(new deplacer());

			addBehaviour(new consultation());
			addBehaviour(comportSeq);
			
		}

	}
	
	public class demanderDeplacer extends Behaviour {
        int acquerir=0;
		public void action() {
			if (acquerir==0) {
			System.out.println("agent: " + getLocalName() + " je suis demandeur");
			etat = DEMANDEUR;
			ht = ht + 1;
			last = ht;
			for (int i=0; i<RI.size(); i++) {
			attendu.add(RI.get(i));}
			genererPoint();
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContent("requete"+"#"+last+"#"+id+"#"+prochainX+"#"+prochainY);
			for (int i=0; i<RI.size();i++) {
				msg.addReceiver(new AID(RI.get(i),AID.ISLOCALNAME));
				System.out.println(getLocalName()+ " j'ai envoye une requete a Robot "+RI.get(i));
			}
			send(msg);
			acquerir = 1;
			}
		}// action

		public boolean done() {
			if (attendu.size() == 0 )
				return true;
			else return false;

		}// done

	}// demandeur
	
	
	
	public class libererCase extends OneShotBehaviour{

		@Override
		public void action() {
			System.out.println("agent: " + getLocalName() + " je vais liberer l'encienne case ");
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContent("permission"+"#"+id);
			for (int i=0; i<differe.size();i++) {
				System.out.println(getLocalName()+ "je vais envoye une permission differe au " + differe.get(i));
						msg.addReceiver(new AID(differe.get(i),AID.ISLOCALNAME));
			}
			send(msg);
			differe = new ArrayList<String>() ;
			
			
		}
		
	}
	
    public class deplacer extends OneShotBehaviour{

		
		public void action() {
			etat = DEDANS;
			for(int i=0; i<5;i++)
			System.out.println("agent: " + getLocalName() + " je suis dedans");
			
			demarrer();
			
				SequentialBehaviour comportSeq = new SequentialBehaviour();
				comportSeq.addSubBehaviour(new demanderDeplacer());
				comportSeq.addSubBehaviour(new libererCase());
				comportSeq.addSubBehaviour(new deplacer());
				addBehaviour(comportSeq);
			
		
			etat = DEHORS;
			for(String s: differe_prochain)
				differe.add(s);
			differe = new ArrayList<String>() ;

		}
		
	}

	public class consultation extends CyclicBehaviour{

		@Override
		public void action() {
			ACLMessage msgRecu = receive();
			if (msgRecu != null) {
				System.out.println(getLocalName() + ": message recu = " + msgRecu.getContent());
				String [] tab_msg = msgRecu.getContent().split("#");
				if(tab_msg[0].equals("requete")) {
					int k,idj,proX,proY;
					k=Integer.parseInt(tab_msg[1]);
					idj=Integer.parseInt(tab_msg[2]);
					proX=Integer.parseInt(tab_msg[3]);
					proY=Integer.parseInt(tab_msg[4]);
					ht=Math.max(ht, k);
					if(x==proX && y==proY) {
						System.out.println(getLocalName() + ": je vais differer la requete ");
						differe.add(msgRecu.getSender().getLocalName());
					}else if(prochainX==proX && prochainY==proY && (prochainX!=x || prochainY!=y)){
						boolean priorite = ((etat.equals("dedans")) || ((etat.equals("demandeur")) && ((last<k) || ((last==k)&&(id<idj)))));
						
						if(priorite) {
							System.out.println(getLocalName() + ": je vais differer la requete ");
							differe_prochain.add(msgRecu.getSender().getLocalName());
						}
						else {
							System.out.println(getLocalName() + ": je vais envoye une permission ");
							ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
							msg.addReceiver(msgRecu.getSender());
							msg.setContent("permission"+"#"+id);
							send(msg);
						}
					}else {
						System.out.println(getLocalName() + ": je vais envoye une permission a "+idj);
						ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
						msg.addReceiver(msgRecu.getSender());
						msg.setContent("permission"+"#"+id);
						send(msg);
					}
					
				}
				else {
					if(tab_msg[0].equals("permission"))
						attendu.remove(msgRecu.getSender().getLocalName().toString());
				}
			}
			
			
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
		
			if(prochainX<x) 
				deplacerOuest();
			else if(prochainX>x)
				deplacerEst();
			else if(prochainY<y) 
				 deplacerSud();
		    else if(prochainY<y) 
			     deplacerNord();
			
			afficheInfo();
			stop();

		}
	
	public void stop() {
		System.out.println("Arr�t de robot "+getLocalName());
	} 
	
	public void afficheInfo() {
		System.out.println("Robot "+getLocalName()+" je suis � la position ("+x+","+y+") direction="+direction);
	}
	
	public void genererPoint() {
		int random=(int) Math.round(Math.random() *3);
		if(random==0)
			prochainX=x+1;
		else if(random==1)
			prochainX=x-1;
		else if(random==2)
			prochainY=y+1;
		else if(random==3)
			prochainY=y-1;
		
		if(prochainX==h)
			prochainX-=2;
		else if(prochainX==-1)
			prochainX+=2;
		else if(prochainY==l)
			prochainY-=2;
		else if(prochainY==-1)
			prochainY+=2;
		
	}
	
	
	
}