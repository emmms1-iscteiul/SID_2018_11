package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;


import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class InvestigadorGui {

	private JFrame frame;

	public InvestigadorGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(450, 200);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addContent();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void addContent() {
		frame.setLayout(new BorderLayout());

		
		JPanel topPanel=new JPanel();
		
		JLabel frameLabel = new JLabel("Manutenção De Culturas e Medições");
		frameLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

		topPanel.add(frameLabel);
	
		JPanel centerPanel=new JPanel(new GridLayout(4,1));
		
		JLabel consultarLabel=new JLabel("Consultar tabela: ");
		JLabel criarLabel=new JLabel("Criar tabela: ");
		JLabel editarLabel=new JLabel("Editar tabela: ");
		JLabel apagarLabel=new JLabel("Apagar tabela: ");
		
		JComboBox<String> consultarComboBox=new JComboBox<String>();
		consultarComboBox.addItem("Cultura");
		consultarComboBox.addItem("Medição");
		consultarComboBox.addItem("Variável");
		consultarComboBox.addItem("Variável Medida");
		

		JComboBox<String> criarComboBox=new JComboBox<String>();
		criarComboBox.addItem("Cultura");
		criarComboBox.addItem("Medição");
		criarComboBox.addItem("Variável");
		criarComboBox.addItem("Variável Medida");
		
		JComboBox<String> editarComboBox=new JComboBox<String>();
		editarComboBox.addItem("Cultura");
		editarComboBox.addItem("Medição");
		editarComboBox.addItem("Variável");
		editarComboBox.addItem("Variável Medida");
		
		JComboBox<String> apagarComboBox=new JComboBox<String>();
		apagarComboBox.addItem("Cultura");
	
		
		centerPanel.add(consultarLabel);
		centerPanel.add(consultarComboBox);
		centerPanel.add(criarLabel);
		centerPanel.add(criarComboBox);
		centerPanel.add(editarLabel);
		centerPanel.add(editarComboBox);
		centerPanel.add(apagarLabel);
		centerPanel.add(apagarComboBox);
		
		frame.add(topPanel,BorderLayout.NORTH);
		frame.add(centerPanel,BorderLayout.CENTER);

	}

	public void open() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		InvestigadorGui frame = new InvestigadorGui("Monotorização de Culturas");
		frame.open();
	}

}
