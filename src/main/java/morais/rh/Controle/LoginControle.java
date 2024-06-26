package morais.rh.Controle;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import morais.rh.App;
import morais.rh.DAO.ControleBanco;
import morais.rh.DAO.ModelosDAO.AlteraDAO;
import morais.rh.DAO.ModelosDAO.UsuarioDAO;
import morais.rh.Modelo.Usuario;

public class LoginControle {

    @FXML
    Button Bentrar;

    @FXML
    Button Bolho;

    @FXML
    Label Lrevela;

    @FXML
    PasswordField Lsenha;

    @FXML
    TextField Lusuario;

    ArrayList<Usuario> usus = UsuarioDAO.buscarUsuarioSQL();

    public void initialize(){

        Bentrar.setOnAction((ActionEvent event) ->{

            String usuario = Lusuario.getText();
            String senha = Lsenha.getText();

            System.out.println(usus.size());

            for(Usuario usu : usus){
                
                if(usu.getUsuario().equals(usuario) && usu.getSenha().equals(senha)){
                    try {
                        for(Usuario usu1 : usus){
                            UsuarioDAO.atualizarAtual(usu1.getCod(), usu.getCod());
                        }
                        App.setRoot("TelaInicial");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    BackgroundFill backgroundFill = new BackgroundFill(Color.RED, CornerRadii.EMPTY, null);
                    Background background = new Background(backgroundFill);
                    Bentrar.setBackground(background);
                }
            }
        });

        Bolho.setOnAction((ActionEvent event) ->{
            if(Lrevela.getText() == null){
                Lrevela.setText(Lsenha.getText());
            }else{
                Lrevela.setText(null);
            }
        });

        Platform.runLater(() -> {
            if(AlteraDAO.buscarStatus() != 1){
                ControleBanco.AtualizarB();
                usus = UsuarioDAO.buscarUsuarioSQL();
            }
        });

    }
        
}
