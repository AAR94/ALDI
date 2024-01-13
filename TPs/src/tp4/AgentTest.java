package tp4;

import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentTest extends Agent{
	public static final String DEHORS="DEHORS";
	public static final String DEMANDEUR="DEMANDEUR";
	public static final String DEDANS="DEDANS";

	private int id;
	private String etat=DEHORS;
	private int h=0;
	public  int last=0;
	private volatile int acquerir=0;
	ArrayList<String> attendu=new ArrayList<String>(),
	                  differe=new ArrayList<String>(),
	                  r_i=new ArrayList<String>();
	
	public void setup(){
		System.out.println("Je suis l'agent : "+getLocalName());
		
		Object[] args=getArguments();
		if(args!=null) {
			id=Integer.parseInt(args[0].toString());
			for(int i=0;i<args.length;i++)
				if(!(getLocalName().equals(args[i].toString())))
					r_i.add(args[i].toString());
			
		}
		SequentialBehaviour comport=new SequentialBehaviour();
		comport.addSubBehaviour(new Dehors());
		comport.addSubBehaviour(new Demandeur());
		comport.addSubBehaviour(new Dedans());
		comport.addSubBehaviour(new Liberer());
		addBehaviour(new ConsultationBoite());
		addBehaviour(comport);
	}
	
	public class Dehors extends Behaviour{

		@Override
		public void action() {
			System.out.println("Agent "+id+" est en Dehors");	
			acquerir=0;
		}

		@Override
		public boolean done() {
			return (int)Math.round(Math.random())==1;
		}
		
	}
	
	public class Demandeur extends Behaviour{

		/*public Demandeur() {
			etat=DEMANDEUR;
			h++;
			last=h;
			attendu=cloneList(r_i);
		}*/
		@Override
		public void action() {
			if(acquerir==0) {
				etat=DEMANDEUR;
				h++;
				last=h;
				attendu=cloneList(r_i);
				ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
				msg.setContent("requete#"+last+"#"+id);
				for(int i=0;i<r_i.size();i++) {
					msg.addReceiver(new AID(r_i.get(i),AID.ISLOCALNAME));
					System.out.println(getLocalName()+" j'ai envoyé une requete à l'agent "+r_i.get(i));
				}
				send(msg);
				acquerir=1;

			}
			
		}

		@Override
		public boolean done() {			
			return attendu.isEmpty();
		}
		
	}
	
	public class Dedans extends OneShotBehaviour{

		@Override
		public void action() {
			 etat=DEDANS;
			 for(int i=0;i<5;i++)
				 System.out.println("++++++++++++ Agent "+getLocalName()+" je suis dedans");
		}
		
	}
	
	public class Liberer extends OneShotBehaviour{

		@Override
		public void action() {
			System.out.println("------ Agent "+getLocalName()+" je vais libérer la SC");
			etat=DEHORS;
			ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
			msg.setContent("permission#"+id);
			for(int i=0;i<differe.size();i++) {
				msg.addReceiver(new AID(differe.get(i),AID.ISLOCALNAME));
				System.out.println(getLocalName()+" j'ai envoyé une requete à l'agent "+differe.get(i));
			}
			send(msg);
			differe=new ArrayList<String>();
			
			SequentialBehaviour comport=new SequentialBehaviour();
			comport.addSubBehaviour(new Dehors());
			comport.addSubBehaviour(new Demandeur());
			comport.addSubBehaviour(new Dedans());
			comport.addSubBehaviour(new Liberer());
			addBehaviour(comport);
		}
		
	}
	

	
	
	
	public class ConsultationBoite extends CyclicBehaviour{

		@Override   
		public void action() {
			//reception de requete(k,j)
			ACLMessage msgRecu=receive();
			if(msgRecu!=null) {
				System.out.println(getLocalName()+" :message reçu= "+msgRecu.getContent());
				String[] tab_mess=msgRecu.getContent().split("#");
				if(tab_mess[0].equals("requete")) {
					int k=Integer.parseInt(tab_mess[1]);
					int idj=Integer.parseInt(tab_mess[2]);
					h=Math.max(h, k);
					boolean priorite=(etat.equals(DEDANS) ||
							 (etat.equals(DEMANDEUR) && ((last<k) || (last==k && id<idj))));
					if(priorite) {
						System.out.println(getLocalName()+" : je vais différé");
						differe.add(msgRecu.getSender().getLocalName());
					}else {
						System.out.println(getLocalName()+" je vais envoyé une permission");
						ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
						msg.addReceiver(msgRecu.getSender());
						msg.setContent("permission#"+id);
						send(msg);
					}
				}else {
					if(tab_mess[0].equals("permission"))
						attendu.remove(msgRecu.getSender().getLocalName().toString());
				}
					
			}
				
		}
		
	}
	
	public ArrayList<String> cloneList(ArrayList<String> list){
		ArrayList<String> l=new ArrayList<String>();
		for(String a : list)
			l.add(a);
		return l;
	}
	
	
	
	
}
