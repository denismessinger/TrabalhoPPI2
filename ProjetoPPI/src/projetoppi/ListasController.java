package projetoppi;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import projeto_ppi.dao.ListaDAO;
import projeto_ppi.pojo.Lista;


public class ListasController implements Initializable{
    
    @FXML private Label texto;
    @FXML private AnchorPane visivel;
    @FXML private TextField referencia1;
    @FXML private TextField descricao1;
    @FXML private Button pronto;
    @FXML private Button editar;
    @FXML private Button voltar1;
    @FXML private TableView<Lista> tabela;
    @FXML private Button selecionar;
    @FXML private Button excluir;   
    @FXML private TableColumn<Lista, Integer> id;
    @FXML private TableColumn<Lista, String> arquivo; 
    @FXML private TableColumn<Lista, String> referencia;
    @FXML private TableColumn<Lista, String> descricao;
    @FXML private AnchorPane principal;
    public List<Lista> listas = new ArrayList<>();
    public ListaDAO dao = new ListaDAO();
    public ObservableList<Lista> lista = FXCollections.observableArrayList();           
    @FXML private Button adicionar;
    @FXML private Button voltar;
    private boolean add;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        id.setCellValueFactory(
            new PropertyValueFactory<>("id"));
        referencia.setCellValueFactory(
            new PropertyValueFactory<>("referencia"));
        descricao.setCellValueFactory(
             new PropertyValueFactory<>("descricao"));
        carregar();
    }
    
    @FXML
    void adicionar(ActionEvent event) {
        visivel.setVisible(true);
        principal.setVisible(false);
        add=true;
        texto.setText("ADICIONAR");
    }

    @FXML
    void excluir(ActionEvent event) {
        Lista list = tabela.getSelectionModel().getSelectedItem();
        if(list!=null){
            try{
                dao.excluir(list.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        carregar();

    }

    @FXML
    void selecionar(ActionEvent event) throws NullPointerException{
        Lista lista = new Lista();
        lista = (Lista)tabela.getSelectionModel().getSelectedItem();
        if(lista!=null){
            File arquivo = new File(lista.getArquivo());
            try{
                Desktop.getDesktop().open(arquivo);
            }catch(Exception ex){
                ex.printStackTrace();    
            }
        }
    }
    

    @FXML
    void voltar(ActionEvent event) throws IOException {
        Parent fxmlMenus = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene Menuscene = new Scene(fxmlMenus, 457, 307);
        ProjetoPPI.stage.setScene(Menuscene);
        ProjetoPPI.stage.show();
    }
    
    public void carregar(){
        if(!lista.isEmpty()){
            lista.clear();
        }
        listas = dao.buscar();
        System.out.print(listas.size());
        for (Lista list : listas){
            lista.add(list);
        }
        try{
            tabela.setItems(lista);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    void pronto(ActionEvent event) throws IOException {
        if(add){
            if(testarTxt()){
                Lista list = new Lista();
                gerarDoc();
                list.setArquivo(referencia1.getText()+".docx");
                list.setDescricao(descricao1.getText());
                list.setReferencia(referencia1.getText());
                dao.salvar(list);
                visivel.setVisible(false);
                principal.setVisible(true);
                carregar();
            }else{
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Campo Inválido");
                dialogoInfo.setContentText("Inserir os campos corretamente!");
                dialogoInfo.showAndWait();
            }
        }else{
            if(testarTxt()){
                Lista lista = new Lista();
                lista = (Lista)tabela.getSelectionModel().getSelectedItem();
                if(lista!=null){
                    File arquivo = new File(lista.getArquivo());
                    arquivo.delete();
                    gerarDoc();
                    lista.setArquivo(referencia1.getText()+".docx");
                    lista.setDescricao(descricao1.getText());
                    lista.setReferencia(referencia1.getText());
                    dao.atualizar(lista);
                    visivel.setVisible(false);
                    principal.setVisible(true);
                    carregar();
                }else{
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Campo Inválido");
                    dialogoInfo.setContentText("Selecione uma lista para editar!");
                    dialogoInfo.showAndWait();
                }
            }else{
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Campo Inválido");
                dialogoInfo.setContentText("Preencher ambos os campos!");
                dialogoInfo.showAndWait();
            }
        }
    }


    @FXML
    void cancelar(ActionEvent event) {
        visivel.setVisible(false);
        principal.setVisible(true);
    }
    
    public void gerarDoc() throws IOException{
        FileOutputStream out = null;
        XWPFDocument doc = new XWPFDocument();
        File file = new File(referencia1.getText()+".docx");
        try{
            if(file.exists()){
                
            }else{
                out = new FileOutputStream(new File(referencia1.getText()+".docx"));
                XWPFParagraph par = doc.createParagraph();
                XWPFRun run = par.createRun();
                run.setText("Referencia: "+referencia1.getText());
                run.addBreak();
                run.addBreak();
                XWPFParagraph par2 = doc.createParagraph();
                XWPFRun run2 = par2.createRun();
                run.setText("Descrição: "+descricao1.getText());
                doc.write(out);
            }
        }catch(FileNotFoundException ex){
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Arquibo inválido!");
            dialogoInfo.setContentText("Arquivo não encontradp!!");
            dialogoInfo.showAndWait();
        }
    }
    @FXML
    void editar(ActionEvent event) {
        visivel.setVisible(true);
        principal.setVisible(false);
        add=false;
        texto.setText("EDITAR");
    }
    public boolean testarTxt(){
        if(!referencia.getText().equals("")){
            if(!descricao.getText().equals("")){
                return true;
            }
        }else{
            return false;
        }
        return false;
    }
}
