package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MainWindow implements Initializable{

	private ArrayList<Double>ejeX=new ArrayList<>();
    private ArrayList<Double>ejeY=new ArrayList<>();
    
	private String file="";
	
	public MainWindow(String file) {
		this.file=file;
	}
	
	 @FXML
	    private Canvas canvas;
	 
	 private GraphicsContext gc;
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 gc = canvas.getGraphicsContext2D();
		addData();
		paint();
	}
	public void addData() {
		String []split1=file.split(",");
		String []firstline = split1[1].split(";");
		
		double value = Double.parseDouble(firstline[1]);
		ejeX.add(value);
		for(int i=2;i<split1.length-1;i++) {
			String []split2 = split1[i].split(";");
			value = Double.parseDouble(split2[0]);
			ejeY.add(value);
			value =  Double.parseDouble(split2[1]);
			ejeX.add(value);
		}
		String []lastline = split1[split1.length-1].split(";");
		value = Integer.parseInt(lastline[0]);
		ejeY.add(value);
		
		
	}
	public void paint() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		dimensions();
		plane();
	}
	public void dimensions() {
		double[]resX = getMinMax(ejeX);
	       double minX = resX[0];
	       double maxX = resX[1];
	       
	       double[]resY = getMinMax(ejeY);
	       double minY = resY[0];
	       double maxY = resY[1];
	       
	       double deltaPx = canvas.getWidth();
	       double deltaDias = maxX-minX;
	       
	       double pendienteX=deltaPx/deltaDias;
	       
	       double intercepto = pendienteX * minX *(-1);
	       
	       double deltaPy = -canvas.getHeight();
	       
	       double deltaAccidentes = maxY-minY;
	       
	       double pendienteY = deltaPy/deltaAccidentes;
	       double interceptoY = pendienteY* maxY*(-1);
	       //System.out.println(minX + " "+maxX+" "+minY+" "+maxY);
	       gc.setFill(Color.BLUE);
	       for(int i=0;i<ejeX.size();i++) {
	    	   gc.fillOval(conversion(ejeX.get(i),pendienteX,intercepto), conversion(ejeY.get(i),pendienteY,interceptoY)-3, 6, 6);
	       }
	       gc.setStroke(Color.BLUE);
	       gc.setLineWidth(2);
	       for(int i=0;i<ejeX.size()-1;i++) {
	      // gc.setLineWidth(2);
	       gc.moveTo(conversion(ejeX.get(i),pendienteX,intercepto)+3, conversion(ejeY.get(i),pendienteY,interceptoY)+3);
	       gc.lineTo(conversion(ejeX.get(i+1),pendienteX,intercepto)+3, conversion(ejeY.get(i+1),pendienteY,interceptoY)+3);
	       }
	       gc.stroke();
	}
	public double[] getMinMax(ArrayList<Double>eje) {
		ArrayList<Double>aux=new ArrayList<>();
		aux.addAll(eje);
		Collections.sort(aux);
		double min = aux.get(0);
		double max = aux.get(aux.size()-1);
		return new double [] {min,max};
	}
	private double conversion(double x,double m,double b) {
		
	   return m*x+b;	
	}
	public void plane() {
		double[]resX = getMinMax(ejeX);
	       double minX = resX[0];
	       double maxX = resX[1];
	       
	       double[]resY = getMinMax(ejeY);
	       double minY = resY[0];
	       double maxY = resY[1];
	       
	       double dif = maxX-minX;
	       double def = maxY-minY;
	       double pixels = 600/dif;
	       
	       
	       int uni =0;
	       double quarter = dif/4; 
	       uni = (int)minX+(int)quarter;
	       dif = uni;
	      
	       gc.setFill(Color.BLACK);
	       gc.setStroke(Color.BLACK);
	       gc.setLineWidth(1);
	       for(int i=0;i<3;i++) {
	    	   gc.moveTo(dif*pixels, 0);
		       gc.lineTo(dif*pixels, 400);
		      // gc.stroke();
		       gc.fillText(String.valueOf(uni), dif*pixels, 400);
		       dif += quarter;
		       uni += quarter;
	       }
	       gc.stroke();
	       gc.setStroke(Color.GRAY);
	       gc.setLineWidth(1);
	       pixels = 400/def;
	       quarter = def/4;
	       uni =(int)maxY-(int)quarter;
	       def = (int)quarter;
	       for(int i=0;i<3;i++) {
	    	   gc.moveTo(0, def*pixels);
		       gc.lineTo(600, def*pixels);
		      // gc.stroke();
		       gc.fillText(String.valueOf(uni), 0,def*pixels );
		      
		       def = def+quarter;
		       uni = uni-(int)quarter;
	       }
	       gc.stroke();
	       
	     /*  gc.moveTo(dif*pixels, 0);
	       gc.lineTo(dif*pixels, 400);
	      // gc.stroke();
	       gc.fillText(String.valueOf(uni), dif*pixels, 400);
	       dif += quarter;
	       uni += quarter;
	      // dif = dif*pixels;
	 
	      
	      // gc.setStroke(Color.BLACK);
	       gc.setLineWidth(1);
	       gc.moveTo(dif*pixels, 0);
	       gc.lineTo(dif*pixels, 400);
	       //gc.stroke();
	       gc.fillText(String.valueOf(uni), dif*pixels, 400);
	       
	       dif += quarter;
	       uni += quarter;
	      // dif = dif*pixels;
	 
	      
	      // gc.setStroke(Color.BLACK);
	       gc.setLineWidth(1);
	       gc.moveTo(dif*pixels, 0);
	       gc.lineTo(dif*pixels, 400);
	 //      gc.stroke();
	       gc.fillText(String.valueOf(uni), dif*pixels, 400);
	       
	       pixels = 400/def;
	       
	       quarter = def/4;
	       uni =(int)def-(int)quarter;
	       def = (int)quarter;
	       
	     //  gc.setStroke(Color.BLACK);
	       gc.setLineWidth(1);
	       gc.moveTo(0, def*pixels);
	       gc.lineTo(600, def*pixels);
	      // gc.stroke();
	       gc.fillText(String.valueOf(uni), 0,def*pixels );
	       System.out.println(def);
	       def = def+quarter;
	       uni = uni-(int)quarter;
	       System.out.println(def);
	       gc.setLineWidth(1);
	       gc.moveTo(0, def*pixels);
	       gc.lineTo(600, def*pixels);
	       gc.stroke();
	       gc.fillText(String.valueOf(uni), 0,def*pixels );
	       */
	}
}
