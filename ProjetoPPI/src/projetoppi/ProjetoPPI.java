/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoppi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto_ppi.pojo.Usuario;

/**
 *
 * @author Seven
 */
public class ProjetoPPI extends Application {
    
    public static Stage stage;
    
    private static Scene Loginscene;
    private static Scene Menuscene;
    private static Scene proximoscene;
    private static Scene Listasscene;
    private static Scene Novoscene;
    private static Scene ManuaisMenuscene;
    public static Usuario usuario;
    
    @Override
    public void start(Stage stageP) throws Exception { 
        stage = stageP;
        
        stageP.setTitle("BarbosaMM");
        
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Loginscene = new Scene(fxmlLogin, 307, 200);
        
        stageP.setScene(Loginscene);
        stageP.show();
    }
    
    public static void changeScreen(String tela){
        switch (tela){
            case "login":
                stage.close();
                stage.setScene(Loginscene);
                stage.show();
                break;
            case "menu":
                stage.close();
                stage.setScene(Menuscene);
                stage.show();
                break;
            case "lista":
                stage.close();
                stage.setScene(Listasscene);
                stage.show();
                break;
            case "manual":
                stage.close();
                stage.setScene(ManuaisMenuscene);
                stage.show();
                break;
            case "proximo":
                stage.close();
                stage.setScene(proximoscene);
                stage.show();
                break;
            case "novo":
                stage.close();
                stage.setScene(Novoscene);
                stage.show();
                break;
        }
                
    }
    public static void close(){
        stage.close();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
