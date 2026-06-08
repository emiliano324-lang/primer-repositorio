package controllers;

import javax.swing.JOptionPane;

import models.User;
import repository.UserRepository;
import views.UserFormDialog;

public class UserFormController {

    private UserFormDialog view;

    private User user;

    private boolean saved = false;

    public UserFormController(UserFormDialog view, User user) {

        this.view = view;
        this.user = user;

        registerListeners();
        loadData();
    }

    private void registerListeners() {

        view.getBtnSave().addActionListener(e -> handleSave());
        view.getBtnCancel().addActionListener(e -> handleCancel());
    }

    private void loadData() {

        if(user != null) {

            view.getTxtFieldName().setText(user.getName());
            view.getTxtFieldEmail().setText(user.getEmail());

        }
    }

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

    private void handleCancel() {

        view.dispose();
    }

    private void resetErrorLabels() {
        view.getLblErrorFieldName().setVisible(false);
        view.getLblErrorFieldEmail().setVisible(false);
        view.getLblErrorFieldPassword().setVisible(false);
        view.getLblErrorFieldConfirmPassword().setVisible(false);

        view.getLblErrorFieldConfirmPassword().setText("Debe confirmar su contraseña");
    }

    public boolean isSaved() {
        return saved;
    }

    public User getUser() {
        return user;
    }
}