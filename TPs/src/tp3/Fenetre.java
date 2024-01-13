package tp3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Fenetre extends JFrame{
	List<String> listAgentsName=new ArrayList<String>();
	public static JComboBox<String> agentSource_JComboB=new JComboBox<String>();
	public static JComboBox<String> agentDestination_JComboB=new JComboBox<String>();
	public static JTextField msgText_JTextF=new JTextField();
	public static JTextArea conversation_JTextArea=new JTextArea();
	public static JButton send_JButton=new JButton("Envoyer");

	
	
	public Fenetre(){
		this.setTitle("Communication entre les agents");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,500);
		initComponents();
		this.setVisible(true);
	}
	
	
	
	public void initComponents(){
		JPanel pane=new JPanel(new FlowLayout()),
			   agentSrc_pane=new JPanel(),
			   agentDest_pane=new JPanel(),
			   msgSend_pane=new JPanel(),
			   icon_pane=new JPanel();
		JLabel agentNameSource_JLabel=new JLabel("Nom Agent Source      :");
		JLabel agentNameDestination_JLabel=new JLabel("Nom Agent Destination :");
		JLabel msgSend_JLabel =new JLabel("Message a envoyer     :");
		
		
		agentSrc_pane.add(agentNameSource_JLabel);
		agentSrc_pane.add(agentSource_JComboB);
		pane.add(agentSrc_pane);
		
		agentDest_pane.add(agentNameDestination_JLabel);
		agentDest_pane.add(agentDestination_JComboB);
		pane.add(agentDest_pane);

		msgSend_pane.add(msgSend_JLabel);
		msgSend_pane.add(msgText_JTextF);
		pane.add(msgSend_pane);
		
		pane.add(send_JButton);
		JScrollPane sp=new JScrollPane(conversation_JTextArea);
		sp.setBorder(BorderFactory.createTitledBorder("Messages envoyés/reçus :"));
		sp.setPreferredSize(new Dimension(380,190));
		pane.add(sp);

		
		agentNameSource_JLabel.setHorizontalAlignment(SwingConstants.LEFT);
		agentNameDestination_JLabel.setHorizontalAlignment(SwingConstants.LEFT);
		msgSend_JLabel.setHorizontalAlignment(SwingConstants.LEFT);

		pane.setPreferredSize(new Dimension(400,450));
		agentSrc_pane.setPreferredSize(new Dimension(400,60));
		agentDest_pane.setPreferredSize(new Dimension(400,60));
		msgSend_pane.setPreferredSize(new Dimension(400,60));
		agentSource_JComboB.setPreferredSize(new Dimension(250,50));
		agentDestination_JComboB.setPreferredSize(new Dimension(250,50));
		msgText_JTextF.setPreferredSize(new Dimension(250,50));
		send_JButton.setPreferredSize(new Dimension(200,50));
		
		JLabel icon=new JLabel(new ImageIcon(getClass().getResource("iconAgents.jpg")));
		icon_pane.setBackground(Color.white);
		icon_pane.add(icon);
		this.getContentPane().add(pane, BorderLayout.EAST);
		this.getContentPane().add(icon_pane, BorderLayout.CENTER);
		
		listAgentsName.add("c1");
		listAgentsName.add("c2");
		listAgentsName.add("c3");

		agentSource_JComboB.addItem(listAgentsName.get(0));
		agentSource_JComboB.addItem(listAgentsName.get(1));
		agentSource_JComboB.addItem(listAgentsName.get(2));
		agentSource_JComboB.setBackground(Color.white);
		
		agentDestination_JComboB.addItem(listAgentsName.get(0));
		agentDestination_JComboB.addItem(listAgentsName.get(1));
		agentDestination_JComboB.addItem(listAgentsName.get(2));
		agentDestination_JComboB.setBackground(Color.white);
		agentDestination_JComboB.setSelectedIndex(1);
		

	}
	

}
