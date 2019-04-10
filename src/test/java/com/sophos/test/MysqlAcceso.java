package com.sophos.test;

import java.sql.Connection;// Representa una conexi�n a una base de datos de SQL

import java.sql.DriverManager; //gestiona el conjunto de controladores Java Database
import java.sql.PreparedStatement;//Invoque el m�todo Connection.prepareStatement para crear un objeto PreparedStatement.
import java.sql.ResultSet;// es donde se contiene los resutados de una consulta SQL
import java.sql.SQLException; //proporcionan informaci�n acerca de los errores y avisos que se producen
import java.sql.Statement;//  procesar una sentencia SQL est�tica y obtener los resultados producidos por ella

import org.junit.After;//Si asigna recursos externos en un m�todo Antes, debe liberarlos despu�s de que se ejecute la prueba
import org.junit.Before;//declaraci�n como las condiciones previas antes de cada caso de prueba
import org.junit.Test;//m�todo de anulaci�n p�blico al que se adjunta se puede ejecutar como un caso de prueba
import org.openqa.selenium.By;//  librer�a By que permite apuntar a objetos en la p�gina
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;//ese es para importar las expected conditions

import com.sophos.conexionmysql.ConexionMysql;
															//por ejemplo si encuentra el elemento

public class MysqlAcceso {
	private Connection connect = null; // creando variables de tipo connect
	private Statement sql = null; // crea la variable sql para para obtener resultado de sql
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private WebDriver driver; // declaro driver

	@Before
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver_73.exe");
		driver = new ChromeDriver();
		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws SQLException {
		
		ConexionMysql oConexionMysql = new ConexionMysql(driver);
		oConexionMysql.Conexion();
		oConexionMysql.busqueda();
		

		}
	}


