package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import classes.Movimento;
import classes.Produto;
import dao.MovimentoDao;
import dao.ProdutoDao;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class TelaPrincipalConsulta extends JFrame {
	private JTable table;
	private List<Produto> listaProdutos;
	private DefaultTableModel modeloTabela;
	private DefaultComboBoxModel modeloCombo;
	private JComboBox cmbCategoria;
	private JComboBox cmbMarca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalConsulta frame = new TelaPrincipalConsulta();
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
	public TelaPrincipalConsulta() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\joaob\\Documents\\Icones\\2633204_circuit_digital_tech_technology_icon.png"));
		getContentPane().setBackground(new Color(0, 128, 128));
		setTitle("Consulta de Produtos");
		getContentPane().setForeground(new Color(70, 130, 180));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 655);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnProduto = new JMenu("Produto");
		mnProduto.setForeground(new Color(0, 0, 128));
		mnProduto.setBackground(new Color(0, 206, 209));
		menuBar.add(mnProduto);

		JMenuItem mnit = new JMenuItem("Cadastrar produto");
		mnit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoGui pg = new ProdutoGui();
				pg.setVisible(true);
			}
		});
		mnit.setForeground(new Color(0, 0, 255));
		mnProduto.add(mnit);

		JMenu mnMovimentacao = new JMenu("Movimenta\u00E7\u00E3o");
		mnMovimentacao.setForeground(new Color(0, 0, 128));
		mnMovimentacao.setBackground(new Color(0, 0, 128));
		menuBar.add(mnMovimentacao);

		JMenuItem mnitEstoque = new JMenuItem("Tela de estoque");
		mnitEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMovimento tm = new TelaMovimento();
				tm.setVisible(true);
			}
		});
		mnitEstoque.setForeground(new Color(0, 0, 205));
		mnitEstoque.setBackground(new Color(0, 139, 139));
		mnMovimentacao.add(mnitEstoque);
		getContentPane().setLayout(null);

		JLabel lblCotegoriaProduto = new JLabel("Tabela de Produtos");
		lblCotegoriaProduto.setForeground(new Color(255, 255, 255));
		lblCotegoriaProduto.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 19));
		lblCotegoriaProduto.setBounds(6, 11, 316, 46);
		getContentPane().add(lblCotegoriaProduto);

		JButton btnMontar = new JButton("Montar Produto");
		btnMontar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMontar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCompatibilidade tc = new TelaCompatibilidade();
				tc.setVisible(true);
			}
		});
		btnMontar.setIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\serra-de-vaivem.png"));
		btnMontar.setBackground(new Color(102, 205, 170));
		btnMontar.setForeground(new Color(255, 255, 255));
		btnMontar.setBounds(529, 25, 255, 32);
		getContentPane().add(btnMontar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 64, 778, 519);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(table);
		popularTabela();
	}

	public void popularTabela() {
		try {

			ProdutoDao produtoDao = new ProdutoDao();
			listaProdutos = produtoDao.buscarTodos();
			modeloTabela = new DefaultTableModel();
			modeloTabela.addColumn("Codigo");
			modeloTabela.addColumn("Marca");
			modeloTabela.addColumn("Nome");
			modeloTabela.addColumn("Descricao");
			modeloTabela.addColumn("Categoria");
			modeloTabela.addColumn("Quantidade");

			for (Produto produto : listaProdutos) {
				modeloTabela
						.addRow(new String[] { produto.getIdproduto().toString(), produto.getMarca(), produto.getNome(),
								produto.getDescricao(), produto.getCategoria(), produto.getQuant().toString() });
				table.setModel(modeloTabela);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao criar tabela " + e);
		}

	}
}
