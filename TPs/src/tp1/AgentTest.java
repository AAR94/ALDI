package tp1;

import jade.core.Agent;
public class AgentTest extends Agent{
	/*protected void setup() {
		System.out.println("Je suis l'agent :"+this.getLocalName());
		System.out.println("Je suis l'agent :"+this.getName());
		System.out.println("Je suis l'agent :"+this.getAID());

	}
	*/
	
	 protected void setup(){
	  System.out.println("Je suis l'argent "+getAID().getLocalName());
	  System.out.println("Mes arguments sont :");
	  Object[] args=getArguments();
	  if(args!=null) 
		  for(int i=0;i<args.length;i++) {
			  String val=(String)args[i];
			  System.out.println(val);
		  }
		 
	   
	  
	 }
	 
}
