/**
	@author
		� Denis Christopher
		� Genco Luca
**/

package be.twinkle.daoimpl;

import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class ParserConfig {

	// M�thode charg�e de construire un objet de type Persistance.
	// Elle initialise ses attributs avec les valeurs extraites du fichier XML.
	// Elle re�oit en param�tre le nom du fichier XML de configuration.
	// Elle lance une exception si le fichier de configuration est incorrect.
	public static Persistance lireConfiguration(String fichierConfiguration) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Persistance.class);
		Unmarshaller um = context.createUnmarshaller();
		FileReader fr = new FileReader(fichierConfiguration);
		Persistance persistance = (Persistance) um.unmarshal(fr);
		fr.close();
		validation(persistance);
		return persistance;
	}

	private static void validation(Persistance persistance) throws Exception {
		if (persistance.getNbDaos() == 0)
			throw new Exception("Il manque la balise <dao>");
		if (persistance.getType() == null)
			throw new Exception("Il manque la balise <type>");
		if (!persistance.getType().equals(Persistance.DB) 
				&& !persistance.getType().equals(Persistance.MOCK))
			throw new Exception("Type de persistance est incorrect");
		if (persistance.getType().equals(Persistance.MOCK)) return;
		if (persistance.getDriver() == null)
			throw new Exception("Il manque la balise <driver>");
		if (persistance.getUrl() == null)
			throw new Exception("Il manque la balise <url>");
		if (persistance.getUser() == null)
			throw new Exception("Il manque la balise <user>");
		if (persistance.getPassword() == null)
			throw new Exception("Il manque la balise <password>");
	
	}
}