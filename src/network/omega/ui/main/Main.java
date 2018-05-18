package network.omega.ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import library.assistant.database.DatabaseHandler;
import library.assistant.util.LibraryAssistantUtil;
import network.omega.ui.login.LoginController;
import network.omega.ui.preferences.ManageLocalStorage;

import java.awt.*;

public class Main extends Application {

    public static Color BACKGROUND_FILL = Color.valueOf("#2A2E37");

    @Override
    public void start(Stage stage) throws Exception {

        //if we have a password file already created in local storage with just load the main app

        if(ManageLocalStorage.passwordFileExists()) {
            Parent parent = FXMLLoader.load(getClass().getResource("/network/omega/ui/main/main.fxml"));
            Stage stage1 = new Stage(StageStyle.DECORATED);
            stage1.setTitle("OMEGA Governance");
            Scene scene = new Scene(parent);
            scene.setFill(Main.BACKGROUND_FILL);
            stage1.setScene(scene);
            stage1.show();
            LibraryAssistantUtil.setStageIcon(stage1);
            return;
        }

        // Omega graphics :
        // https://github.com/omegacoinnetwork/omegacoin/tree/master/src/qt/res/icons
        Parent root = FXMLLoader.load(getClass().getResource("/network/omega/ui/login/login.fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/network/omega/ui/login/login.fxml"));
//        Parent root = loader.load();
//        LoginController controller = loader.getController();
//        Example updateThread = new Example(controller);


        Scene scene = new Scene(root);
        scene.setFill(BACKGROUND_FILL);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("OMEGA Governance");

        LibraryAssistantUtil.setStageIcon(stage);
        
        new Thread(() -> {
            DatabaseHandler.getInstance();
        }).start();
    }



    public static void main(String[] args) {
        launch(args);
    }

}