package tp2_Behaviour;

public class Test {
	public static void main(String[] args) {
		String[] commande=new String[3];
		String argument="";
		argument=argument+"c1:tp2_Behaviour.Calculateur";
		//argument=argument+";";
		//argument=argument+"c2:tp2_Behaviour.Calculateur";
		commande[0]="-cp";
		commande[1]="jade.boot";
		commande[2]=argument;
		jade.Boot.main(commande);
		
	}
}
