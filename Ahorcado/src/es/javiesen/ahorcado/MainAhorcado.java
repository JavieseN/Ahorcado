package es.javiesen.ahorcado;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class MainAhorcado {

	public static void main(String[] args) 
	{
		List<String>palabras = llenarListaPalabras();
		String resultado = mostrarPalabra(palabras);
		
		comenzarJuego(resultado);
		
	}
	
	private static void grafico(int vida, char[] palabra, Set<Character> letrasUsadas, char ultimaLetra, List<Character> muestraResultado) 
	{
		System.out.println("VIDAS: " + vida);
		for (int i = 0; i < palabra.length; i++) 
		{
			if(ultimaLetra == palabra[i] && palabra[i] != '_')
			{
				muestraResultado.set(i,ultimaLetra);
			}
		}
		
	    Iterator<Character> it = muestraResultado.iterator();
	    while(it.hasNext())
	    {
	      System.out.print(it.next() + " ");
	    }
		System.out.println();
		System.out.print("LETRAS UTILIZADAS: (");
		
		Object[] arrayLetrasUsadas = letrasUsadas.toArray();
		for (int i = 0; i < letrasUsadas.size(); i++) 
		{
			if (arrayLetrasUsadas[i] == arrayLetrasUsadas[0]) 
			{
				System.out.print(arrayLetrasUsadas[i]);
			}
			else
			{
				System.out.print(","+arrayLetrasUsadas[i]);
			}
		}
		/*for (Character character : letrasUsadas) 
		{
			System.out.print(character+",");
		}*/
		System.out.print(")\n");
		
	}

	private static void comenzarJuego(String resultado) 
	{
		boolean victoria = false;
		Scanner scan = null;
		int vida = 5;
		char[] palabra = resultado.toCharArray();
		int aciertos = 0;;
		//List<Character> letrasUsadas = new ArrayList<Character>();
		Set<Character> letrasUsadas = new HashSet<Character>();
		
		List<Character> muestraResultado = new ArrayList<Character>();
		for (int i = 0; i < palabra.length; i++) 
		{
			muestraResultado.add('_');
		}
		
		grafico(vida, palabra, letrasUsadas,' ',muestraResultado);
		
		while (vida > 0 && victoria == false) 
		{
			System.out.println("Introduce la letra");
			scan = new Scanner(System.in);
			char letra = scan.nextLine().toUpperCase().charAt(0);
			
			for (int i = 0; i < palabra.length; i++) 
			{
				aciertos += comprobarLetra(palabra[i],letra);
				
			}
			if(aciertos == 0)
			{
				vida--;
			}
			letrasUsadas.add(letra);
			
			grafico(vida, palabra, letrasUsadas,letra, muestraResultado);
			aciertos = 0;
			for (int i = 0; i < muestraResultado.size() ; i++) 
			{
				if(!muestraResultado.get(i).equals('_'))
				{
					victoria = true;
				}
				else
				{
					victoria = false;
					break;
				}
			}
		}
		if(vida == 0)
		{
			System.out.println("HAS PERDIDO");
			System.out.println("La palabra era: "+resultado);
		}
		else
		{
			System.out.println("HAS GANADO");
		}
		scan.close();
	}
	
	private static int comprobarLetra(char palabra, char letra)
	{
		if(palabra == letra)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	private static String mostrarPalabra(List<String> palabras) 
	{
		int numPalabra = ThreadLocalRandom.current().nextInt(0, palabras.size());
		//System.out.println(palabras.get(numPalabra));
		
		return palabras.get(numPalabra);
	}

	public static List<String> llenarListaPalabras() {
		List<String> palabras = new ArrayList<String>();
		FileReader fr = null;
		BufferedReader br = null;
		try 
		{
			fr = new FileReader("ejercicioAhoracado.txt");
			br = new BufferedReader(fr);
			String palabra = "";
			while((palabra = br.readLine()) != null)
			{
				palabras.add(palabra);
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*for (String string : palabras) {
			System.out.println(string);
		}*/
		return palabras;
	}

}
