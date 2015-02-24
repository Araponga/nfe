package org.brots.scidata.nfe.utils;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class EscolherArquivo {
	
	private File diretorio = null;
	
    public EscolherArquivo(){
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Escolha o(s) arquivo(s)...");
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setApproveButtonText("OK");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setMultiSelectionEnabled(false);
        
       if (fc.showOpenDialog(fc) == JFileChooser.CANCEL_OPTION){
            System.exit(1);
        }
       
       diretorio = new File(fc.getSelectedFile().getAbsolutePath());
    }

	public File getDiretorio() throws IOException {
		return diretorio;
	}
}