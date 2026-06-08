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

import config.Config;
import enums.Role;
import enums.Sex;

/**
 * Cuadro de dialogo para crear o editar usuarios.
 *
 * <p>Esta ventana modal permite capturar y modificar la informacion
 * de un usuario mediante un formulario que incluye datos personales,
 * credenciales de acceso, sexo y rol dentro del sistema.</p>
 *
 * <p>Tambien proporciona componentes para mostrar mensajes de error
 * durante la validación de los datos ingresados.</p>
 *
 * @author Hugo 
 * @author Emiliano
 * @version 1.0
 */
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

    private ButtonGroup grpRoles;
	private JRadioButton rbAdmin;
	private JRadioButton rbClient;
    
 
    
    private JButton btnSave;
    private JButton btnCancel;

    // GETTERS
    /**
     * Obtiene el sexo seleccionado en el formulario.
     *
     * @return sexo seleccionado por el usuario.
     */
	public Sex getSex() {

        if(rbMan.isSelected()) {
            return Sex.MALE;
        }

        if(rbWoman.isSelected()) {
            return Sex.FEMALE;
        }

        return Sex.OTHER;
    }
	/**
	 * Obtiene el rol seleccionado en el formulario.
	 *
	 * @return rol asignado al usuario.
	 */
	public Role getRole() {
		if(rbAdmin.isSelected()) {
			return Role.ADMIN;
		}
		
		return Role.CLIENT;
	}
	/**
     * Obtiene la etiqueta utilizada para la vista previa de la imagen de perfil.
     *
     * @return Componente JLabel de la vista previa de la imagen.
     */
	public JLabel getLblImagePreview() {
		return lblImagePreview;
	}
	/**
     * Obtiene la etiqueta que despliega el nombre del archivo de imagen cargado.
     *
     * @return Componente JLabel con el nombre de la imagen.
     *
     */
	public JLabel getLblImageName() {
		return lblImageName;
	}
	/**
     * Obtiene la etiqueta de error vinculada a la carga de la imagen.
     *
     * @return Componente JLabel para errores de imagen.
     */
	public JLabel getLblErrorImage() {
		return lblErrorImage;
	}
	/**
	 * Obtiene el campo de texto del nombre de usuario.
	 *
	 * @return campo de nombre.
	 */
	public JTextField getTxtFieldName() {
		return txtFieldName;
	}
	/**
     * Establece el campo de texto del nombre de usuario.
     *
     * @param txtFieldName Nuevo campo de texto para el nombre.
     */
	public void setTxtFieldName(JTextField txtFieldName) {
		this.txtFieldName = txtFieldName;
	}
	/**
	 * Obtiene el campo de texto del correo electrónico.
	 *
	 * @return campo de correo electrónico.
	 */
	public JTextField getTxtFieldEmail() {
		return txtFieldEmail;
	}
	/**
     * Establece el campo de texto del correo electrónico.
     *
     * @param txtFieldEmail Nuevo campo de texto para el email.
     */
	public void setTxtFieldEmail(JTextField txtFieldEmail) {
		this.txtFieldEmail = txtFieldEmail;
	}
	/**
	 * Obtiene la contraseña ingresada.
	 *
	 * @return contraseña del usuario.
	 */
	public String getPwdPassword() {
		return String.valueOf(pwdPassword.getPassword());
	}
	/**
     * Establece el componente para capturar la contraseña.
     *
     * @param pwdPassword Nuevo componente JPasswordField para la contraseña.
     */
	public void setPwdPassword(JPasswordField pwdPassword) {
		this.pwdPassword = pwdPassword;
	}
	/**
     * Obtiene la confirmacion de la contraseña ingresada.
     *
     * @return Confirmación de la contraseña del usuario.
     */
	public String getPwdConfirmPassword() {
		return String.valueOf(pwdConfirmPassword.getPassword());
	}
	/**
     * Establece el componente para capturar la confirmacion de la contraseña.
     *
     * @param Nuevo componente JPasswordField para confirmar la contraseña.
     */
	public void setPwdConfirmPassword(JPasswordField pwdConfirmPassword) {
		this.pwdConfirmPassword = pwdConfirmPassword;
	}
	/**
     * Obtiene la etiqueta encargada de mostrar errores de validacion en el nombre.
     *
     * @return JLabel de error para el nombre.
     */
	public JLabel getLblErrorFieldName() {
		return lblErrorFieldName;
	}
	/**
     * Establece la etiqueta para los errores de validación del nombre.
     *
     * @param nuevo JLabel de error para el nombre.
     */
	public void setLblErrorFieldName(JLabel lblErrorFieldName) {
		this.lblErrorFieldName = lblErrorFieldName;
	}
	/**
     * Obtiene la etiqueta encargada de mostrar errores de validación en el email.
     *
     * @return JLabel de error para el email.
     */
	public JLabel getLblErrorFieldEmail() {
		return lblErrorFieldEmail;
	}
	/**
     * Establece la etiqueta para los errores de validación del email.
     *
     * @param lblErrorFieldEmail Nuevo JLabel de error para el email.
     */
	public void setLblErrorFieldEmail(JLabel lblErrorFieldEmail) {
		this.lblErrorFieldEmail = lblErrorFieldEmail;
	}
	/**
     * Obtiene la etiqueta encargada de mostrar errores de validación en la contraseña.
     *
     * @return JLabel de error para la contraseña.
     */
	public JLabel getLblErrorFieldPassword() {
		return lblErrorFieldPassword;
	}
	/**
     * Establece la etiqueta para los errores de validación de la contraseña.
     *
     * @param JLabel de error para la contraseña.
     */
	public void setLblErrorFieldPassword(JLabel lblErrorFieldPassword) {
		this.lblErrorFieldPassword = lblErrorFieldPassword;
	}
	/**
     * Obtiene la etiqueta encargada de mostrar errores en la confirmación de contraseña.
     *
     * @return JLabel de error para la confirmación de contraseña.
     */
	public JLabel getLblErrorFieldConfirmPassword() {
		return lblErrorFieldConfirmPassword;
	}
	/**
     * Establece la etiqueta para los errores de validación de confirmación de contraseña.
     *
     * @param lblErrorFieldConfirmPassword nuevo JLabel de error para la confirmación.
     */
	public void setLblErrorFieldConfirmPassword(JLabel lblErrorFieldConfirmPassword) {
		this.lblErrorFieldConfirmPassword = lblErrorFieldConfirmPassword;
	}
	/**
	 * Obtiene el boton para guardar la informacion del usuario.
	 *
	 * @return botón guardar.
	 */
	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}
	/**
	 * Obtiene el boton para cancelar la operacion actual.
	 *
	 * @return botón cancelar.
	 */
	public JButton getBtnCancel() {
		return btnCancel;
	}
	/**
     * Establece el botón para cancelar la operación actual.
     *
     * @param btnCancel Nuevo botón para la acción de cancelar.
     */
	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}
	/**
     * Establece el grupo de botones que gestiona las opciones de sexo.
     *
     * @param grpSexes Nuevo ButtonGroup para las opciones de sexo.
     */
	public void setGrpSexes(ButtonGroup grpSexes) {
		this.grpSexes = grpSexes;
	}
	/**
     * Establece el botón de opción para el sexo masculino.
     *
     * @param rbMan JRadioButton para la opción de Hombre.
     */
	public void setRbMan(JRadioButton rbMan) {
		this.rbMan = rbMan;
	}
	/**
     * Establece el botón de opción para el sexo femenino.
     *
     * @param rbWoman JRadioButton para la opción de Mujer.
     */
	public void setRbWoman(JRadioButton rbWoman) {
		this.rbWoman = rbWoman;
	}
	/**
     * Establece el botón de opción para no especificar sexo.
     *
     * @param rbDoNotSay JRadioButton para la opción "Prefiero no decir".
     */
	public void setRbDoNotSay(JRadioButton rbDoNotSay) {
		this.rbDoNotSay = rbDoNotSay;
	}
	/**
     * Obtiene el grupo de botones encargado de las opciones de sexo.
     *
     * @return El objeto ButtonGroup con las opciones de sexo.
     */
	public ButtonGroup getGrpSexes() {
		return grpSexes;
	}
	/**
	 * Crea e inicializa el formulario de usuario.
	 *
	 * @param parent ventana padre que contiene este cuadro de dialogo.
	 */
	public UserFormDialog(JFrame parent) {
        super(parent, true);

        setTitle("Formulario Usuario");
        setSize(450, 450);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        add(initializeComponents());
    }
	/**
     * Inicializa, diseña y ensambla de manera interna todos los componentes de la interfaz.
     * Crea secciones para datos importantes, sexo, roles, términos legales y acciones finales.
     *
     * @return Un panel con scroll que contiene toda la interfaz construida.
     */
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
		
		JPanel roleSection = new JPanel(alignToCenter);

		roleSection.setOpaque(false);
		roleSection.add(createLabel("Rol", subtitleFont));

		JPanel roleFlowPanel = new JPanel(alignToCenter);

		roleFlowPanel.setOpaque(false);
		
		grpRoles = new ButtonGroup();

		rbAdmin = createRadio("Administrador", grpRoles, roleFlowPanel);
		rbClient = createRadio("Cliente", grpRoles, roleFlowPanel);
		
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
		
		// AÑADIR APARTADO ROLES
		componentsPanel.add(roleSection);
		componentsPanel.add(roleFlowPanel);

		
		// AÑADIR APARTADO FOTO
	
		
		// AÑADIR APARTADO DE PRIVACIDAD
		componentsPanel.add(privacySection);
		componentsPanel.add(termsAndConditionsPanel);
		
		// AÑADIR BOTONES DE REGISTRAR Y SALIR
		exitAndRegisterButtons.add(btnCancel);
		exitAndRegisterButtons.add(btnSave);
		componentsPanel.add(exitAndRegisterButtons);
		
		return scroll;
    }

    /**
     * Genera un panel con un título predefinido.
     *
     * @return JPanel con estilo azul y texto centrado.
     */
    private JPanel createTitlePanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(0, 31, 84));

        JLabel label = new JLabel("FORMULARIO DE USUARIO");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Verdana", Font.BOLD, 20));

        panel.add(label);

        return panel;
    }
    /**
     *Generador de JLabels con color y tamaño personalizado.
     *
     * @param lblText Texto a desplegar en la etiqueta.
     * @param font    Fuente a aplicar.
     * @return Jlabel personalizado.
     */
    private JLabel createLabel(String lblText, Font font) {
	
		JLabel label = new JLabel(lblText);
		
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setVisible(true);
		label.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		
		return label;
    }
    /**
     * Genera cueadros de texto con color y tamaño personalizado.
     *
     * @param panel Panel contenedor donde se incrustara el campo creado.
     * @return cuadro de texto personalizado.
     */
    private JTextField createTextField(JPanel panel) {
        JTextField field = new JTextField();
        field.setFont(new Font("Verdana", Font.BOLD, 12));
        panel.add(field);
        return field;
    }
    /**
     *Genera cuadros de texto para contraseñas perosnalizados.
     *
     * @param panel Panel contenedor donde se incrustara el campo de contraseña.
     * @return JpassowrdField personalizado.
     */
    private JPasswordField createPasswordField(JPanel panel) {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Verdana", Font.BOLD, 12));
        panel.add(field);
        return field;
    }
    /**
     * Generador de radio button personalizado
     *
     * @param text  texto del Radio Button.
     * @return JRadioButton personalizado.
     */
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
    /**
     * Generador de botones con colores y tamaño personalizado.
     *
     * @param text  texto del boton.
     * @param font  fuente tipográfica a aplicar.
     * @param panel panel de destino para agregar el boton.
     * @return boton personalizado.
     */
    private JButton createButton(String text, Font font, JPanel panel) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        panel.add(btn);
        return btn;
    }
    /**
     * Generador de etiquetas de texto de error visibles.
     *
     * @param panel Panel contenedor donde se añade la etiqueta.
     * @return JLabel personalizada.
     */
    private JLabel createErrorLabel(JPanel panel) {
		
		JLabel errorLabel = new JLabel();
		
		errorLabel.setFont(new Font("Verdana", Font.BOLD, 11));
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
		panel.add(errorLabel);
		
		return errorLabel;
	} 
}
