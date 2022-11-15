package com.formacion.apirest.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formacion.apirest.entity.Client;
import com.formacion.apirest.service.ClientService;

@RestController
@RequestMapping("api")
public class ClientController {

	//inyeccion de dependencia del servicio
	@Autowired
	private ClientService servicio;
	
	@GetMapping("/hola")
	public String hola() {
		return "hola apirest";
	}
	
	@GetMapping("/clientes")
	public List<Client> index(){
		return servicio.allClients();
		
	}
	
	/*@GetMapping("/clientes/{id}")
	public Client show(@PathVariable Long id) {
		return servicio.findClientById(id);
	}*/
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Client client = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			
			client = servicio.findClientById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta en base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(client == null) {
			response.put("mensaje","El cliente con ID:"+id+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Client>(client,HttpStatus.OK);
	}
	
	/*@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Client save(@RequestBody Client client) {
		return servicio.saveClient(client);
	}*/
	
	@PostMapping("/clientes")
	public ResponseEntity<?> save(@RequestBody Client client) {
		Client clientNew = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			clientNew =  servicio.saveClient(client);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insert en base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		response.put("mensaje","El cliente ha sido creado con éxito!");
		response.put("cliente", clientNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	
	/*@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Client update(@RequestBody Client client,@PathVariable Long id) {
		Client clientUpdate= servicio.findClientById(id);
		
		clientUpdate.setName(client.getName());
		clientUpdate.setLast_name(client.getLast_name());
		clientUpdate.setEmail(client.getEmail());
		clientUpdate.setPhone(client.getPhone());
		clientUpdate.setCreatedAt(client.getCreatedAt());
		
		return servicio.saveClient(clientUpdate);
	}*/
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Client client,@PathVariable Long id) {
		Map<String,Object> response = new HashMap<>();
		Client clientUpdate= servicio.findClientById(id);
		
		if(clientUpdate ==  null) {
			response.put("mensaje","Error: no se pudo editar, el cliente con ID:"+id+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			clientUpdate.setName(client.getName());
			clientUpdate.setLast_name(client.getLast_name());
			clientUpdate.setEmail(client.getEmail());
			clientUpdate.setPhone(client.getPhone());
			clientUpdate.setCreatedAt(client.getCreatedAt());
			
			servicio.saveClient(clientUpdate);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar cliente en base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El cliente ha sido actualizado con éxito!");
		response.put("cliente", clientUpdate);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	/*@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Client delete(@PathVariable Long id) {
		return servicio.deleteClient(id);
	}*/
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Client client = servicio.findClientById(id);
		
		
		Map<String,Object> response = new HashMap<>();
		
		try {
			
			String nombreImagenAnterior = client.getImagen();
			
			if(nombreImagenAnterior != null && nombreImagenAnterior.length() > 0) {
				Path rutaImagenAnterior = Paths.get("uploads").resolve(nombreImagenAnterior).toAbsolutePath();
				File archivoImagenAnterior = rutaImagenAnterior.toFile();
				
				if(archivoImagenAnterior.exists() && archivoImagenAnterior.canRead()) {
					
					archivoImagenAnterior.delete();
				}
			}
			
			client = servicio.deleteClient(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar cliente en base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje","El cliente ha sido eliminado con éxito!");
		response.put("cliente",client);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,@RequestParam("id") Long id){
		Map<String,Object> response = new HashMap<String, Object>();
		
		Client client = servicio.findClientById(id);
		
		if(!archivo.isEmpty() && client!=null ) {
			//String nombreArchivo = archivo.getOriginalFilename();
			String nombreArchivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ","");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
		
			String nombreImagenAnterior = client.getImagen();
			
			if(nombreImagenAnterior != null && nombreImagenAnterior.length() > 0) {
				Path rutaImagenAnterior = Paths.get("uploads").resolve(nombreImagenAnterior).toAbsolutePath();
				File archivoImagenAnterior = rutaImagenAnterior.toFile();
				
				if(archivoImagenAnterior.exists() && archivoImagenAnterior.canRead()) {
					
					archivoImagenAnterior.delete();
				}
			}
			
			
			try {
				
				Files.copy(archivo.getInputStream(), rutaArchivo);
				
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente");
				response.put("error",e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);	
			}
		
			
			
			 client.setImagen(nombreArchivo);
			 servicio.saveClient(client);
			 
			 response.put("cliente", client);
			 response.put("mensaje", "Se ha subido correctamente la imagen: "+nombreArchivo);
			 
			 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			
			if(archivo.isEmpty()) {
				response.put("mensaje1", "el archivo no existe");
			
			}
			if(client==null) {
				response.put("mensaje2", "el id no existe");
				
			}
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
	
	}
}
