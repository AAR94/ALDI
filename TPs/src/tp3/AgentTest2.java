package tp3;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentTest2 extends Agent{
	
	
	public static String msg="";
	
	protected void setup(){		
		addBehaviour(new recep());

	}	
	public class recep extends CyclicBehaviour{
		public void action() {
			receptionAvecReponse("J'ai recu votre msg !");
			//reception();
		}
	}
		
	public void reception(){
		ACLMessage msgRecu=receive();
		if(msgRecu!=null){
			System.out.println(msgRecu.getContent().toString());
		    //Fenetre.conversation.append(msgRecu.getSender().getLocalName()+" > "+msgRecu.getContent()+"\n");

		}
		
	}
	
	public void receptionAvecReponse(String reponse){
		ACLMessage msgRecu=receive();
		if(msgRecu!=null){
			String messContenu=msgRecu.getContent();
		    //Fenetre.conversation.append(msgRecu.getSender().getLocalName()+" > "+messContenu+"\n");
			ACLMessage msgReponse=new ACLMessage(ACLMessage.INFORM);
			msgReponse.addReceiver(msgRecu.getSender());
			msgReponse.setContent(reponse);
			send(msgReponse);
			
		}		
		
	}
	
	
	

}
