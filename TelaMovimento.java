package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import classes.Funcionario;
import classes.Movimento;
import classes.Produto;
import dao.FuncionarioDao;
import dao.MovimentoDao;
import dao.ProdutoDao;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class TelaMovimento extends JFrame {

	private JPanel contentPane;
	private final JButton btnEntrada = new JButton("Entrada");
	private JTextField txfCodProduto;
	private JTextField txfQuantidade;
	private JTextField txfData;
	private JTable table;
	private List<Movimento> listaProdutos;
	private DefaultTableModel modeloTabela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMovimento frame = new TelaMovimento();
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
	public TelaMovimento() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\joaob\\Documents\\Icones\\2633204_circuit_digital_tech_technology_icon.png"));
		setTitle("Movimenta\u00E7\u00E3o de Produto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 587, 489);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnEntrada.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Funcionario funcionario = Funcionario.getInstance();

					Movimento movimento = new Movimento();
					MovimentoDao movimentoDao = new MovimentoDao();

					ProdutoDao produtoDao = new ProdutoDao();
					Produto produto = produtoDao.buscarUm(Integer.parseInt(txfCodProduto.getText()));

					movimento.setData(txfData.getText());
					movimento.setQuant(Integer.parseInt(txfQuantidade.getText()));
					movimento.setTipo("entrada");

					movimento.setProduto(produto);
					movimento.setFuncionario(funcionario);

					movimentoDao.incluir(movimento);

					limpar();
					popularTabela();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro " + ex);
				}

			}
		});
		btnEntrada.setIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\caixa-de-entrada.png"));
		btnEntrada.setForeground(new Color(0, 139, 139));
		btnEntrada.setBounds(31, 400, 124, 31);
		contentPane.add(btnEntrada);

		JButton btnSaida = new JButton("Sa\u00EDda");
		btnSaida.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Funcionario funcionario = Funcionario.getInstance();

					Movimento movimento = new Movimento();
					MovimentoDao movimentoDao = new MovimentoDao();

					ProdutoDao produtoDao = new ProdutoDao();
					Produto produto = produtoDao.buscarUm(Integer.parseInt(txfCodProduto.getText()));

					movimento.setData(txfData.getText());
					movimento.setQuant(Integer.parseInt(txfQuantidade.getText()));
					movimento.setTipo("saida");

					movimento.setProduto(produto);
					movimento.setFuncionario(funcionario);

					movimentoDao.incluir(movimento);

					limpar();
					popularTabela();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro " + ex);
				}
			}
		});
		btnSaida.setIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\login.png"));
		btnSaida.setForeground(new Color(0, 139, 139));
		btnSaida.setBounds(234, 400, 124, 31);
		contentPane.add(btnSaida);

		JButton btnAvaria = new JButton("Avaria");
		btnAvaria.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAvaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Funcionario funcionario = Funcionario.getInstance();

					Movimento movimento = new Movimento();
					MovimentoDao movimentoDao = new MovimentoDao();

					ProdutoDao produtoDao = new ProdutoDao();
					Produto produto = produtoDao.buscarUm(Integer.parseInt(txfCodProduto.getText()));

					movimento.setData(txfData.getText());
					movimento.setQuant(Integer.parseInt(txfQuantidade.getText()));
					movimento.setTipo("avaria");

					movimento.setProduto(produto);
					movimento.setFuncionario(funcionario);

					movimentoDao.incluir(movimento);

					limpar();
					popularTabela();

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);

				}
			}
		});
		btnAvaria.setIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\avaria-no-carro.png"));
		btnAvaria.setForeground(new Color(0, 139, 139));
		btnAvaria.setBounds(429, 400, 124, 31);
		contentPane.add(btnAvaria);

		JButton btnGerarRelatrio = new JButton("Relat\u00F3rio");
		btnGerarRelatrio.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGerarRelatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Document document = new Document();
				try {
					PdfWriter.getInstance(document, new FileOutputStream("C:\\Relatorios\\rel_movimento.pdf"));
					document.open();
					document.add(new Paragraph("Sistema de Controle de Estoque"));
					document.add(new Paragraph("Relatorio de Movimentos"));
					document.add(new Paragraph(" "));

					PdfPTable table = new PdfPTable(4);
					table.addCell("ID Movimento");
					table.addCell("Quantidade");
					table.addCell("Tipo");
					table.addCell("Nome/Produto");
					


					MovimentoDao movimentoDao = new MovimentoDao();
					List<Movimento> lista = movimentoDao.buscarTodos();
					for (Movimento movimento : lista) {
						table.addCell(movimento.getIdmovimento().toString());
						table.addCell(movimento.getQuant().toString());
						table.addCell(movimento.getTipo());
						table.addCell(movimento.getProduto().getNome());
					}
					document.add(table);
				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, a);
				} finally {
					document.close();
				}

			}
		});
		btnGerarRelatrio.setIcon(
				new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\1055107_bookshelf_books_library_icon.png"));
		btnGerarRelatrio.setForeground(new Color(0, 128, 128));
		btnGerarRelatrio.setBackground(new Color(255, 255, 255));
		btnGerarRelatrio.setBounds(448, 110, 113, 31);
		contentPane.add(btnGerarRelatrio);

		JLabel lblNewLabel = new JLabel("C\u00F3digo do Produto");
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(31, 44, 153, 31);
		contentPane.add(lblNewLabel);

		JLabel lblTipo = new JLabel("Quantidade:");
		lblTipo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblTipo.setForeground(new Color(255, 255, 255));
		lblTipo.setBounds(31, 86, 153, 31);
		contentPane.add(lblTipo);

		JLabel lblNewLabel_1 = new JLabel("Data:");
		lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(31, 138, 153, 14);
		contentPane.add(lblNewLabel_1);

		txfCodProduto = new JTextField();
		txfCodProduto.setBounds(172, 49, 251, 20);
		contentPane.add(txfCodProduto);
		txfCodProduto.setColumns(10);

		txfQuantidade = new JTextField();
		txfQuantidade.setColumns(10);
		txfQuantidade.setBounds(172, 91, 251, 20);
		contentPane.add(txfQuantidade);

		txfData = new JTextField();
		txfData.setColumns(10);
		txfData.setBounds(172, 135, 251, 20);
		contentPane.add(txfData);

		JLabel lblNewLabel_2 = new JLabel("Movimenta\u00E7\u00E3o de produto");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(31, 11, 251, 26);
		contentPane.add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 551, 221);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(table);
		popularTabela();

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 11, 551, 157);
		contentPane.add(panel);
	}

	public void limpar() {
		txfCodProduto.setText(null);
		txfQuantidade.setText(null);
		txfData.setText(null);
	}

	public void popularTabela() {

		try {
			MovimentoDao movimentoDao = new MovimentoDao();
			listaProdutos = movimentoDao.buscarTodos();
			modeloTabela = new DefaultTableModel();
			modeloTabela.addColumn("Nome");
			modeloTabela.addColumn("Quantidade");
			modeloTabela.addColumn("Movimento");
			modeloTabela.addColumn("Data");

			for (Movimento movimento : listaProdutos) {
				modeloTabela.addRow(new String[] { movimento.getProduto().getNome(), movimento.getQuant().toString(),
						movimento.getTipo(), movimento.getData() });
				table.setModel(modeloTabela);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao criar tabela " + e);
		}

	}
}
