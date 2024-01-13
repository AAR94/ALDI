package tp2_ParrallelBehaviour;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;

public class Calculateur extends Agent{
	public void setup() {
		System.out.println("Agent : "+getLocalName());
		//ParallelBehaviour comport=new ParallelBehaviour(ParallelBehaviour.WHEN_ANY);
		ParallelBehaviour comport=new ParallelBehaviour(ParallelBehaviour.WHEN_ALL);
		comport.addSubBehaviour(new Addition());
		comport.addSubBehaviour(new Soustraction());
		comport.addSubBehaviour(new Produit());
		addBehaviour(comport);
	}
	/* public class Addition extends Behaviour{
		int a,b,c;
		public void action() {
			System.out.println("le 1ère sous-comportement");
			a=(int)(Math.random()*100);
			b=(int)(Math.random()*100);
			c=a+b;
			System.out.println("Agent "+getLocalName()+" :  j'ai calculé :"+a+"+"+b+"="+c);
		}
		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return c==100;
		}
	}
	
	public class Soustraction extends Behaviour{
		int a,b,c;
		public void action() {
			System.out.println("le 2ème sous-comportement");
			a=(int)(Math.random()*100);
			b=(int)(Math.random()*100);
			c=a-b;
			System.out.println("Agent "+getLocalName()+" :  j'ai calculé :"+a+"-"+b+"="+c);
		}
		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return c<0;
		}
	}*/
	
	public class Produit extends OneShotBehaviour{
		int a,b,c;
		public void action() {
			System.out.println("le 3ème sous-comportement");
			a=(int)(Math.random()*100);
			b=(int)(Math.random()*100);
			c=a*b;
			System.out.println("Agent "+getLocalName()+" :  j'ai calculé :"+a+"*"+b+"="+c);
		}

	}
	
	
	//version 2 :
	public class Addition extends CyclicBehaviour{
		int a,b,c;
		public void action() {
			System.out.println("le 1ère sous-comportement");
			a=(int)(Math.random()*100);
			b=(int)(Math.random()*100);
			c=a+b;
			System.out.println("Agent "+getLocalName()+" :  j'ai calculé :"+a+"+"+b+"="+c);
		}
		
	}
	
	public class Soustraction extends CyclicBehaviour{
		int a,b,c;
		public void action() {
			System.out.println("le 2ème sous-comportement");
			a=(int)(Math.random()*100);
			b=(int)(Math.random()*100);
			c=a-b;
			System.out.println("Agent "+getLocalName()+" :  j'ai calculé :"+a+"-"+b+"="+c);
		}
		
	}
	
	
}
