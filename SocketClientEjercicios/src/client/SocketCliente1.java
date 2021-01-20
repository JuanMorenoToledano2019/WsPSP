package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketCliente1 {

	public static final int PUERTO = 2013;
	public static final String IP_SERVER = "localhost";
	
	public static void main(String[] args) {
		System.out.println("        APLICACIÓN CLIENTE");
		System.out.println("-----------------------------------");
		
		Socket socketCliente1 = null;
		InputStreamReader entrada = null;
		PrintStream salida = null;
		
		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);

		Scanner sc = new Scanner(System.in);
		try {
			socketCliente1 = new Socket();
			socketCliente1.connect(direccionServidor);
			System.out.println("Conexion establecida... a " + IP_SERVER + " por el puerto "
					+ PUERTO);
			
			entrada = new InputStreamReader(socketCliente1.getInputStream());
			salida = new PrintStream(socketCliente1.getOutputStream());
			
			System.out.println("CLIENTE: Introduzca los numeros a sumar");
			String numero1 = sc.nextLine();
			String numero2 = sc.nextLine();
			System.out.println("CLIENTE: Introduzca la operacion");
			String operacion = sc.nextLine();
			String operandos = numero1 + "-" + numero2 + "-" + operacion;
			salida.println(operandos);
			
			BufferedReader bf = new BufferedReader(entrada);
			
			String resultado = bf.readLine();
			
			System.out.println("CLIENTE: " + resultado);
		} catch (Exception e) {
			System.err.println("Error: " + e);
			e.printStackTrace();
		} finally {
			try {
				salida.close();
				entrada.close();
				socketCliente1.close();
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
