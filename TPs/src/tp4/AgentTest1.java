package tp4;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class AgentTest1 extends Agent {

	int id;
	int h = 0;
	int last = 0;
	String etat = "dehors";
	ArrayList<String> attendu = new ArrayList<String>();
	ArrayList<String> RI = new ArrayList<String>();
	ArrayList<String> differe = new ArrayList<String>();
	int acquerir = 0;

	protected void setup() {

		System.out.println("je suis l'agent: " + this.getLocalName());

		Object[] args = getArguments();

		if (args != null) {
			id = Integer.parseInt(args[0].toString());
			for (int i = 1; i < args.length; i++) {
				if (!(getLocalName().equals(args[i].toString())))
					RI.add(args[i].toString());
			}
		}

		SequentialBehaviour comportSeq = new SequentialBehaviour();
		comportSeq.addSubBehaviour(new dehors());
		comportSeq.addSubBehaviour(new demandeur());
		comportSeq.addSubBehaviour(new dedans());
		comportSeq.addSubBehaviour(new liberer());

		addBehaviour(new consultation());
		addBehaviour(comportSeq);

	}   

	public class dehors extends Behaviour {
		public void action() {
			System.out.println("agent: " + getLocalName() + "je suis dehors");
			acquerir = 0;

		}// action

		public boolean done() {
			int transition = (int) Math.round(Math.random() * 1);
			if (transition == 0)
				return false;
			else
				return true;

		}// done

	}// dehors

	public class demandeur extends Behaviour {

		public void action() {
			if (acquerir==0) {
			System.out.println("agent: " + getLocalName() + "je suis demandeur");
			etat = "demandeur";
			h = h + 1;
			last = h;
			for (int i=0; i<RI.size(); i++) {
			attendu.add(RI.get(i));}
			
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContent("requete"+"#"+last+"#"+id);
			for (int i=0; i<RI.size();i++) {
				msg.addReceiver(new AID(RI.get(i),AID.ISLOCALNAME));
				System.out.println(getLocalName()+ "j'ai envoye une requete a l'agent "+RI.get(i));
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
	
	public class dedans extends OneShotBehaviour{

		
		public void action() {
			etat = "dedans";
			for(int i=0; i<5;i++)
			System.out.println("agent: " + getLocalName() + "je suis dedans");
			
		}// action
		
	}// dedans
	
	public class liberer extends OneShotBehaviour{

		@Override
		public void action() {
			System.out.println("agent: " + getLocalName() + "je vais liberer la SC");
			etat="dehors";
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContent("permission"+"#"+id);
			for (int i=0; i<differe.size();i++) {
				System.out.println(getLocalName()+ "je vais envoye une permission differe au " + differe.get(i));
						msg.addReceiver(new AID(differe.get(i),AID.ISLOCALNAME));
			}
			send(msg);
			differe = new ArrayList<String>() ;
			
			SequentialBehaviour comportSeq = new SequentialBehaviour();
			comportSeq.addSubBehaviour(new dehors());
			comportSeq.addSubBehaviour(new demandeur());
			comportSeq.addSubBehaviour(new dedans());
			comportSeq.addSubBehaviour(new liberer());

			addBehaviour(comportSeq);
		}// action
		
	}// liberer
	
	public class consultation extends CyclicBehaviour{

		@Override
		public void action() {
			ACLMessage msgRecu = receive();
			if (msgRecu != null) {
				System.out.println(getLocalName() + ": message recu = " + msgRecu.getContent());
				String [] tab_msg = msgRecu.getContent().split("#");
				if(tab_msg[0].equals("requete")) {
					int k,idj;
					k=Integer.parseInt(tab_msg[1]);
					idj=Integer.parseInt(tab_msg[2]);
					h=Math.max(h, k);
					
					boolean priorite = ((etat.equals("dedans")) || ((etat.equals("demandeur")) && ((last<k) || ((last==k)&&(id<idj)))));
					
					if(priorite) {
						System.out.println(getLocalName() + ": je vais differer la requete ");
						differe.add(msgRecu.getSender().getLocalName());
					}
					else {
						System.out.println(getLocalName() + ": je vais envoye une permission");
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

}