package tp6;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] commande=new String[3];
		String argument="";
		argument+="Robot1:tp6.Robot(1,3,3,0,0,Est,Robot1,Robot2,Robot3)";
		argument+=";";
		argument+="Robot2:tp6.Robot(2,3,3,0,1,Sud,Robot1,Robot2,Robot3)";
		argument+=";";
		argument+="Robot3:tp6.Robot(3,3,3,1,1,Nord,Robot1,Robot2,Robot3)";
		commande[0]="-cp";
		commande[1]="jade.boot";
		commande[2]=argument;
		jade.Boot.main(commande);
	}

}
