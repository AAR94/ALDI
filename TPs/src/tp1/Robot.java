package tp1;

import jade.core.Agent;

public class Robot extends Agent {
	private int x;
	private int y;
	private String direction;
	
	public Robot() {
		
	}
	public Robot(int x,int y,String d) {
		this.x=x;
		this.y=y;
		direction=d;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String toString() {
		return "x="+x+",y="+y+",direction="+direction+"\n--------------------------------\n";
	}
	
	protected void setup() {
		 System.out.println("Je suis le Robot "+getAID().getLocalName());
		  System.out.println("Mes arguments sont :");
		  Object[] args=getArguments();
		  Robot robot=new Robot();
		  if(args!=null) 
			  for(int i=0;i<args.length;i++) 
				  if(i==0)
					  robot.setX(Integer.parseInt((String)args[i]));
				  else if(i==1)
					  robot.setY(Integer.parseInt((String)args[i]));
				  else
					  robot.setDirection((String)args[i]);	
		  System.out.println(robot.toString());
			  
	}
	
}
