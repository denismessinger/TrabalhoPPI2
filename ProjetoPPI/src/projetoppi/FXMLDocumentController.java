/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoppi;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import projeto_ppi.dao.UsuarioDAO;
import projeto_ppi.pojo.Usuario;
/**
 *
 * @author Seven
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button button;

    @FXML
    private Label label;
    
    @FXML
    private TextField login;

    @FXML
    private PasswordField senha;
    
    @FXML
    private Button sair;
    
    @FXML
    private AnchorPane painel;
    
    
    @FXML
    private void login(ActionEvent event) throws IOException {
        if(testarLogin(login.getText(),senha.getText())){
            Parent fxmlMenus = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene Menuscene = new Scene(fxmlMenus, 457, 307);
            ProjetoPPI.stage.setScene(Menuscene);
            ProjetoPPI.stage.show();
            
        }else{
            JOptionPane.showMessageDialog(null, "Senha Incorreta!");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    void sair(ActionEvent event) {
        System.exit(0);
    }
    
    public boolean testarLogin(String login, String senha){
        UsuarioDAO usu = new UsuarioDAO();    
        Usuario usuario = new Usuario();
        List<Usuario> lista = usu.buscar();
        for(int i=0; i<lista.size();i++){
            if(lista.get(i).getUsuario().equals(login)){
                if(lista.get(i).getSenha().equals(senha)){
                    ProjetoPPI.usuario=lista.get(i);
                    return true;
                }
            }
        }   
            return false;
    }
    
}
