package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Funcionario;
import dao.FuncionarioDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.Toolkit;

public class telaFuncCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txfLogin;
	private JTextField txfNome;
	private JPasswordField pwfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaFuncCadastro frame = new telaFuncCadastro();
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
	public telaFuncCadastro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\joaob\\Documents\\Icones\\2633204_circuit_digital_tech_technology_icon.png"));
		setTitle("Cadastro de Funcion\u00E1rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 549, 375);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastroDeFuncionarios = new JLabel("Cadastro de Funcion\u00E1rios");
		lblCadastroDeFuncionarios.setForeground(new Color(255, 255, 255));
		lblCadastroDeFuncionarios.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		lblCadastroDeFuncionarios.setBounds(10, 0, 240, 44);
		contentPane.add(lblCadastroDeFuncionarios);

		JLabel lblLongin = new JLabel("Login:");
		lblLongin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblLongin.setBounds(31, 126, 72, 25);
		contentPane.add(lblLongin);

		JLabel lblsenha = new JLabel("Senha:");
		lblsenha.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblsenha.setBounds(31, 188, 72, 14);
		contentPane.add(lblsenha);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblNome.setBounds(31, 71, 72, 14);
		contentPane.add(lblNome);

		JCheckBox chcAtivo = new JCheckBox("Ativo");
		chcAtivo.setFont(new Font("Arial Black", Font.PLAIN, 11));
		chcAtivo.setBounds(24, 226, 79, 23);
		contentPane.add(chcAtivo);

		JButton btnCadastrar = new JButton("Cadastrar ");
		btnCadastrar.setIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\aceitar.png"));
		btnCadastrar.setSelectedIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\adicionar-usuario.png"));
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(new Color(0, 128, 128));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FuncionarioDao funcionarioDao = new FuncionarioDao();
					Funcionario funcionario = new Funcionario();

					funcionario.setNome(txfNome.getText());
					funcionario.setLogin(txfLogin.getText());
					funcionario.setSenha(pwfSenha.getText());
					funcionario.setAtivo(chcAtivo.isSelected());
					funcionarioDao.incluir(funcionario);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnCadastrar.setBounds(104, 277, 146, 31);
		contentPane.add(btnCadastrar);

		txfLogin = new JTextField();
		txfLogin.setColumns(10);
		txfLogin.setBounds(113, 125, 314, 31);
		contentPane.add(txfLogin);

		txfNome = new JTextField();
		txfNome.setColumns(10);
		txfNome.setBounds(113, 65, 314, 31);
		contentPane.add(txfNome);

		pwfSenha = new JPasswordField();
		pwfSenha.setBounds(113, 182, 314, 31);
		contentPane.add(pwfSenha);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\login.png"));
		btnLimpar.setSelectedIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\sair.png"));
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpar.setForeground(new Color(255, 255, 255));
		btnLimpar.setBackground(new Color(0, 128, 128));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txfNome.setText(null);
				txfLogin.setText(null);
				pwfSenha.setText(null);
				

			}
		});
		btnLimpar.setBounds(289, 277, 122, 31);
		contentPane.add(btnLimpar);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 533, 60);
		contentPane.add(panel);

	}
}
