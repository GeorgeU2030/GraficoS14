package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


import control.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	private Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		 String fileComplete="";
		System.out.println("Enter the route of the file");
		String path=sc.next();
		FileReader readFile;
		try {
			readFile = new FileReader(path);
			BufferedReader textFile = new BufferedReader(readFile);
	        String line;
	        
	        try {
				while((line=textFile.readLine())!=null) {
					fileComplete+=line;
				}}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 	
			
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/MainWindow.fxml"));
		loader.setController(new MainWindow(fileComplete));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

}
