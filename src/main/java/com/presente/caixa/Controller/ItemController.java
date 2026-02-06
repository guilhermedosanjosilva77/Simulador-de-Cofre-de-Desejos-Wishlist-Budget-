package com.presente.caixa.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presente.caixa.Entity.ItemEntity;
import com.presente.caixa.Service.ItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //CREATE - POST
    @PostMapping
    public ItemEntity criar(@RequestBody ItemEntity itemEntity) {
        return itemService.criar(itemEntity);
    }

    //READ - GET
    @GetMapping
    public List<ItemEntity>buscar() {
        return itemService.buscar();
    }

    //READ BY ID - GET
    @GetMapping("/{id_item}")
    public ItemEntity buscarPorId(@PathVariable Long id_item) {
        return itemService.buscarPorId(id_item);
    }
    

    //UPDATE - PUT
    @PutMapping("/{id_item}")
    public ItemEntity atualizar(@PathVariable Long id_item, @RequestBody ItemEntity itemEntity) {
        return itemService.atualizar(id_item, itemEntity);
    }

    //DELETE - DELETE
    @DeleteMapping("/{id_item}")
    public void deletar(@PathVariable Long id_item){
        itemService.deletar(id_item);
    }
    
    
    
}
