package com.example.demo123;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CursorEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    /*@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Retrieve data from the database
        ArangoDB arangoDB = new ArangoDB.Builder().user("root").
                password("12345").host("localhost", 8529).build();
        ArangoDatabase db = arangoDB.db("hello");
        String query = "FOR doc IN mycollection RETURN doc";
        ArangoCursor<BaseDocument> cursor = db.query(query, BaseDocument.class);

        List<BaseDocument> results = new ArrayList<>();
        while (cursor.hasNext()) {
            BaseDocument document = cursor.next();
            results.add(document);
        }
        TableView<BaseDocument> tableView = new TableView<>();

        TableColumn<BaseDocument, String> keyColumn = new TableColumn<>("Key");
        keyColumn.setCellValueFactory(new PropertyValueFactory<>("_key"));

        TableColumn<BaseDocument, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<BaseDocument, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        tableView.getColumns().addAll(keyColumn, nameColumn, ageColumn);

        // Populate the TableView with data
        tableView.getItems().addAll(results);

        // Create a Scene to display the TableView
        StackPane root = new StackPane(tableView);
        Scene scene = new Scene(root, 600, 400);

        // Set the Stage's title and display the Scene
        primaryStage.setTitle("ArangoDB Display");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}