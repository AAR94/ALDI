package tp5;

public class TestV2 {
	
	//test correspondance à la partie 4.3
	
	public static void main(String[] args) {
		String[] commande=new String[3];
		String argument="";
		argument=argument+"Robucar:tp5.RobotV2(20,25,7,10,Est,6,10,6,11,5,11)";
		argument=argument+";";
		argument=argument+"Atlas:tp5.RobotV2(20,25,8,11,Est,7,11,6,11,5,11,4,11,4,12)";
		commande[0]="-cp";
		commande[1]="jade.boot";
		commande[2]=argument;
		jade.Boot.main(commande);

	}
}
