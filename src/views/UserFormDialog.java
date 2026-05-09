package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class UserFormDialog extends JDialog {

    private JTextField txtFieldName;
    private JTextField txtFieldEmail;
    private JPasswordField pwdPassword;
    private JPasswordField pwdConfirmPassword;
    
    private JLabel lblErrorFieldName;
	private JLabel lblErrorFieldEmail;
	private JLabel lblErrorFieldPassword;
	private JLabel lblErrorFieldConfirmPassword;

    private ButtonGroup grpSexes;

    private JRadioButton rbMan;
    private JRadioButton rbWoman;
    private JRadioButton rbDoNotSay;

    private JButton btnSave;
    private JButton btnCancel;

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

	public JPasswordField getPwdPassword() {
		return pwdPassword;
	}

	public void setPwdPassword(JPasswordField pwdPassword) {
		this.pwdPassword = pwdPassword;
	}

	public JPasswordField getPwdConfirmPassword() {
		return pwdConfirmPassword;
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

        // PANEL PRINCIPAL
        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(new BoxLayout(componentsPanel, BoxLayout.Y_AXIS));
        componentsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        componentsPanel.setBackground(customBlue);

        // TITULO
        JPanel titlePanel = new JPanel(alignToCenter);
        titlePanel.setBackground(customBlue);

        JLabel title = new JLabel("FORMULARIO DE USUARIO");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana", Font.BOLD, 20));

        titlePanel.add(title);

        // APARTADO DE DATOS
        JPanel dataPanel = new JPanel(alignToCenter);
		
        dataPanel.setOpaque(false);
        dataPanel.add(createLabel("Datos Importantes", subtitleFont));

        // GRID DE DATOS
        JPanel grid = new JPanel(new GridLayout(1,2,10,0));
        grid.setOpaque(false);

        JPanel labels = new JPanel();
        labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
        labels.setOpaque(false);

        JPanel fields = new JPanel();
        fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));

        // LABELS
        labels.add(createLabel("Nombre", textFont));
        labels.add(createLabel("Email", textFont));
        labels.add(createLabel("Contraseña", textFont));
        labels.add(createLabel("Confirmar", textFont));

        // CAMPOS
        txtFieldName = createTextField(fields);
        lblErrorFieldName = createErrorLabel("El nombre es obligatorio", textFont);
        fields.add(lblErrorFieldName);

        txtFieldEmail = createTextField(fields);
        lblErrorFieldEmail = createErrorLabel("El correo es obligatorio", textFont);
        fields.add(lblErrorFieldEmail);
        
        pwdPassword = createPasswordField(fields);
        lblErrorFieldPassword = createErrorLabel("La contraseña es obligatoria", textFont);
        fields.add(lblErrorFieldPassword);
        
        pwdConfirmPassword = createPasswordField(fields);
        lblErrorFieldConfirmPassword = createErrorLabel("Debe confirmar su contraseña", textFont);
        fields.add(lblErrorFieldConfirmPassword);

        grid.add(labels);
        grid.add(fields);

        // SEXO
        JPanel sexPanel = new JPanel(alignToCenter);
		
        sexPanel.setOpaque(false);
        sexPanel.add(createLabel("Sexo", subtitleFont));

        grpSexes = new ButtonGroup();

        rbMan = createRadio("Hombre", grpSexes, sexPanel);
        rbWoman = createRadio("Mujer", grpSexes, sexPanel);
        rbDoNotSay = createRadio("Prefiero no decir", grpSexes, sexPanel);

        // BOTONES
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.setBackground(customBlue);

        btnSave = createButton("Guardar", textFont, buttons);
        btnCancel = createButton("Cancelar", textFont, buttons);

        // SCROLL
        JScrollPane scroll = new JScrollPane(componentsPanel);
        scroll.setHorizontalScrollBar(null);

        add(titlePanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        componentsPanel.add(grid);
        componentsPanel.add(sexPanel);
        componentsPanel.add(buttons);
        
        return scroll; 
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

        group.add(rb);
        panel.add(rb);

        return rb;
    }

    private JButton createButton(String text, Font font, JPanel panel) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        panel.add(btn);
        return btn;
    }
    
    private JLabel createErrorLabel(String lblText, Font font) {
    	
    	JLabel label = new JLabel(lblText);
    	
    	label.setFont(font);
    	label.setForeground(Color.RED);
    	label.setVisible(false);
    	
    	return label;
    }
}
