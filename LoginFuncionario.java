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
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class LoginFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txfLogin;
	private JPasswordField pwrdSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFuncionario frame = new LoginFuncionario();
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
	public LoginFuncionario() {
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		setFont(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\joaob\\Documents\\Icones\\2633204_circuit_digital_tech_technology_icon.png"));
		setTitle("Login do Funcion\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 370);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Realizar Login");
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		lblNewLabel.setBounds(215, 35, 151, 60);
		contentPane.add(lblNewLabel);

		pwrdSenha = new JPasswordField();
		pwrdSenha.setBackground(new Color(245, 245, 245));
		pwrdSenha.setBounds(179, 222, 223, 20);
		contentPane.add(pwrdSenha);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 94, 584, 296);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnLogar = new JButton("Entrar");
		btnLogar.setSelectedIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\login.png"));
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					FuncionarioDao funcionarioDao = new FuncionarioDao();

					if (funcionarioDao.validarFuncionario(txfLogin.getText(), pwrdSenha.getText())) {

						TelaPrincipalConsulta tp = new TelaPrincipalConsulta();
						tp.setVisible(true);
						setVisible(false);

					} else {
						JOptionPane.showMessageDialog(null, "Login ou senha invalidos");
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro" + e1);
				}
			}
		});
		
		btnLogar.setBounds(235, 189, 109, 41);
		panel.add(btnLogar);
		btnLogar.setIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\login.png"));
		btnLogar.setForeground(new Color(0, 128, 128));
		btnLogar.setBackground(new Color(255, 255, 255));
		btnLogar.setFont(new Font("Tahoma", Font.BOLD, 13));

		JButton btnCadastrar = new JButton("Nao tenho cadastro");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaFuncCadastro tc = new telaFuncCadastro();
				tc.setVisible(true);
			}
		});
		btnCadastrar.setBounds(216, 159, 158, 20);
		panel.add(btnCadastrar);
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCadastrar.setForeground(new Color(0, 128, 128));
		btnCadastrar.setBackground(Color.WHITE);

		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setBounds(235, 11, 102, 32);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\adicionar-usuario.png"));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		txfLogin = new JTextField();
		txfLogin.setBounds(179, 53, 223, 20);
		panel.add(txfLogin);
		txfLogin.setBackground(new Color(245, 245, 245));
		txfLogin.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(235, 84, 111, 32);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\joaob\\Documents\\Icones\\cadeado.png"));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	}
}
