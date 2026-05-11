package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import utils.Config;


public class UserFormDialog extends JDialog {

    private JTextField txtFieldName;
    private JTextField txtFieldEmail;
    private JPasswordField pwdPassword;
    private JPasswordField pwdConfirmPassword;
    
    private JLabel lblImagePreview;
	private JLabel lblImageName;
	private JLabel lblErrorFieldName;
	private JLabel lblErrorFieldEmail;
	private JLabel lblErrorFieldPassword;
	private JLabel lblErrorFieldConfirmPassword;
	private JLabel lblErrorImage;

    private ButtonGroup grpSexes;

    private JRadioButton rbMan;
    private JRadioButton rbWoman;
    private JRadioButton rbDoNotSay;

    private String selectedImagePath;
    
    private JButton btnSave;
    private JButton btnCancel;
	private JButton btnSelectImage;

    // GETTERS
	public String getSex() {

        if(rbMan.isSelected()) {
            return "Masculino";
        }

        if(rbWoman.isSelected()) {
            return "Femenino";
        }

        return "No Definido";
    }
	
	public JLabel getLblImagePreview() {
		return lblImagePreview;
	}

	public JLabel getLblImageName() {
		return lblImageName;
	}

	public JLabel getLblErrorImage() {
		return lblErrorImage;
	}

	public String getSelectedImagePath() {
		return selectedImagePath;
	}

	public JButton getBtnSelectImage() {
		return btnSelectImage;
	}

	public JTextField getTxtFieldName() {
		return txtFieldName;
	}

	public void setTxtFieldName(JTextField txtFieldName) {
		this.txtFieldName = txtFieldName;
	}

	public JTextField getTxtFieldEmail() {
		return txtFieldEmail;
	}

	public void setTxtFieldEmail(JTextField txtFieldEmail) {
		this.txtFieldEmail = txtFieldEmail;
	}

	public String getPwdPassword() {
		return String.valueOf(pwdPassword.getPassword());
	}

	public void setPwdPassword(JPasswordField pwdPassword) {
		this.pwdPassword = pwdPassword;
	}
	
	public String getPwdConfirmPassword() {
		return String.valueOf(pwdConfirmPassword.getPassword());
	}

	public void setPwdConfirmPassword(JPasswordField pwdConfirmPassword) {
		this.pwdConfirmPassword = pwdConfirmPassword;
	}

	public JLabel getLblErrorFieldName() {
		return lblErrorFieldName;
	}

	public void setLblErrorFieldName(JLabel lblErrorFieldName) {
		this.lblErrorFieldName = lblErrorFieldName;
	}

	public JLabel getLblErrorFieldEmail() {
		return lblErrorFieldEmail;
	}

	public void setLblErrorFieldEmail(JLabel lblErrorFieldEmail) {
		this.lblErrorFieldEmail = lblErrorFieldEmail;
	}

	public JLabel getLblErrorFieldPassword() {
		return lblErrorFieldPassword;
	}

	public void setLblErrorFieldPassword(JLabel lblErrorFieldPassword) {
		this.lblErrorFieldPassword = lblErrorFieldPassword;
	}

	public JLabel getLblErrorFieldConfirmPassword() {
		return lblErrorFieldConfirmPassword;
	}

	public void setLblErrorFieldConfirmPassword(JLabel lblErrorFieldConfirmPassword) {
		this.lblErrorFieldConfirmPassword = lblErrorFieldConfirmPassword;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public void setGrpSexes(ButtonGroup grpSexes) {
		this.grpSexes = grpSexes;
	}

	public void setRbMan(JRadioButton rbMan) {
		this.rbMan = rbMan;
	}

	public void setRbWoman(JRadioButton rbWoman) {
		this.rbWoman = rbWoman;
	}

	public void setRbDoNotSay(JRadioButton rbDoNotSay) {
		this.rbDoNotSay = rbDoNotSay;
	}

	public ButtonGroup getGrpSexes() {
		return grpSexes;
	}

	public UserFormDialog(JFrame parent) {
        super(parent, true);

        setTitle("Formulario Usuario");
        setSize(450, 450);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        add(initializeComponents());
    }

    private JScrollPane initializeComponents() {
    	Color customBlue = new Color(0, 31, 84);
		Font subtitleFont = new Font("Verdana", Font.BOLD, 16);
		Font textFont = new Font("Verdana", Font.BOLD, 12);
		FlowLayout alignToCenter = new FlowLayout(FlowLayout.CENTER);
		Color defaultButtonColor;
		
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
		importantDataSection.add(createLabel("Datos Importantes", subtitleFont));

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
		labelsColumn.add(createLabel("Nombre de Usuario", textFont));
		labelsColumn.add(createLabel("Email", textFont));
		labelsColumn.add(createLabel("Contraseña", textFont));
		labelsColumn.add(createLabel("Confirmar contraseña", textFont));
		
		// CAMPOS Y SU ETIQUETA DE ERROR
		txtFieldName = createTextField(fieldsColumn);
		lblErrorFieldName = createErrorLabel(fieldsColumn);
		
		txtFieldEmail = createTextField(fieldsColumn);
		lblErrorFieldEmail = createErrorLabel(fieldsColumn);
		
		pwdPassword = createPasswordField(fieldsColumn);
		lblErrorFieldPassword = createErrorLabel(fieldsColumn);

		pwdConfirmPassword = createPasswordField(fieldsColumn);
	    lblErrorFieldConfirmPassword = createErrorLabel(fieldsColumn);
		
		// AÑADIR AMBAS COLUMNAS EN EL PANEL
		importantDataGrid.add(labelsColumn);
		importantDataGrid.add(fieldsColumn);
		
		// APARTADO DE SEXO
		JPanel sexSection = new JPanel(alignToCenter);

		sexSection.setOpaque(false);
		sexSection.add(createLabel("Sexo", subtitleFont));
		
		// PANEL DE OPCIÓN DE SEXO
		JPanel sexFlowPanel = new JPanel(alignToCenter);
		
		sexFlowPanel.setOpaque(false);
		
		grpSexes = new ButtonGroup();
		
		rbMan = createRadio("Hombre", grpSexes, sexFlowPanel);
		rbWoman = createRadio("Mujer", grpSexes, sexFlowPanel);
		rbDoNotSay = createRadio("Prefiero no decir", grpSexes, sexFlowPanel);
		
		// PANEL DE IMAGEN
		JPanel imageSection = new JPanel(alignToCenter);
		
		imageSection.setOpaque(false);
		imageSection.add(createLabel("Foto", subtitleFont));
		
		btnSelectImage = new JButton("Seleccionar imagen");

		lblImageName = new JLabel("Ninguna imagen seleccionada");

		lblImagePreview = new JLabel();
		lblImagePreview.setPreferredSize(new Dimension(120,120));
		lblImagePreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		lblErrorImage = createErrorLabel(componentsPanel);

		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));

		btnSelectImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblImagePreview.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblImageName.setAlignmentX(Component.CENTER_ALIGNMENT);

		imagePanel.add(lblImagePreview);
		imagePanel.add(btnSelectImage);
		imagePanel.add(lblImageName);

		
		// APARTADO DE PRIVACIDAD
		JPanel privacySection = new JPanel(alignToCenter);
		
		privacySection.setOpaque(false);
		privacySection.add(createLabel("Privacidad", subtitleFont));
		
		JPanel termsAndConditionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		termsAndConditionsPanel.setOpaque(false);
		
		JRadioButton rbTermsAndConditions = createRadio("Acepto terminos y condiciones", null, termsAndConditionsPanel);
		
		// BOTONES DE SALIR Y REGISTRARSE
		JPanel exitAndRegisterButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		exitAndRegisterButtons.setBackground(customBlue);
		
		btnCancel = createButton("Cancelar", textFont, exitAndRegisterButtons);
		btnSave = createButton("Guardar", textFont, exitAndRegisterButtons);
		
		// BARRA VERTICAL DE SCROLL
		JScrollPane scroll = new JScrollPane(componentsPanel);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
		
		// AÑADIR TITULO
		superiorBar.add(title);
		add(superiorBar, BorderLayout.NORTH);
		
		// AÑADIR APARTADO DE DATOS IMPORTANTES
		componentsPanel.add(importantDataSection);
		componentsPanel.add(importantDataGrid);
		
		// AÑADIR APARTADO SEXO
		componentsPanel.add(sexSection);
		componentsPanel.add(sexFlowPanel);
		
		// AÑADIR APARTADO FOTO
		componentsPanel.add(imageSection);
		componentsPanel.add(imagePanel);
		
		// AÑADIR APARTADO DE PRIVACIDAD
		componentsPanel.add(privacySection);
		componentsPanel.add(termsAndConditionsPanel);
		
		// AÑADIR BOTONES DE REGISTRAR Y SALIR
		exitAndRegisterButtons.add(btnCancel);
		exitAndRegisterButtons.add(btnSave);
		componentsPanel.add(exitAndRegisterButtons);
		
		return scroll;
    }

    public void chooseImage() {
		String lastDirectory = Config.get("registration.image.last.directory", System.getProperty("user.home"));
		
		JFileChooser chooser = new JFileChooser(lastDirectory);
		chooser.setDialogTitle("Seleccionar imagen");
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png");
		chooser.setFileFilter(filter);
		
		int option = chooser.showOpenDialog(this);
		
		if(option == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			
			selectedImagePath = file.getAbsolutePath();
			lastDirectory = file.getParent();
			
			Config.set("registration.image.last.directory", lastDirectory);
			
			lblImageName.setText(file.getName());
			
			ImageIcon icon = new ImageIcon(selectedImagePath);
			Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			
			lblImagePreview.setIcon(new ImageIcon(img));
		}
		
	}
    
    private JPanel createTitlePanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(0, 31, 84));

        JLabel label = new JLabel("FORMULARIO DE USUARIO");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Verdana", Font.BOLD, 20));

        panel.add(label);

        return panel;
    }
    
    private JLabel createLabel(String lblText, Font font) {
	
		JLabel label = new JLabel(lblText);
		
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setVisible(true);
		label.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		
		return label;
    }
    
    private JTextField createTextField(JPanel panel) {
        JTextField field = new JTextField();
        field.setFont(new Font("Verdana", Font.BOLD, 12));
        panel.add(field);
        return field;
    }

    private JPasswordField createPasswordField(JPanel panel) {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Verdana", Font.BOLD, 12));
        panel.add(field);
        return field;
    }

    private JRadioButton createRadio(String text, ButtonGroup group, JPanel panel) {
        JRadioButton rb = new JRadioButton(text);
        rb.setOpaque(false);
        rb.setForeground(Color.WHITE);
        rb.setFont(new Font("Verdana", Font.BOLD, 12));

        if(group != null) {
            group.add(rb);
        }

        if(panel != null) {
            panel.add(rb);
        }
        
        return rb;
    }

    private JButton createButton(String text, Font font, JPanel panel) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        panel.add(btn);
        return btn;
    }
    
    private JLabel createErrorLabel(JPanel panel) {
		
		JLabel errorLabel = new JLabel();
		
		errorLabel.setFont(new Font("Verdana", Font.BOLD, 11));
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
		panel.add(errorLabel);
		
		return errorLabel;
	} 
}
