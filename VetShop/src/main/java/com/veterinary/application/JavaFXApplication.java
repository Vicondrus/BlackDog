package com.veterinary.application;

import com.veterinary.controllers.Controller;
import com.veterinary.controllers.DTOController;
import com.veterinary.controllers.LoginController;
import com.veterinary.controllers.RegularUserController;
import com.veterinary.dtos.DTO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFXApplication extends Application {

    private static ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(VetShopApplication.class)
                .run(args);
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }

    @Override
    public void start(Stage stage) {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(LoginController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static <T extends Controller> void changeScene(Class<T> c){
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent pane = fxWeaver.loadView(c);

        Stage stage = new Stage();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static <T extends DTOController> void changeScene(Class<T> c, DTO dto){
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);

        DTOController controller = fxWeaver.loadController(c);
        controller.setDTO(dto);

        Parent pane = fxWeaver.loadView(c);

        Stage stage = new Stage();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        controller.refresh();
    }
}
