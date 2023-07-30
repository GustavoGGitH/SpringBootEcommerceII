package com.basicEcommerceII.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class UploadFileService {

	// Creo una variable que contenga la ruta absoluta donde voy a guardar las
	// imagenes de los productos
	private org.slf4j.Logger log = LoggerFactory.getLogger(UploadFileService.class);
	private String folder = "/opt/images/";
	private final Path root= Paths.get("images");
	
	
	public UploadFileService()  {
		if (!Files.exists(root)) {
			try {
			
			Files.createDirectories(root);
			}
			catch (IOException e) {
				System.out.println("No se puede crear el directorio");
				e.printStackTrace();
				
			}
			
		}
		
	}

	public String saveImage(MultipartFile file) throws IOException {

		if (!file.isEmpty()) {
			// En este punto lo que hago es transformar mi file en una serie de bytes para
			// ser enviados
			byte[] bytes = file.getBytes();
			// Luego establezco la ruta

			Path path = Paths.get(folder + file.getOriginalFilename());
			Files.write(path, bytes);

			return file.getOriginalFilename();
		}
		else {
			File fileSource= new File("/opt/default.jpg");
			File fileDest= new File("/default.jpg");
			InputStream in =new FileInputStream(fileSource);
			
			Path path= Paths.get(folder+fileDest);
			Files.write(path, in.readAllBytes());
		}		
		return "default.jpg";
	}

	public void deleteImage(String nombre)  {

		String ruta = "/opt/images/";
		File file = new File(ruta + nombre);
		file.delete();

	}

}