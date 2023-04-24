package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import classes.Produto;
import dao.ProdutoDao;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCompatibilidade extends JFrame {

	private JPanel contentPane;
	private JTable tableProduto;
	private JTable tableCompativel;
	private List<Produto> lista;
	private List<Produto> prodComp;
	private DefaultTableModel modelo1;
	private DefaultTableModel modelo2;
	private Produto produto;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCompatibilidade frame = new TelaCompatibilidade();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCompatibilidade() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\joaob\\Documents\\Icones\\2633204_circuit_digital_tech_technology_icon.png"));
		setTitle("Compatibilidade");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 938, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 912, 629);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scpProduto = new JScrollPane();
		scpProduto.setBounds(23, 80, 426, 538);
		panel.add(scpProduto);
		
		tableProduto = new JTable();
		tableProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				Integer itemSelecionado = tableProduto.getSelectionModel().getLeadSelectionIndex();
				produto = lista.get(itemSelecionado);
				popularTabela2();
			}
		});
		scpProduto.setViewportView(tableProduto);
		popularTabela1();

		
		JScrollPane scpCompativel = new JScrollPane();
		scpCompativel.setBounds(472, 80, 430, 538);
		panel.add(scpCompativel);
		
		tableCompativel = new JTable();
		scpCompativel.setViewportView(tableCompativel);
		
		JLabel lblNewLabel_1_2 = new JLabel("Produtos Dispon\u00EDveis:");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(23, 55, 176, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Produtos Compat\u00EDveis:");
		lblNewLabel_1_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblNewLabel_1_2_1.setBounds(473, 55, 176, 14);
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel = new JLabel("Compatibilidade de Produtos");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(223, 11, 292, 33);
		panel.add(lblNewLabel);
	}
	
	public void popularTabela1() {
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			lista = produtoDao.buscarTodos();
			modelo1 = new DefaultTableModel();

			modelo1.addColumn("Nome");
			modelo1.addColumn("Marca");
			modelo1.addColumn("Categoria");

			for (Produto produto : lista) {
				modelo1.addRow(new String[] { produto.getNome(), produto.getMarca(),
						produto.getCategoria() });
			}
			tableProduto.setModel(modelo1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro" + e);
		}
	}
	public void popularTabela2() {
		try {

			ProdutoDao produtoDao = new ProdutoDao();
			prodComp = produtoDao.buscarCompativeis(produto.getIdproduto());
			modelo2 = new DefaultTableModel();

			modelo2.addColumn("Nome");
			modelo2.addColumn("Marca");
			modelo2.addColumn("Categoria");
			

			for (Produto produto : prodComp) {
				modelo2.addRow(new String[] { produto.getNome(), produto.getMarca(),
						produto.getCategoria(), produto.getQuant().toString() });
			}
			tableCompativel.setModel(modelo2);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro" + e);
		}
	}
}
