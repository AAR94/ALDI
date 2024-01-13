package tp3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentTest extends Agent{
	
	public static String currentAgentSource="c1";
	public static String currentAgentDestination="c2";
	public static String succes_msg="J'ai recu votre msg !";
	public static String last_msg="";

	public String name="";
	public static int inc=1;	
	public AgentTest(){
		name="c"+inc++;
	}
	
	protected void setup(){		
		Fenetre.send_JButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentAgentSource=Fenetre.agentSource_JComboB.getSelectedItem().toString();
				currentAgentDestination=Fenetre.agentDestination_JComboB.getSelectedItem().toString();
				
				if(name.equals(currentAgentSource)){
					last_msg=Fenetre.msgText_JTextF.getText();
					emission(currentAgentDestination ,last_msg);
				}
			}
		});

		addBehaviour(new Receive());	

	}
		
	public class Receive extends CyclicBehaviour{
		public void action() {
			
			/*this condition to prevent an infinte cycle */
			if(last_msg.equals(succes_msg))
			  reception();
			else
			  receptionAvecReponse(succes_msg);
		}
	}
	
	public void emission(String nameAgent , String msg){
		ACLMessage msgEnvoi=new ACLMessage(ACLMessage.INFORM);
		msgEnvoi.addReceiver(new AID(nameAgent,AID.ISLOCALNAME));
		msgEnvoi.setContent(Fenetre.msgText_JTextF.getText());
		send(msgEnvoi);
	}
	
	public void reception(){
		ACLMessage msgRecu=receive();
		if(msgRecu!=null)
		    Fenetre.conversation_JTextArea.append(msgRecu.getSender().getLocalName()+" > "+msgRecu.getContent()+"\n");

		
		
	}
	
	public void receptionAvecReponse(String reponse){
		ACLMessage msgRecu=receive();
		if(msgRecu!=null){
			String messContenu=msgRecu.getContent();
		    Fenetre.conversation_JTextArea.append(msgRecu.getSender().getLocalName()+" > "+messContenu+"\n");
			ACLMessage msgReponse=new ACLMessage(ACLMessage.INFORM);
			msgReponse.addReceiver(msgRecu.getSender());
			msgReponse.setContent(reponse);
			last_msg=reponse;
			send(msgReponse);
			
		}		
		
	}
	
	
	
	
}
