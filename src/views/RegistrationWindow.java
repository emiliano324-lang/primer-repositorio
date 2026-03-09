package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;

//import java.util.concurrent.Flow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
//import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
//import javax.swing.JSlider;
import javax.swing.JTextField;
//import javax.swing.border.Border;
//import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RegistrationWindow extends JFrame{

	LoginWindow window;
	
	public RegistrationWindow() {
		
		setSize(450, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLocation(100,100);
		//setBounds(100,100,500,500);
		setResizable(false);
		setTitle("Registro");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icon = tk.getImage("src/img/logo_uabcs.png");
		setIconImage(icon);
		
		initializeComponents();
		
		setVisible(true);
	}
	
	public void initializeComponents() {
		
		Color customBlue = new Color(0, 31, 84);
		Font subtitleFont = new Font("Verdana", Font.BOLD, 16);
		Font textFont = new Font("Verdana", Font.BOLD, 12);
		FlowLayout alignToCenter = new FlowLayout(FlowLayout.CENTER);
		
		// CREAR TITULO "FORMULARIO DE REGISTRO"
		JPanel superiorBar = new JPanel(alignToCenter);

		superiorBar.setBackground(customBlue);

		JLabel title = new JLabel("FORMULARIO DE REGISTRO");
		
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Verdana", Font.BOLD, 20));
		
		// CREAR FORMULARIO
		JPanel componentsPanel = new JPanel();
		
		componentsPanel.setLayout(new BoxLayout(componentsPanel, BoxLayout.Y_AXIS));
		componentsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		componentsPanel.setBackground(customBlue);
		
		// APARTADO DE DATOS IMPORTANTE (NOMBRE DE USUARIO, CORREO, CONTRASEÑA, ETC.)
		JPanel importantDataSection = new JPanel(alignToCenter);
		
		importantDataSection.setOpaque(false);
		
		JLabel lblImportantData = createLabel("Datos Importantes", subtitleFont, importantDataSection);

		// CREAR PANEL GRID DE DOS COLUMNAS
		JPanel importantDataGrid = new JPanel(new GridLayout(1,2,10,0));
		importantDataGrid.setOpaque(false);
		
		// COLUMNA IZQUIERDA
		JPanel labelsColumn = new JPanel();
		labelsColumn.setLayout(new BoxLayout(labelsColumn,BoxLayout.Y_AXIS));
		labelsColumn.setOpaque(false);
		
		// COLUMNA DERECHA
		JPanel fieldsColumn = new JPanel();
		fieldsColumn.setLayout(new BoxLayout(fieldsColumn,BoxLayout.Y_AXIS));
		
		// ETIQUETAS
		JLabel lblName = createLabel("Nombre de Usuario", textFont, labelsColumn);
		JLabel lblEmail = createLabel("Email", textFont, labelsColumn);
		JLabel lblPassword = createLabel("Contraseña", textFont, labelsColumn);
		JLabel lblConfirmPassword = createLabel("Confirmar contraseña", textFont, labelsColumn);
		
		// CAMPOS Y SU ETIQUETA DE ERROR
		JTextField txtFieldName = createTextField(fieldsColumn);
		JLabel lblEmptyFieldName = createErrorLabel("El nombre es obligatorio", fieldsColumn);
		
		JTextField txtFieldEmail = createTextField(fieldsColumn);
		JLabel lblEmptyFieldEmail = createErrorLabel("El correo es obligatorio", fieldsColumn);
		JLabel lblUnvalidEmail = createErrorLabel("Correo no válido", fieldsColumn);
		
		JPasswordField pwdPassword = createPasswordField(fieldsColumn);
		JLabel lblEmptyFieldPassword = createErrorLabel("La contraseña es obligatoria", fieldsColumn);

		JPasswordField pwdConfirmPassword = createPasswordField(fieldsColumn);
		JLabel lblEmptyFieldConfirmPassword = createErrorLabel("Debes confirmar la contraseña", fieldsColumn);
		JLabel lblErrorUnequalPasswords = createErrorLabel("Las contraseñas no coinciden", fieldsColumn);
		
		// AÑADIR AMBAS COLUMNAS EN EL PANEL
		importantDataGrid.add(labelsColumn);
		importantDataGrid.add(fieldsColumn);
		
		// APARTADO DE SEXO
		JPanel sexSection = new JPanel(alignToCenter);

		sexSection.setOpaque(false);
		
		JLabel lblSex = createLabel("Sexo", subtitleFont, sexSection);
		
		// PANEL DE OPCIÓN DE SEXO
		JPanel sexFlowPanel = new JPanel(alignToCenter);
		
		sexFlowPanel.setOpaque(false);
		
		ButtonGroup grpSexes = new ButtonGroup();
		
		JRadioButton rbMan = createJRadioButton("Hombre", grpSexes, sexFlowPanel);
		JRadioButton rbWoman = createJRadioButton("Mujer", grpSexes, sexFlowPanel);
		JRadioButton rbDoNotSay = createJRadioButton("Prefiero no decir", grpSexes, sexFlowPanel);
		
		// APARTADO DE PRIVACIDAD
		JPanel privacySection = new JPanel(alignToCenter);
		
		privacySection.setOpaque(false);

		JLabel lblPrivacy = createLabel("Privacidad", subtitleFont, privacySection);
		
		JPanel termsAndConditionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		termsAndConditionsPanel.setOpaque(false);
		
		JRadioButton rbTermsAndConditions = createJRadioButton("Acepto terminos y condiciones", null, termsAndConditionsPanel);
		
		// BOTONES DE SALIR Y REGISTRARSE
		JPanel exitAndRegisterButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		exitAndRegisterButtons.setBackground(customBlue);
		
		JButton btnExit = new JButton("Salir");
		btnExit.setFont(textFont);
		
		JButton btnRegistrate = new JButton("Registrarme");
		btnRegistrate.setFont(textFont);
		
		// AÑADIR TITULO
		superiorBar.add(title);
		add(superiorBar, BorderLayout.NORTH);
		
		// AÑADIR APARTADO DE DATOS IMPORTANTES
		componentsPanel.add(importantDataSection);
		componentsPanel.add(importantDataGrid);
		
		// AÑADIR APARTADO SEXO
		componentsPanel.add(sexSection);
		componentsPanel.add(sexFlowPanel);
		
		// AÑADIR APARTADO DE PRIVACIDAD
		componentsPanel.add(privacySection);
		componentsPanel.add(termsAndConditionsPanel);
		
		// AÑADIR BOTONES DE REGISTRAR Y SALIR
		exitAndRegisterButtons.add(btnExit);
		exitAndRegisterButtons.add(btnRegistrate);
		componentsPanel.add(exitAndRegisterButtons);
	
		//nombre
		txtFieldName.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				
				// Validar nombre
			    if (txtFieldName.getText().trim().isEmpty()) {
			        lblEmptyFieldName.setVisible(true);
			    } else {
			        lblEmptyFieldName.setVisible(false);
			    }
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
			    if (txtFieldName.getText().trim().isEmpty()) {
			        lblEmptyFieldName.setVisible(true);

			    } else {
			        lblEmptyFieldName.setVisible(false);
			    }
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// Validar nombre
			    if (txtFieldName.getText().trim().isEmpty()) {
			        lblEmptyFieldName.setVisible(true);

			    } else {
			        lblEmptyFieldName.setVisible(false);
			    }
			}
		}
		);;
		
		
		//correo
		txtFieldEmail.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if (txtFieldEmail.getText().trim().isEmpty()) {
				    	lblEmptyFieldEmail.setVisible(true);
			        
			    } else if (!txtFieldEmail.getText().contains("@")) {
			    		lblEmptyFieldEmail.setVisible(false);
				    	lblUnvalidEmail.setVisible(true);
			    		
			    } else {
			    		lblEmptyFieldEmail.setVisible(false);
				    	lblUnvalidEmail.setVisible(false);
	
			    }
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (txtFieldEmail.getText().trim().isEmpty()) {
			    	lblEmptyFieldEmail.setVisible(true);
		        
		    } else if (!txtFieldEmail.getText().contains("@")) {
		    		lblEmptyFieldEmail.setVisible(false);
			    	lblUnvalidEmail.setVisible(true);
		    		
		    } else {
		    		lblEmptyFieldEmail.setVisible(false);
			    	lblUnvalidEmail.setVisible(false);
		    }
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if (txtFieldEmail.getText().trim().isEmpty()) {
					lblEmptyFieldEmail.setVisible(true);
		        
				} else if (!txtFieldEmail.getText().contains("@")) {
					lblEmptyFieldEmail.setVisible(false);
					lblUnvalidEmail.setVisible(true);
		    		
				} else {
		    			lblEmptyFieldEmail.setVisible(false);
		    			lblUnvalidEmail.setVisible(false);
				}
		    }
		}
		);;
		
		//contraseña y confirmar contraseña (por mejorar)
		pwdPassword.getDocument().addDocumentListener(new DocumentListener() {
			
			String pass = new String(pwdPassword.getPassword());
			String confirmPass = new String(pwdConfirmPassword.getPassword());
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			    if (pass.isEmpty()) {
			    		lblEmptyFieldPassword.setVisible(true);
			    } else {
			    		lblEmptyFieldPassword.setVisible(false);
			    }

			    if (confirmPass.isEmpty()) {
			        lblEmptyFieldConfirmPassword.setVisible(true);
			    } else {
			    		lblEmptyFieldConfirmPassword.setVisible(false);
			    }

			    if (!confirmPass.isEmpty() && !pass.equals(confirmPass)) {
			    		lblErrorUnequalPasswords.setVisible(true);
			    } else {
			    		lblErrorUnequalPasswords.setVisible(false);
			    }
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			    if (pass.isEmpty()) {
			    		lblEmptyFieldPassword.setVisible(true);
			    } else {
			    		lblEmptyFieldPassword.setVisible(false);
			    }

			    if (confirmPass.isEmpty()) {
			        lblEmptyFieldConfirmPassword.setVisible(true);
			    } else {
			    		lblEmptyFieldConfirmPassword.setVisible(false);
			    }

			    if (!confirmPass.isEmpty() && !pass.equals(confirmPass)) {
			    		lblErrorUnequalPasswords.setVisible(true);
			    } else {
			    		lblErrorUnequalPasswords.setVisible(false);
			    }
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			    if (pass.isEmpty()) {
			    		lblEmptyFieldPassword.setVisible(true);
			    } else {
			    		lblEmptyFieldPassword.setVisible(false);
			    }

			    if (confirmPass.isEmpty()) {
			        lblEmptyFieldConfirmPassword.setVisible(true);
			    } else {
			    		lblEmptyFieldConfirmPassword.setVisible(false);
			    }

			    if (!confirmPass.isEmpty() && !pass.equals(confirmPass)) {
			    		lblErrorUnequalPasswords.setVisible(true);
			    } else {
			    		lblErrorUnequalPasswords.setVisible(false);
			    }
				
			}
		});
		
		btnRegistrate.addActionListener(e -> {

		    boolean errorFound = false;

		    // Validar nombre
		    if (txtFieldName.getText().trim().isEmpty()) {
		        lblEmptyFieldName.setVisible(true);
		        errorFound = true;

		    } else {
		        lblEmptyFieldName.setVisible(false);
		    }

		    // Validar correo
		    if (txtFieldEmail.getText().trim().isEmpty()) {
			    	lblEmptyFieldEmail.setVisible(true);
		        errorFound = true;
		        
		    } else if (!txtFieldEmail.getText().contains("@")) {
		    		lblEmptyFieldEmail.setVisible(false);
			    	lblUnvalidEmail.setVisible(true);
		    		
		    } else {
		    		lblEmptyFieldEmail.setVisible(false);
			    	lblUnvalidEmail.setVisible(false);

		    }

		    // Validar contraseña
		    String pass = new String(pwdPassword.getPassword());
		    String confirmPass = new String(pwdConfirmPassword.getPassword());

		    if (pass.isEmpty()) {
		    		lblEmptyFieldPassword.setVisible(true);
		        errorFound = true;
		    } else {
		    		lblEmptyFieldPassword.setVisible(false);
		    }

		    if (confirmPass.isEmpty()) {
		        lblEmptyFieldConfirmPassword.setVisible(true);
		        errorFound = true;
		    } else {
		    		lblEmptyFieldConfirmPassword.setVisible(false);
		    }

		    if (!confirmPass.isEmpty() && !pass.equals(confirmPass)) {
		    		lblErrorUnequalPasswords.setVisible(true);
		        errorFound = true;
		    } else {
		    		lblErrorUnequalPasswords.setVisible(false);
		    }

		    // Validar términos
		    if (!rbTermsAndConditions.isSelected()) {
		        JOptionPane.showMessageDialog(null,
		                "Debes aceptar los términos y condiciones",
		                "Error",
		                JOptionPane.WARNING_MESSAGE);
		        errorFound = true;
		    }

		    // Si todo está correcto
		    if (!errorFound) {
		        JOptionPane.showMessageDialog(null,
		                "Registro exitoso",
		                "Éxito",
		                JOptionPane.INFORMATION_MESSAGE);
		        handleLogin();
		    }

		});
		
		JScrollPane scroll = new JScrollPane(componentsPanel);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
	}
	
	private JLabel createLabel(String lblText, Font font, JPanel panel) {
		
		JLabel label = new JLabel(lblText);
		
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setVisible(true);
		label.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

		panel.add(label);
		
		return label;
	}
	
	private JTextField createTextField(JPanel panel) {
		
		JTextField textField = new JTextField();
		
		textField.setFont(new Font("Verdana", Font.BOLD, 12));
		
		panel.add(textField);
		
		return textField;
	}
	
	private JPasswordField createPasswordField(JPanel panel) {
		
		JPasswordField passwordField = new JPasswordField();
		
		passwordField.setFont(new Font("Verdana", Font.BOLD, 12));
		
		panel.add(passwordField);
		
		return passwordField;
	}
	
	private JRadioButton createJRadioButton(String lblText, ButtonGroup grpButtons, JPanel panel) {
		
		JRadioButton button = new JRadioButton(lblText);
		
		button.setOpaque(false);
		button.setFont(new Font("Verdana", Font.BOLD, 12));
		button.setForeground(Color.WHITE);
		
		if(grpButtons != null) {
			grpButtons.add(button);
		}
		
		if(panel != null) {
			panel.add(button);
		}
		
		return button;
	}
	
	private JLabel createErrorLabel(String lblText, JPanel panel) {
		
		JLabel errorLabel = new JLabel(lblText);
		
		errorLabel.setFont(new Font("Verdana", Font.BOLD, 11));
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
		panel.add(errorLabel);
		
		
		return errorLabel;
	}
	
	 private void handleLogin() {
		new MainWindow();
		
		window.dispose();
	}
}
