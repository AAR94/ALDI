package tp5;

public class Test {
	
	/*
	  //test correspondance à la partie 3.1
	   
	  public static void main(String[] args) {
		String[] commande=new String[3];
		String argument="";
		argument=argument+"Robucar:tp5.Robot(20,25,7,10,Est,5)";
		//argument=argument+";";
		//argument=argument+"c2:tp2_Behaviour.Calculateur";
		commande[0]="-cp";
		commande[1]="jade.boot";
		commande[2]=argument;
		jade.Boot.main(commande);
	}
	*/
	
	
	
	//test correspondance à la partie 3.2
	
	public static void main(String[] args) {
		String[] commande=new String[3];
		String argument="";
		argument=argument+"Robucar:tp5.Robot(20,25,7,10,Est,5)";
		argument=argument+";";
		argument=argument+"Atlas:tp5.Robot(20,25,8,11,Ouest,8)";
		commande[0]="-cp";
		commande[1]="jade.boot";
		commande[2]=argument;
		jade.Boot.main(commande);

	}

}
