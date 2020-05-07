package com.vetshop.application;

import com.vetshop.controllers.Controller;
import com.vetshop.controllers.DTOController;
import com.vetshop.controllers.common.LoginController;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.dtos.DTO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * The type Java fx application.
 */
public class JavaFXApplication extends Application {

    private static ConfigurableApplicationContext applicationContext;

    /**
     * Change scene.
     *
     * @param <T> the type parameter
     * @param c   the c
     */
    public static <T extends Controller> void changeScene(Class<T> c) {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent pane = fxWeaver.loadView(c);

        Stage stage = new Stage();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Change scene.
     *
     * @param <T> the type parameter
     * @param c   the c
     * @param dto the dto
     */
    public static <T extends DTOController> void changeScene(Class<T> c, DTO dto) {
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

    /**
     * Show alert.
     *
     * @param message the message
     */
    public static void showAlert(String message) {
        Platform.runLater(() -> AlertBox.display("INFO", message));
    }

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        applicationContext = new SpringApplicationBuilder()
                .sources(BlackDogClientApplication.class)
                .run(args);
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println("BEAN :: " + beanName);
        }
    }

    @Override
    public void stop() {
        applicationContext.close();
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
}
