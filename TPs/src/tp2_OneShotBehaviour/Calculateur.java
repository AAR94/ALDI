package tp2_OneShotBehaviour;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class Calculateur extends Agent{
	public void setup() {
		System.out.println("Agent : "+getLocalName());
		addBehaviour(new Addition());
	}
	public class Addition extends OneShotBehaviour{
		int a,b,c;
		public void action() {
			a=(int)(Math.random()*100);
			b=(int)(Math.random()*100);
			c=a+b;
			System.out.println("Agent"+getLocalName()+" :  j'ai calculé :"+a+"+"+b+"="+c);
		}
	}
}
