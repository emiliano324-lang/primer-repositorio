package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.Player;
import models.User;
import repository.UserRepository;
import views.LoginView;
import views.LoginWindow;
import views.MainWindow;
import views.RegistrationWindow;				
/**
 * Controlador encargado de gestionar el proceso de registro para nuevos usuarios en la aplicacion.
 * Administra las validaciones de campos en tiempo real, el control de contraseñas coincidentes,
 * la persistencia en el repositorio y la navegación de retorno.
 * @author Hugo 
 * @author Emiliano 
 * @version 1.0
 */
public class RegistrationController {

    private RegistrationWindow view;
    private UserRepository repository;
    /**
     * Constructor del controlador de registro.
     * Vincula la ventana correspondiente, inicializa el acceso a datos y activa los escuchadores de eventos.
     * * @param view Vista de la interfaz gráfica de registro RegistrationWindow.
     */
    public RegistrationController(RegistrationWindow view) {
        this.view = view;
        this.repository = new UserRepository();
        registrationListener();
    }
    /**
     * Registra los listeners de eventos para los componentes interactivos de la vista.
     */
    private void registrationListener() {

        // NOMBRE
        view.getTxtFieldName().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
            	validateName(); 
            }
            public void removeUpdate(DocumentEvent e) {
            	validateName(); 
            }
            public void changedUpdate(DocumentEvent e) {
            	validateName(); 
            }
        });

        // EMAIL
        view.getTxtFieldEmail().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { 
            validateEmail(); 
            }
            public void removeUpdate(DocumentEvent e) { 
            	validateEmail(); 
            }
            public void changedUpdate(DocumentEvent e) { 
            	validateEmail(); 
           }
        });
        
        //view.getBtnSelectImage().addActionListener(e -> view.chooseImage());
        
        view.getBtnRegistrate().addActionListener(e -> register());

        view.getBtnExit().addActionListener(e -> handleBack());
        
    }

    // VALIDACIONES
    /**
     * Realiza una validación exhaustiva de los atributos de un usuario antes de proceder a guardarlo.
     * Verifica campos vacíos, formato básico de correo electrónico y la coincidencia exacta de contraseñas.
     * * @param user El objeto temporal con los datos capturados en la interfaz.
     * @return true si todos los campos cumplen con los criterios de aceptacion; false en caso de detectar fallos.
     */
    private boolean validateRegistration(User user) {
    	
		boolean errorFound = false;

		// Validar nombre
		if (user.getName().trim().isEmpty()) {

			errorFound = true;

			view.getLblErrorFieldName().setText("El nombre es obligatorio");
			view.getLblErrorFieldName().setVisible(true);
		}

		// Validar correo (campo vacío)
		if (user.getEmail().trim().isEmpty()) {

			errorFound = true;

			view.getLblErrorFieldEmail().setText("El correo es obligatorio");
			view.getLblErrorFieldEmail().setVisible(true);

			// Validar correo (si no contiene @)
		} else if (!user.getEmail().contains("@")) {

			errorFound = true;

			view.getLblErrorFieldEmail().setText("El correo no es válido");
			view.getLblErrorFieldEmail().setVisible(true);
		}

		// Validar contraseña
		if (user.getPassword().trim().isEmpty()) {
			errorFound = true;

			view.getLblErrorFieldPassword().setText("La contraseña es obligatoria");
			view.getLblErrorFieldPassword().setVisible(true);

		} else if (!user.getPassword().isEmpty() && !user.getPassword().equals(view.getConfirmPassword())) {
			errorFound = true;

			view.getLblErrorFieldConfirmPassword().setText("Las contraseñas no coinciden");
			view.getLblErrorFieldConfirmPassword().setVisible(true);
		}

		return !errorFound;
    }
    /**
     * Evalúa de forma aislada el cuadro de texto del nombre para ocultar o mostrar el mensaje de error.
     */
    private void validateName() {
        JTextField txt = view.getTxtFieldName();
        JLabel error = view.getLblErrorFieldName();
        
        if(txt.getText().trim().isEmpty()) {
        	error.setText("El nombre es obligatorio");
        	error.setVisible(true);
        }else {
        	error.setVisible(false);
        }
    }
    /**
     * Evalúa de forma aislada el cuadro de texto del correo electrónico para validar su formato y estado.
     */
    private void validateEmail() {
        JTextField txt = view.getTxtFieldEmail();
        JLabel error = view.getLblErrorFieldEmail();

        if (txt.getText().trim().isEmpty()) {
        	error.setText("El correo es obligatorio");
            error.setVisible(true);
        } else if (!txt.getText().contains("@")) {
        	error.setText("El correo no es válido");
            error.setVisible(false);
        } else {
            error.setVisible(false);
        }
    }

    // REGISTRO
    /**
     * Procesa el flujo de envio del formulario de registro.
     * <p>Limpia etiquetas de advertencia previas, construye una instancia de usuario con los datos de la vista 
     * y, si supera las validaciones de negocio, intenta guardarlo mediante el repositorio. Notifica el resultado 
     * al usuario a través de un cuadro de dialogo y, en caso de exito, destruye la vista redirigiendo al Login.</p>
     */
    private void register() {

	    	view.resetErrorLabels();
	    	
	    	User user = new User(view.getName(), view.getEmail(), view.getPassword(), view.getSex(), view.getRole());
	    	//String imagePathString = saveImage();
	    	
	    	if(validateRegistration(user)) {
	    		
	    		try {
	    			if(repository.save(user)) {
	    				JOptionPane.showMessageDialog(view, "Registro exitoso");
	    			}else {
	    				JOptionPane.showMessageDialog(view, "Ya existe un usuario con el mismo nombre o correo");
	    				return;
	    			}
	    			
	    			handleBack();
	    			view.dispose();
	    			
	    		}catch(IOException e) {
	    			JOptionPane.showMessageDialog(view, e.getMessage());;
	    		}
	    	}
    }
    /**
     * Retorna al usuario al flujo inicial abriendo una nueva ventana de inicio de sesion 
     * y destruyendo el formulario actual.
     */
    private void handleBack() {
        new LoginWindow();
        view.dispose();
    }
    /**
     * Muestra un cuadro de confirmación gráfico para interceptar intenciones de salida total.
     * Si el usuario selecciona afirmativamente, se aborta la ejecución completa del hilo de la aplicacion.
     */
    public void handleClose() {
        int option = JOptionPane.showConfirmDialog(view, "Seguro que quieres salir?");
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
