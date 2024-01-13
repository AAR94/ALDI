package tp4;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args){
		String[] commande=new String[3];
		String argument="";
		argument+="a1:tp4.AgentTest1(1,a1,a2,a3)";
		argument+=";";
		argument+="a2:tp4.AgentTest1(2,a1,a2,a3)";
		argument+=";";
		argument+="a3:tp4.AgentTest1(3,a1,a2,a3)";
		commande[0]="-cp";
		commande[1]="jade.boot";
		commande[2]=argument;
		jade.Boot.main(commande);
	}
}
