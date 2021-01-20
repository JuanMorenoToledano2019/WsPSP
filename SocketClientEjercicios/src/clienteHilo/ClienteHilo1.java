package clienteHilo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteHilo1 {

	public static void main(String[] args) {
		System.out.println("        APLICACI�N CLIENTE");
		System.out.println("-----------------------------------");

		Scanner lector = new Scanner(System.in);
		
		InputStreamReader entrada = null;
		PrintStream salida = null;
		Socket socketCliente1 = null;
		
		try {
			socketCliente1 = new Socket();
			InetSocketAddress direccionServidor = new InetSocketAddress("localhost", 2002);
			System.out.println("Esperando a que el servidor acepte la conexi�n");
			
			socketCliente1.connect(direccionServidor);
			System.out.println("Comunicaci�n establecida con el servidor");
			
			entrada = new InputStreamReader(socketCliente1.getInputStream());
			salida = new PrintStream(socketCliente1.getOutputStream());
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			
			String texto = "";
			while (!texto.equals("FIN")) {
				System.out.println("Escribe mensaje (FIN para terminar): ");
				texto = lector.nextLine();//mensaje leido del usuario

				salida.println(texto);
				System.out.println("Esperando respuesta ...... ");
				
				String respuesta = entradaBuffer.readLine();
				System.out.println("Servidor responde: " + respuesta);
			}
		} catch (UnknownHostException e) {
			System.out.println("No se puede establecer comunicaci�n con el servidor");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S");
			System.out.println(e.getMessage());
		} finally {
			try {
				if(salida != null && entrada != null){
					salida.close();
					entrada.close();
					socketCliente1.close();
				}
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		lector.close();
		System.out.println("Comunicaci�n cerrada");		
	}
}
