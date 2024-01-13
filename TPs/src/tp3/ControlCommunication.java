package tp3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControlCommunication implements Initializable{
	@FXML
	static Button envoyer_Button;
	@FXML
	static TextField messageEnvoyer_TextF;
	@FXML
	static TextField nomAgentDest_TextF;
	@FXML
	static TextArea messageEnvRecus_TextA;
	
	static AgentTest agent=new AgentTest();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//initActions();
		
	}
	
	
	public static void initActions(){
		envoyer_Button.setOnAction(e->{
			//agent.emission("c2", agent.msg);
		});
	}

}
