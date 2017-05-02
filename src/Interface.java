import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Interface extends JFrame {
	private JPanel container = new JPanel();
	private JCheckBox check1 = new JCheckBox("Vegetarien");
	private JCheckBox check2 = new JCheckBox("Grec");
	private JCheckBox check3 = new JCheckBox("Italien");
	private JCheckBox check4 = new JCheckBox("Burger");
	private JLabel label = new JLabel("Type(s) de restaurant :");
	private JButton bouton = new JButton("Valider");
	private JLabel adresses = new JLabel("Adresse(s) :");
	private JTextField jtf1 = new JTextField("");
	private JTextField jtf2 = new JTextField("");
	private JTextField jtf3 = new JTextField("");
	private JTextField jtf4 = new JTextField("");
	private JTextField jtf5 = new JTextField("");
	
	public Interface() {
		this.setTitle("Kayak");
		// On definit sa taille en largeur et en hauteur
		this.setSize(800, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    JPanel top = new JPanel();
	    check1.addActionListener(new StateListener());
	    check2.addActionListener(new StateListener());
	    check3.addActionListener(new StateListener());
	    check4.addActionListener(new StateListener());
	    top.add(label);
	    top.add(check1);
	    top.add(check2);
	    top.add(check3);
	    top.add(check4);
	    container.add(top, BorderLayout.NORTH);
	    
	    JPanel center = new JPanel();
	    Font police = new Font("Arial", Font.BOLD, 14);
	    jtf1.setFont(police);
	    jtf1.setPreferredSize(new Dimension(250, 30));
	    jtf1.setForeground(Color.BLACK);
	    jtf2.setFont(police);
	    jtf2.setPreferredSize(new Dimension(250, 30));
	    jtf2.setForeground(Color.BLACK);
	    jtf3.setFont(police);
	    jtf3.setPreferredSize(new Dimension(250, 30));
	    jtf3.setForeground(Color.BLACK);
	    jtf4.setFont(police);
	    jtf4.setPreferredSize(new Dimension(250, 30));
	    jtf4.setForeground(Color.BLACK);
	    jtf5.setFont(police);
	    jtf5.setPreferredSize(new Dimension(250, 30));
	    jtf5.setForeground(Color.BLACK);
	    
	    center.add(adresses);
	    center.add(jtf1);
	    center.add(jtf2);
	    center.add(jtf3);
	    center.add(jtf4);
	    center.add(jtf5);
	    container.add(center, BorderLayout.CENTER);
	    
	    bouton.addActionListener(new BoutonListener()); 
	    JPanel south = new JPanel();
	    south.add(bouton);
	    container.add(south, BorderLayout.SOUTH);
	    
	    this.setContentPane(container);
		// On rend la fenetre visible
		this.setVisible(true);
	}
	
	class StateListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      System.out.println("source : " + ((JCheckBox)e.getSource()).getText() + " - état : " + ((JCheckBox)e.getSource()).isSelected());
	    }
	}
	
	class BoutonListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	      System.out.println("Valider");
	    }
	}
	
	public static void main(String[] args) {
		Interface fenetre = new Interface();
	}
	
}
