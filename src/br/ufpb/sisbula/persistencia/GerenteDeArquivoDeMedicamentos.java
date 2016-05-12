package br.ufpb.sisbula.persistencia;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import javax.imageio.stream.FileImageInputStream;

import br.ufpb.sisbula.Medicamento;
import br.ufpb.sisbula.persistencia.GerenteDeArquivoDeMedicamentos;


public class GerenteDeArquivoDeMedicamentos {
	
	private static final String ARQUIVO_DEFAULT = null;
	private String nomeArquivo;

	public GerenteDeArquivoDeMedicamentos(){
		this.nomeArquivo = ARQUIVO_DEFAULT;
	}
	
	public void gravaMedicamento(Collection <Medicamento> medicamentos) throws IOException{
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
			out.writeObject(medicamentos);
		}finally{
			if(out!= null){
				out.close();
			}
		}
	}
	
	public Collection <Medicamento> leMedicamentos() throws Exception{
		ObjectInputStream in = null;
		try{
			in = new ObjectInputStream(new FileInputStream(nomeArquivo));
			return (Collection<Medicamento>) in.readObject();
		}finally{
			if(in!= null){
				in.close();
			}
		}
		
	}
}

	