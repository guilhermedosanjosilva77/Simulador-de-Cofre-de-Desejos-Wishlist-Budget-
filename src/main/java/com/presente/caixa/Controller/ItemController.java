package com.presente.caixa.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.presente.caixa.DTO.ItemRequest;
import com.presente.caixa.DTO.ItemResponse;
import com.presente.caixa.Service.ItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //CREATE - POST
    @PostMapping
    public ItemResponse criar(@RequestBody ItemRequest itemRequest) {
        return itemService.criar(itemRequest);
    }

    //READ - GET
    @GetMapping
    public List<ItemResponse>buscar() {
        return itemService.buscar();
    }

    //READ BY ID - GET
    @GetMapping("/{id_item}")
    public ItemResponse buscarPorId(@PathVariable Long id_item) {
        return itemService.buscarPorId(id_item);
    }
    

    //UPDATE - PUT
    @PutMapping("/{id_item}")
    public ItemResponse atualizar(@PathVariable Long id_item, @RequestBody ItemRequest itemRerRequest) {
        return itemService.atualizar(id_item, itemRerRequest);
    }

    //DELETE - DELETE
    @DeleteMapping("/{id_item}")
    public void deletar(@PathVariable Long id_item){
        itemService.deletar(id_item);
    }

    @PatchMapping("/{id_item}/adicionar")
public ItemResponse adicionarValor(@PathVariable Long id_item, @RequestParam Double valor) {
    return itemService.adicionarValor(id_item, valor);
}
    
    
    
}
