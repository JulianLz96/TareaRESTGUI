package main.java.gui.controllers;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

import javafx.application.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;

public class HomeController extends Application {
	@FXML private TextField marcaTextF;
	@FXML private TextField modeloTextF;
	@FXML private TextField precioTextF;
	@FXML private TextField idTextF;

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		URL fxmlUrl = this.getClass().getClassLoader().getResource("fxml/Home.fxml");
		FXMLLoader loader = new FXMLLoader(fxmlUrl);
		Pane pane = loader.load();
		Scene scene = new Scene(pane);
		Stage stage = new Stage();
		stage.setScene(scene);	
		stage.show();
	}
	
	@FXML
	public void Crear() {
		System.out.println("crear");
		OkHttpClient client = new OkHttpClient();
		RequestBody reqbody = RequestBody.create(null, new byte[0]); 
		Request request = new Request.Builder()
		  .url("http://localhost:8080/TareaRESTJFX/producto/producto?marca="
				  + marcaTextF.getText()
				  +"&producto=" + modeloTextF.getText()
				  + "&precio=" + precioTextF.getText())
		  .post(reqbody)
		  .addHeader("cache-control", "no-cache")
		  .addHeader("Postman-Token", "7343094d-3140-48bd-8469-e3386d7eb448")
		  .build();

		try {
			Response response = client.newCall(request).execute();
			Alert alert = new Alert(AlertType.INFORMATION);
			if(response.isSuccessful()) {
				alert.setTitle("Producto");
				alert.setHeaderText("Product Added");
				alert.setContentText("Buen Producto");
				
				alert.showAndWait();
				
				response.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML void Modificar() {
		System.out.println("modificar");
		OkHttpClient client = new OkHttpClient();
		RequestBody reqbody = RequestBody.create(null, new byte[0]); 
		Request request = new Request.Builder()
		  .url("http://localhost:8080/TareaRESTJFX/producto/producto?marca="
				  + marcaTextF.getText() 
				  +"&producto=" + modeloTextF.getText()
				  + "&precio=" + precioTextF.getText()
				  + "&id=" + idTextF.getText())
		  .put(reqbody)
		  .addHeader("cache-control", "no-cache")
		  .addHeader("Postman-Token", "7cd80fde-8720-4163-a11a-54e785880187")
		  .build();

		try {
			Response response = client.newCall(request).execute();
			if(response.isSuccessful()) {
				System.out.println("Entro is successful");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Producto");
				alert.setHeaderText("Product Modified");
				alert.setContentText("Producto Modificado");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void Eliminar() {
		System.out.println("eliminar");
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://localhost:8080/TareaRESTJFX/producto/producto?id=" 
				  + idTextF.getText())
		  .delete(null)
		  .addHeader("cache-control", "no-cache")
		  .addHeader("Postman-Token", "139d6478-9a7b-496f-90c0-b3c960230bbb")
		  .build();

		try {
			Response response = client.newCall(request).execute();
			if(response.isSuccessful()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Producto");
				alert.setHeaderText("Product Deleted");
				alert.setContentText("Producto Eliminado");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void Consultar() {
		System.out.println("Consultar");
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://localhost:8080/TareaRESTJFX/producto/producto?id=" + idTextF.getText())//TODO agregar textF id
		  .get()
		  .addHeader("cache-control", "no-cache")
		  .addHeader("Postman-Token", "126e6353-2083-4f74-a140-da32dd9815ab")
		  .build();

		try {
			Response response = client.newCall(request).execute();
			Alert alert = new Alert(AlertType.INFORMATION);
			if(response.isSuccessful()) {
				//Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Producto");
				alert.setHeaderText("Found Product");
				alert.setContentText(response.body().string());
			
				response.close();
			}
			else {
				alert.setTitle("Producto");
				alert.setHeaderText("No Product Found");
				
			}
			alert.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		launch(args);
	}
}
