package tp2_WakerBehaviour;

public class Test {
	public static void main(String[] args) {
		String[] commande=new String[3];
		String argument="";
		argument=argument+"c1:tp2_WakerBehaviour.Calculateur";
		//argument=argument+";";
		//argument=argument+"c2:tp2_WakerBehaviour.Calculateur";
		commande[0]="-cp";
		commande[1]="jade.boot";
		commande[2]=argument;
		jade.Boot.main(commande);
		
	}
}
