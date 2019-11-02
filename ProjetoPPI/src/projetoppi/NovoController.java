package projetoppi;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import projeto_ppi.dao.CalendarioDAO;
import projeto_ppi.pojo.Calendario;
import projeto_ppi.pojo.Lista;
import projeto_ppi.pojo.Referencia;

public class NovoController implements Initializable{
    
    @FXML private Button cancelar;
    @FXML private Button pronto;
    @FXML private AnchorPane visivel;
    @FXML private AnchorPane principal;
    @FXML private Button excluir;
    @FXML private Button adicionar;
    @FXML private Button voltar;
    @FXML private Button editar;
    @FXML private Button proximos;
    @FXML private TextField referencia2;
    @FXML private TextField descricao2;   
    @FXML private DatePicker chegada2;
    @FXML private DatePicker inicio2;
    @FXML private DatePicker entrega2;
    @FXML private TextField maquina2;
    @FXML private TableView<Calendario> tabela;   
    @FXML private TableColumn<Calendario, String> referencia;
    @FXML private TableColumn<Calendario, String> descricao;
    @FXML private TableColumn<Calendario, String> chegada;
    @FXML private TableColumn<Calendario, String> maquina;
    @FXML private TableColumn<Calendario, String> usinagem;
    @FXML private TableColumn<Calendario, String> ENTREGA;
    public List<Calendario> listas = new ArrayList<>();
    public CalendarioDAO dao = new CalendarioDAO();
    public ObservableList<Calendario> lista = FXCollections.observableArrayList();
    public boolean add;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        referencia.setCellValueFactory(
            new PropertyValueFactory<>("referencia"));
        descricao.setCellValueFactory(
             new PropertyValueFactory<>("descricao"));
        maquina.setCellValueFactory(
            new PropertyValueFactory<>("maquina"));
        chegada.setCellValueFactory(
             new PropertyValueFactory<>("chegada"));
        usinagem.setCellValueFactory(
            new PropertyValueFactory<>("usinagem"));
        ENTREGA.setCellValueFactory(
             new PropertyValueFactory<>("entrega"));
        carregar();
    }
    
    @FXML
    void cancelar(ActionEvent event) {
        visivel.setVisible(false);
        principal.setVisible(true);
    }
    
    @FXML
    void editar(ActionEvent event) {
        visivel.setVisible(true);
        principal.setVisible(false);
        add=false;
    }
    
    @FXML
    void proximos(ActionEvent event) throws IOException {
        Parent fxmlProximos = FXMLLoader.load(getClass().getResource("proximo.fxml"));
        Scene proximoscene = new Scene(fxmlProximos, 600, 277);
        ProjetoPPI.stage.setScene(proximoscene);
        ProjetoPPI.stage.show();
    }
    
    @FXML
    void pronto(ActionEvent event) {
        if(add){
            Calendario cale = new Calendario();
            cale.setReferencia(referencia2.getText());
            cale.setDescricao(descricao2.getText());
            cale.setMaquina(maquina2.getText());
            cale.setChegada(chegada2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cale.setUsinagem(inicio2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cale.setEntrega(entrega2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            dao.salvar(cale);
            carregar();
        }else{
            Calendario cale = new Calendario();
            cale = (Calendario)tabela.getSelectionModel().getSelectedItem();
            if(cale!=null){
                cale.setReferencia(referencia2.getText());
                cale.setDescricao(descricao2.getText());
                cale.setMaquina(maquina2.getText());
                cale.setChegada(chegada2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                cale.setUsinagem(inicio2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                cale.setEntrega(entrega2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                dao.atualizar(cale);
                carregar();
            }else{
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Campo Inválido");
                dialogoInfo.setContentText("Selecione uma lista para editar!");
                dialogoInfo.showAndWait();
            }
        }
    }
    
    @FXML
    void adicionar(ActionEvent event) {    
        visivel.setVisible(true);
        principal.setVisible(false);
        add=true;
    }

    @FXML
    void excluir(ActionEvent event) {
        Calendario list = tabela.getSelectionModel().getSelectedItem();
        if(list!=null){
            try{
                dao.excluir(list.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        carregar();
        }else{
            
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
        for (Calendario list : listas){
            lista.add(list);
        }
        try{
            tabela.setItems(lista);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}