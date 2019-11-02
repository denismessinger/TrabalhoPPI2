/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import projeto_ppi.dao.ReferenciaDAO;
import projeto_ppi.pojo.Referencia;
/**
 *
 * @author Seven
 */
public class proximoController implements Initializable {
    
    
    @FXML private TableColumn<Referencia, String> referencia2;
    @FXML private TableColumn<Referencia, String> data2;
    @FXML private TableView<Referencia> tabela;
    @FXML private AnchorPane visivel;
    @FXML private AnchorPane principal;
    @FXML private Button excluir1;
    @FXML private Button pronto;
    @FXML private TextField referencia;
    @FXML private DatePicker data;
    @FXML private Button voltar;
    @FXML private Button adicionar;
    @FXML private Button editar;
    @FXML private Button excluir;
    public boolean add;
    public List<Referencia> listas = new ArrayList<>();
    public ReferenciaDAO dao = new ReferenciaDAO();
    public ObservableList<Referencia> lista = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        referencia2.setCellValueFactory(
            new PropertyValueFactory<>("referencia"));
        data2.setCellValueFactory(
             new PropertyValueFactory<>("data1"));
        carregar();
        if(ProjetoPPI.usuario.getPermissao()!=1){
            excluir.setVisible(false);
            editar.setVisible(false);
            adicionar.setVisible(false);
        }
    }
    @FXML
    void adicionar(ActionEvent event) {
        visivel.setVisible(true);
        principal.setVisible(false);
        add=true;
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
    void excluir(ActionEvent event) {
        Referencia list = tabela.getSelectionModel().getSelectedItem();
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
    void pronto(ActionEvent event) {
        if(add){
            Referencia ref = new Referencia();
            ref.setReferencia(referencia.getText());
            ref.setData1(data.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            dao.salvar(ref);
            visivel.setVisible(false);
            principal.setVisible(true);
            carregar();
        }
        else{
            Referencia ref = new Referencia();
            ref = (Referencia)tabela.getSelectionModel().getSelectedItem();
            if(ref!=null){
                ref.setReferencia(referencia.getText());
                ref.setData1(data.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                dao.atualizar(ref);
                visivel.setVisible(false);
                principal.setVisible(true);
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
    void voltar(ActionEvent event) throws IOException {
        Parent fxmlNovos = FXMLLoader.load(getClass().getResource("Novo.fxml"));
        Scene Novoscene = new Scene(fxmlNovos, 900, 553);
        ProjetoPPI.stage.setScene(Novoscene);
        ProjetoPPI.stage.show();
    }
    public void carregar(){
        if(!lista.isEmpty()){
            lista.clear();
        }
        listas = dao.buscar();
        System.out.print(listas.size());
        for (Referencia list : listas){
            lista.add(list);
        }
        try{
            tabela.setItems(lista);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
