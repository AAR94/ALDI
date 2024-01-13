package tp3;

public class Main {

	public static void main(String[] args) {
		new Fenetre();
		System.out.println("Commands :");
		String[] commande=new String[3];
		String argument="";
		argument=argument+"c1:tp3.AgentTest";
		argument=argument+";";
		argument=argument+"c2:tp3.AgentTest";
		argument=argument+";";
		argument=argument+"c3:tp3.AgentTest";
		commande[0]="-cp";
		commande[1]="jade.boot";
		commande[2]=argument;
		jade.Boot.main(commande);
	}

}
