import javax.swing.*;


public class Interface extends JFrame {
	private JPanel container = new JPanel();
  private JComboBox combo = new JComboBox();
  private JLabel label = new JLabel("Une ComboBox");
	public Interface() {
		// On nomme la fenetre
		this.setTitle("Kayak");
		// On definit sa taille en largeur et en hauteur
		this.setSize(400, 100);
		// On place la fenetre au milieu
		this.setLocationRelativeTo(null);
		// On termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    combo.setPreferredSize(new Dimension(100, 20));

    JPanel top = new JPanel();
    top.add(label);
    top.add(combo);
    container.add(top, BorderLayout.NORTH);
    this.setContentPane(container);
    this.setVisible(true);            
  
	}
	
	public static void main(String[] args) {
		Interface fenetre = new Interface();
	}
	
}

