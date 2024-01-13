package tp2_TickerBehaviour;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class Calculateur extends Agent{
	public void setup() {
		System.out.println("Agent : "+getLocalName());
		addBehaviour(new Addition(this,5000));
	}
	public class Addition extends TickerBehaviour{
		int a,b,c;
		public Addition(Agent a,int duree) {
			super(a,duree);
		}
		public void onTick() {
			a=(int)(Math.random()*100);
			b=(int)(Math.random()*100);
			c=a+b;
			System.out.println("Agent "+getLocalName()+" :  j'ai calculé :"+a+"+"+b+"="+c);
		}
		
	}
}
