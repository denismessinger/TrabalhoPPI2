package projetoppi;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import projeto_ppi.dao.ManuaisDAO;
import projeto_ppi.dao.OperacaoDAO;
import projeto_ppi.pojo.Manuais;
import projeto_ppi.pojo.Operacao;

/**
 *
 * @author Seven
 */
public class ManuaisMenuController  implements Initializable {
    
    @FXML private Button excluir1;
    @FXML private Button adicionar1;
    @FXML private Button excluir;
    @FXML private Button adicionar;
    @FXML private ComboBox maquinas;
    @FXML private ComboBox operacao;
    @FXML private Button selecionar;
    @FXML private Button selecionar2;
    @FXML private Button voltar;
    @FXML private Button voltar1;
    public ArrayList<Manuais> manuais = new ArrayList<>();
    public ArrayList<Manuais> manuais2 = new ArrayList<>();
    public ObservableList<Manuais> obs;
    public ObservableList<Operacao> obs1;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        carregar1();
        if(ProjetoPPI.usuario.getPermissao()!=1){
            excluir.setVisible(false);
            adicionar.setVisible(false);
        }
    }
    
    void carregar1 (){
        Manuais manual = new Manuais();
        ManuaisDAO manual2 = new ManuaisDAO();
        OperacaoDAO op = new OperacaoDAO();
                
        
        obs = FXCollections.observableArrayList(manual2.buscar());
        maquinas.setItems(obs);
        
        obs1 = FXCollections.observableArrayList(op.buscar());
        operacao.setItems(obs1);
    }
    
    @FXML
    void selecionar(ActionEvent event) {
        Manuais manual = new Manuais();
        manual = (Manuais)maquinas.getSelectionModel().getSelectedItem();
        File arquivo = new File(manual.getArquivo());

        try{
            Desktop.getDesktop().open(arquivo);
        }catch(Exception ex){
            ex.printStackTrace();    
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void selecionar2(ActionEvent event) {
        Operacao op = new Operacao();
        op = (Operacao)operacao.getSelectionModel().getSelectedItem();
        File arquivo = new File(op.getArquivo());

        try{
            Desktop.getDesktop().open(arquivo);
        }catch(Exception ex){
            ex.printStackTrace();    
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        Parent fxmlMenus = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene Menuscene = new Scene(fxmlMenus, 457, 307);
        ProjetoPPI.stage.setScene(Menuscene);
        ProjetoPPI.stage.show();
    }
    @FXML
    void voltar1(ActionEvent event) throws IOException {
        Parent fxmlMenus = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene Menuscene = new Scene(fxmlMenus, 457, 307);
        ProjetoPPI.stage.setScene(Menuscene);
        ProjetoPPI.stage.show();
    }
    
    @FXML
    void excluir(ActionEvent event) {
        ManuaisDAO manual2 = new ManuaisDAO();
        Manuais list = (Manuais)maquinas.getSelectionModel().getSelectedItem();
        if(list!=null){
            try{
                System.out.println(list.getId());
                manual2.excluir(list.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        carregar1();
    }
    
    @FXML
    void adicionar(ActionEvent event) throws IOException {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione manual de máquina");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDG", "*.pdf"));        
        File file = fileChooser.showOpenDialog(null);
        if(file!=null){
            Manuais man = new Manuais();
            man.setArquivo(file.getCanonicalPath());
            String descricao = file.getName().substring(0, 25);
            man.setDescricao(descricao);
            ManuaisDAO man1 = new ManuaisDAO();
            man1.salvar(man);
            carregar1();
        }      
    }
    @FXML
    void excluir1(ActionEvent event) {
        OperacaoDAO op = new OperacaoDAO();
        Operacao list = (Operacao)operacao.getSelectionModel().getSelectedItem();
        if(list!=null){
            try{
                op.excluir(list.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        carregar1();
    }
    
    @FXML
    void adicionar1(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione manual de operação");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDG", "*.pdf"));        
        File file = fileChooser.showOpenDialog(null); 
        if(file!=null){
            Operacao op = new Operacao();
            op.setArquivo(file.getCanonicalPath());
            String descricao = file.getName().substring(0, 25);
            op.setDescricao(descricao);
            OperacaoDAO op1 = new OperacaoDAO();
            op1.salvar(op);
            carregar1();
        }
    }
    
}