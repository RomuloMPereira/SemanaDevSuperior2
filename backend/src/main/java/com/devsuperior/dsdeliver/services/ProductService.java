package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

//A camada de serviço se comunica com os controladores REST e acessa a camada de acesso a dados (Repository)
@Service 
public class ProductService {

	//Injeção de dependência
	@Autowired
	private ProductRepository repository;
	
	// Os métodos da camada de serviço sempre devolvem DTO (Data Transfer Object),
	// um objeto enxuto que só carrega as informações que se mandar
	// DTO não é controlado por ORM e não tem relação com banco de dados
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll(){
		List<Product> list = repository.findAllByOrderByNameAsc();
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}
}
