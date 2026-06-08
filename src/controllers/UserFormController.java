package controllers;

import javax.swing.JOptionPane;

import models.User;
import repository.UserRepository;
import views.UserFormDialog;
/**
 * Controlador encargado de gestionar la logica del formulario modal para la creacion y edicion de usuarios.
 * Administra la precarga de datos en la interfaz, las validaciones de negocio en los campos de entrada,
 * la verificación de registros duplicados y la bandera de éxito de guardado.
 * @author Hugo 
 * @author Emiliano 
 * @version 1.0
 */
public class UserFormController {

    private UserFormDialog view;

    private User user;

    private boolean saved = false;

    
    /**
     * Constructor del controlador del formulario de usuario.
     * Vincula los componentes de la vista, almacena la instancia del usuario a procesar,
     * registra los listeners de eventos y realiza la precarga de datos si aplica.
     * * @param view Vista de la interfaz grafica del dialogo.
     * @param user el objeto que se va a editar, o null si es una inserción nueva.
     */
    public UserFormController(UserFormDialog view, User user) {

        this.view = view;
        this.user = user;

        registerListeners();
        loadData();
    }
    /**
     * Registra los listeners de eventos  para los botones
     * de confirmacion y cancelación del formulario.
     */
    private void registerListeners() {

        view.getBtnSave().addActionListener(e -> handleSave());
        view.getBtnCancel().addActionListener(e -> handleCancel());
    }
    /**
     * Rellena los campos de texto del formulario con los atributos del usuario 
     * únicamente cuando el controlador se inicializa en modo de edicion.
     */
    private void loadData() {

        if(user != null) {

            view.getTxtFieldName().setText(user.getName());
            view.getTxtFieldEmail().setText(user.getEmail());

        }
    }
    /**
     * Valida de forma rigurosa la integridad de los datos ingresados en los campos del formulario.
     * <p>Comprueba que ningún campo obligatorio esté vacío, evalúa el formato básico del correo electrónico 
     * y verifica la coincidencia simétrica entre la contraseña y su confirmación. 
     * Activa y actualiza las etiquetas de error visuales en la interfaz según las reglas infringidas.</p>
     * * @param user objeto temporal  con la información recolectada de la vista.
     * @return true si el formulario no presenta errores de validación; false en caso contrario.
     */
    private boolean validateForm(User user) {

        resetErrorLabels();

        boolean valid = true;

        if(user.getName().trim().isEmpty()) {

            view.getLblErrorFieldName().setVisible(true);

            valid = false;
        }

        if(user.getEmail().trim().isEmpty()) {

            view.getLblErrorFieldEmail().setVisible(true);
            valid = false;
        }else if(!user.getEmail().contains("@")){
        		
        		view.getLblErrorFieldEmail().setText("El correo no es válido");
        		view.getLblErrorFieldEmail().setVisible(true);
        		valid = false;
        }

        if(user.getPassword().trim().isEmpty()) {

            view.getLblErrorFieldPassword().setVisible(true);
            valid = false;
        }

        if(view.getPwdConfirmPassword().trim().isEmpty()) {

            view.getLblErrorFieldConfirmPassword().setVisible(true);
            valid = false;
        }

        if(!user.getPassword().equals(view.getPwdConfirmPassword())) {

            view.getLblErrorFieldConfirmPassword().setText("Las contraseñas no coinciden");
            view.getLblErrorFieldConfirmPassword().setVisible(true);

            valid = false;
        }

        return valid;
    }
    /**
     * Procesa la acción de guardado del formulario.
     * <p>Construye un usuario temporal a partir de las entradas de la interfaz y lo somete a la validación de formato. 
     * Si es correcto, evalua el contexto operacional a traves del repositorio de datos</p>
     * <p>Si se cumplen las condiciones de unicidad, actualiza el estado, marca la bandera de exito y destruye el diálogo.</p>
     */
    private void handleSave() {

        User formUser = new User(
                view.getTxtFieldName().getText(),
                view.getTxtFieldEmail().getText(),
                view.getPwdPassword(),
                view.getSex(),
                view.getRole()
        );

        if(validateForm(formUser)) {

            try {

                UserRepository userRepo = new UserRepository();

                if(user == null) {

                    if(userRepo.searchUser( formUser.getName(), formUser.getEmail()) != null) {

                        JOptionPane.showMessageDialog(
                        		view,
                        		"El nombre o correo ya está registrado."
                        		);
                        return;
                    }

                    user = formUser;
                }
                
                else {

                    if(userRepo.existsOtherUser(formUser.getName(), formUser.getEmail(), user.getId())) {

                        JOptionPane.showMessageDialog(
                                view,
                                "El nombre o correo ya está registrado."
                                );
                        return;
                    }

                    user.setName(formUser.getName());
                    user.setEmail(formUser.getEmail());
                    user.setPassword(formUser.getPassword());
                    user.setSex(formUser.getSex());
                }

                saved = true;
                view.dispose();

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * Cancela la operación actual y cierra el cuadro de dialogo sin aplicar ningun cambio.
     */
    private void handleCancel() {

        view.dispose();
    }
    /**
     * Restablece el estado por defecto de la interfaz, ocultando todas las etiquetas de advertencia 
     * de los campos y reconfigurando el mensaje base para la confirmación de la contraseña.
     */
    private void resetErrorLabels() {
        view.getLblErrorFieldName().setVisible(false);
        view.getLblErrorFieldEmail().setVisible(false);
        view.getLblErrorFieldPassword().setVisible(false);
        view.getLblErrorFieldConfirmPassword().setVisible(false);

        view.getLblErrorFieldConfirmPassword().setText("Debe confirmar su contraseña");
    }
    /**
     * Obtiene el estado final de la transacción del formulario.
     * * @return true si los datos pasaron las validaciones y se confirmo el guardado; false en caso contrario.
     */
    public boolean isSaved() {
        return saved;
    }
    /**
     * Devuelve el objeto usuario procesado .
     * * @return el objeto user administrado por este controlador.
     */
    public User getUser() {
        return user;
    }
}