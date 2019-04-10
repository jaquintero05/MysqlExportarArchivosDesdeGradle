package com.sophos.conexionmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConexionMysql {

	private Connection connect = null;  
	private Statement sql = null; 
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private WebDriver driver;
	
	private By BarraBusqueda = By.name("q");
	private By BotonBuscar = By.name("btnK");

	public ConexionMysql(WebDriver driver) {
		this.driver = driver;

	}

	public void Conexion() {
		
	try {
		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection(
				"jdbc:mysql://localhost/busquedas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "root");

	} catch (ClassNotFoundException | SQLException e) {
		System.out.println("Error al cargar el controlador");
		e.printStackTrace();

	}
	}

	public  void busqueda() {
		
			
			try {
				
				sql = connect.createStatement();// escojo la canexion para empezar a enviarles parametro
				// Result set get the result of the SQL query
				resultSet = sql.executeQuery("select * from busquedas.busqueda");
				
				while (resultSet.next()) {
					String texto = resultSet.getString("textobuscar");
					System.out.println("texto::" + texto);
				driver.get("https://www.google.com.co/");
				driver.findElement(BarraBusqueda).sendKeys(texto);
				driver.findElement(BotonBuscar).submit();

				if (ExpectedConditions.invisibilityOf(
						driver.findElement(By.xpath("//h1[contains(text(),'Resultados de b�squeda')]"))) != null) {
					preparedStatement = connect
							.prepareStatement("update busqueda set resultado=? where textobuscar like  ?;");
					preparedStatement.setBoolean(1, true);
					preparedStatement.setString(2, texto);

					preparedStatement.executeUpdate();
					;
					preparedStatement = connect
							.prepareStatement("update busqueda set resultado=? where textobuscar like  ?;");
					preparedStatement.setBoolean(1, false);
					preparedStatement.setString(2, texto);
					preparedStatement.executeUpdate();

				} 
				
				}
				}
			catch (Exception e) {
				

					}

		
		
				
			}
	}