package tp2_Behaviour;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class Calculateur extends Agent{
	public void setup() {
		System.out.println("Agent : "+getLocalName());
		//addBehaviour(new Addition());
	}
	public class Addition extends Behaviour{
		int a,b,c;
		public void action() {
			a=(int)(Math.random()*100);
			b=(int)(Math.random()*100);
			c=a+b;
			System.out.println("Agent"+getLocalName()+" :  j'ai calculé :"+a+"+"+b+"="+c);
		}
		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return c==100;
		}
	}
}
