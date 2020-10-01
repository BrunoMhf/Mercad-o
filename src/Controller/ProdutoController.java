package Controller;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import Model.ConnectionFactory;
import Model.estoque;
import Model.estoqueDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProdutoController {
	
	
	
	@FXML
    private TabPane abas;

    @FXML
    private Tab cadastrar;

    @FXML
    private TextField nomeNovoProduto;

    @FXML
    private DatePicker validadeNovoProduto;

    @FXML
    private TextField marcaNovoProduto;

    @FXML
    private TextField precoNovoProduto;

    @FXML
    private Tab consultar;

    @FXML
    private TextField nomeConsultaProduto;

    @FXML
    private TableView<estoque> produto;

    @FXML
    private TableColumn<estoque, Integer> idProduto;

    @FXML
    private TableColumn<estoque, String> nomeProduto;

    @FXML
    private TableColumn<estoque, Date> validadeProduto;

    @FXML
    private TableColumn<estoque, String> marcaProduto;

    @FXML
    private TableColumn<estoque, BigDecimal> precoProduto;

    @FXML
    private Tab atualizar;

    @FXML
    private TextField nomeAtualizadoProduto;

    @FXML
    private DatePicker validadeAtualizadoProduto;

    @FXML
    private TextField marcaAtualizadoProduto;
    
    @FXML
    private TextField precoAtualizadoProduto;

    

	public void initialize(URL arg0, ResourceBundle arg1) {
		
				
		idProduto.setCellValueFactory(new PropertyValueFactory<estoque,Integer>("id"));
		nomeProduto.setCellValueFactory(new PropertyValueFactory<estoque,String>("nome"));
		validadeProduto.setCellValueFactory(new PropertyValueFactory<estoque,Date>("data_validade"));
		marcaProduto.setCellValueFactory(new PropertyValueFactory<estoque,String>("marca"));
		precoProduto.setCellValueFactory(new PropertyValueFactory<estoque,BigDecimal>("preco"));
			
	}	
    
    @FXML
    void consultarProduto() {
    	
    	estoqueDAO dao = new estoqueDAO();
    	//dao.consultar(produto);
    	
    	List<estoque> resultado = dao.consultar(nomeConsultaProduto.getText());
    	if (resultado.isEmpty()) {
    		exibirDialogoInformacao("Nenhum produto encontrado!");
    	}
    }

    @FXML
    void deletaProduto() {

    }

    @FXML
    void exibirAbaAtualizacao() {

    }

    @FXML
    void gerenciarAbas() {

    	if(cadastrar.isSelected()  ||  consultar.isSelected()) {
    		atualizar.setDisable(true);
    	}
    	
    }

    @FXML
    void limparCadastroNovoProduto() {
    	nomeNovoProduto.clear();
    	validadeNovoProduto.setValue(null);
    	marcaNovoProduto.clear();
    	precoNovoProduto.clear();
    	
    }

    private void limparCadastroAtualizadoProduto (){
    	nomeAtualizadoProduto.clear();
    	validadeAtualizadoProduto.setValue(null);
    	marcaAtualizadoProduto.clear();
    	precoAtualizadoProduto.clear();
    }
    
    @FXML
    void salvarNovoProduto() {

    	estoque produto = new estoque();
    	
    	produto.setNome(nomeNovoProduto.getText());
    	produto.setData_validade(Date.valueOf(validadeNovoProduto.getValue()));
    	produto.setMarca(marcaNovoProduto.getText());
    	produto.setPreco(new BigDecimal(precoNovoProduto.getText()));
   	try {
    	
   		estoqueDAO dao = new estoqueDAO();
   		dao.cadastrar(produto);
    	
   		exibirDialogoInformacao("Produto Cadastrado com sucesso!");
    	limparCadastroNovoProduto();
   	} catch (Exception e) {
   		exibirDialogoErro("Falha ao cadastrar o produto.");
   	e.printStackTrace();
   	}
  
    
    	
    }

    @FXML
    void salvarProduto() {

    }

    private void exibirDialogoInformacao(String informacao) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informação");
    	alert.setHeaderText(null);
    	alert.setContentText(informacao);
    	
    	alert.showAndWait();
    }

    private void exibirDialogoErro(String erro) {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Informação");
    	alert.setHeaderText(null);
    	alert.setContentText(erro);
    	
    	alert.showAndWait();
    }
}
