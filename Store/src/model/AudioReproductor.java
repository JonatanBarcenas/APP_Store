package model;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioReproductor implements Runnable{
	private String rutaAudio;
	
	public AudioReproductor(String rutaAudio) {
		this.rutaAudio = rutaAudio;
	}
	@Override
	public void run() {
		try {
			FileInputStream archivo = new FileInputStream(rutaAudio);
			BufferedInputStream flujoEntrada = new BufferedInputStream(archivo);
			Player player = new Player(flujoEntrada);
			player.play();
		} catch (FileNotFoundException | JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
