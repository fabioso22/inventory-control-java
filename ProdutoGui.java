package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import classes.Funcionario;
import classes.Produto;
import dao.ProdutoDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class ProdutoGui extends JFrame {

	private JPanel contentPane;
	private JTextField txfMarcaProduto;
	private JTextField txfNomeProduto;
	private JTable table;
	private JComboBox cmbProduto;

	private Produto produto;
	private ProdutoDao produtoDao;
	private List<Produto> lista;
	private DefaultTableModel modelo;
	private DefaultComboBoxModel modeloCombo;
	private JComboBox cmbCategoria;
	private JTextField txfDescProduto;
	private JTextField txfQuantidade;
	private JTextField txfCod;
	private JComboBox cmbCateProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoGui frame = new ProdutoGui();
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
	public ProdutoGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\joaob\\Documents\\Icones\\2633204_circuit_digital_tech_technology_icon.png"));
		setTitle("Cadastro de Produtos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 776, 702);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(304, 0, 456, 674);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 436, 652);
		panel.add(scrollPane);

		JComboBox cmbCateProduto = new JComboBox();
		cmbCateProduto.setModel(new DefaultComboBoxModel(new String[] { "Processador", "Fonte", "SSD", "Placa de Video",
				"Cooler", "Placa Mae", "Memoria RAM" }));
		cmbCateProduto.setBounds(35, 305, 242, 22);

		contentPane.add(cmbCateProduto);
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setBackground(new Color(224, 255, 255));
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {

				try {
					Integer itemSelecionado = table.getSelectionModel().getLeadSelectionIndex();

					produto = lista.get(itemSelecionado);
					txfCod.setText(produto.getIdproduto().toString());
					txfNomeProduto.setText(produto.getNome());
					txfMarcaProduto.setText(produto.getMarca());
					cmbCateProduto.setSelectedItem(produto.getCategoria());
					txfDescProduto.setText(produto.getDescricao());
					txfQuantidade.setText(produto.getQuant().toString());

				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, "Erro " + a);
				}

			}
		});
		scrollPane.setViewportView(table);
		popularTabela();

		JLabel lblNewLabel = new JLabel("Nome do produto");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblNewLabel.setBounds(36, 165, 133, 34);
		contentPane.add(lblNewLabel);

		JLabel lblCategoriaDoProduto = new JLabel("Categoria do produto");
		lblCategoriaDoProduto.setForeground(Color.WHITE);
		lblCategoriaDoProduto.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblCategoriaDoProduto.setBounds(36, 271, 176, 34);
		contentPane.add(lblCategoriaDoProduto);

		JLabel lblMarcaDoProduto = new JLabel("Marca do produto");
		lblMarcaDoProduto.setForeground(Color.WHITE);
		lblMarcaDoProduto.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblMarcaDoProduto.setBounds(35, 218, 133, 34);
		contentPane.add(lblMarcaDoProduto);

		JLabel lblDescrioDoProduto = new JLabel("Descri\u00E7\u00E3o do produto");
		lblDescrioDoProduto.setForeground(Color.WHITE);
		lblDescrioDoProduto.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblDescrioDoProduto.setBounds(35, 325, 194, 34);
		contentPane.add(lblDescrioDoProduto);

		txfMarcaProduto = new JTextField();
		txfMarcaProduto.setBounds(35, 251, 242, 20);
		contentPane.add(txfMarcaProduto);
		txfMarcaProduto.setColumns(10);

		txfNomeProduto = new JTextField();
		txfNomeProduto.setColumns(10);
		txfNomeProduto.setBounds(35, 198, 242, 20);
		contentPane.add(txfNomeProduto);

		JButton btnIncluir = new JButton("Incluir Produto");
		btnIncluir.setSelectedIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\aceitar.png"));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Funcionario funcionario = Funcionario.getInstance();

					produto = new Produto();
					produtoDao = new ProdutoDao();

					produto.setNome(txfNomeProduto.getText());
					produto.setMarca(txfMarcaProduto.getText());
					produto.setCategoria(cmbCateProduto.getSelectedItem().toString());
					produto.setDescricao(txfDescProduto.getText());
					produto.setQuant(Integer.parseInt(txfQuantidade.getText()));
					produto.setFuncionario(funcionario);

					produtoDao.incluir(produto);
					limpar();
					popularTabela();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro " + ex);
				}
			}

		});
		btnIncluir.setIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\caixa-de-entrada.png"));
		btnIncluir.setForeground(new Color(0, 128, 128));
		btnIncluir.setBackground(new Color(255, 255, 255));
		btnIncluir.setBounds(42, 475, 221, 36);
		contentPane.add(btnIncluir);

		JButton btnExcluir = new JButton("Excluir Produto");
		btnExcluir.setSelectedIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\login.png"));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Integer resp = JOptionPane.showConfirmDialog(null, "Deseja excluir?");
					if (resp == 0) {
						produto = new Produto();
						produtoDao = new ProdutoDao();
						produto.setIdproduto(Integer.parseInt(txfCod.getText()));
						produtoDao.excluir(produto);
						limpar();
						popularTabela();

					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro " + ex);
				}
			}
		});
		btnExcluir.setIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\login.png"));
		btnExcluir.setForeground(new Color(0, 128, 128));
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(42, 522, 221, 36);
		contentPane.add(btnExcluir);

		JButton btnAlterar = new JButton("Alterar Produto");
		btnAlterar.setSelectedIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\copy-writing.png"));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Funcionario funcionario = Funcionario.getInstance();
					
					produto = new Produto();
					produtoDao = new ProdutoDao();

					produto.setNome(txfNomeProduto.getText());
					produto.setMarca(txfMarcaProduto.getText());
					produto.setCategoria(cmbCateProduto.getSelectedItem().toString());
					produto.setQuant(Integer.parseInt(txfQuantidade.getText()));
					produto.setDescricao(txfDescProduto.getText());
					produto.setFuncionario(funcionario);
					produto.setIdproduto(Integer.parseInt(txfCod.getText()));

					produtoDao.alterar(produto);
					limpar();
					popularTabela();

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro " + e1);

				}

			}
		});
		btnAlterar.setIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\copy-writing.png"));
		btnAlterar.setForeground(new Color(0, 128, 128));
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setBounds(42, 569, 221, 36);
		contentPane.add(btnAlterar);

		JLabel lblCadastroDeProdutos = new JLabel("Cadastro de Produtos");
		lblCadastroDeProdutos.setForeground(Color.WHITE);
		lblCadastroDeProdutos.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		lblCadastroDeProdutos.setBounds(35, 11, 241, 34);
		contentPane.add(lblCadastroDeProdutos);

		txfDescProduto = new JTextField();
		txfDescProduto.setBounds(36, 356, 241, 20);
		contentPane.add(txfDescProduto);
		txfDescProduto.setColumns(10);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setForeground(Color.WHITE);
		lblQuantidade.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblQuantidade.setBounds(35, 387, 113, 20);
		contentPane.add(lblQuantidade);

		txfQuantidade = new JTextField();
		txfQuantidade.setBounds(36, 418, 241, 20);
		contentPane.add(txfQuantidade);
		txfQuantidade.setColumns(10);

		txfCod = new JTextField();
		txfCod.setEditable(false);
		txfCod.setBounds(36, 148, 241, 20);
		contentPane.add(txfCod);
		txfCod.setColumns(10);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblCodigo.setBounds(36, 115, 77, 22);
		contentPane.add(lblCodigo);
	}

	public void limpar() {

		txfCod.setText(null);
		txfNomeProduto.setText(null);
		txfMarcaProduto.setText(null);
		//cmbCateProduto.setSelectedItem("Processador");
		txfDescProduto.setText(null);
		txfQuantidade.setText(null);
	}

	public void popularTabela() {
		try {
			produtoDao = new ProdutoDao();
			lista = produtoDao.buscarTodos();
			modelo = new DefaultTableModel();

			modelo.addColumn("Codigo");
			modelo.addColumn("Nome");
			modelo.addColumn("Marca");
			modelo.addColumn("Categoria");
			modelo.addColumn("Quantidade");

			for (Produto produto : lista) {
				modelo.addRow(new String[] { produto.getIdproduto().toString(), produto.getNome(), produto.getMarca(),
						produto.getCategoria(), produto.getQuant().toString() });
			}
			table.setModel(modelo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro" + e);
		}
	}
}
