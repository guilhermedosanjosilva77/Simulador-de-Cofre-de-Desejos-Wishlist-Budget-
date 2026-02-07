package com.presente.caixa.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.presente.caixa.DTO.ItemRequest;
import com.presente.caixa.DTO.ItemResponse;
import com.presente.caixa.Entity.ItemEntity;
import com.presente.caixa.Entity.UserEntity;
import com.presente.caixa.Repository.ItemRepository;
import com.presente.caixa.Repository.UserRepository;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    

    

    
    //CREATE
    public ItemService(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public ItemResponse criar(ItemRequest dados){
        //Código para prevenir que tente ser salvo um item que o user nao exista
       UserEntity dono = userRepository.findById(dados.idUsuario()).orElseThrow(()-> new RuntimeException("Usuario não existe"));

       ItemEntity itemEntity = new ItemEntity();
       itemEntity.setNomeItem(dados.nomeItem());
       itemEntity.setValorDisponivel(dados.valorDisponivel());
       itemEntity.setValorItem(dados.valorItem());
       itemEntity.setUserEntity(dono);




        itemRepository.save(itemEntity);
        return new ItemResponse(itemEntity);
    }

    //READ
    public List<ItemResponse> buscar(){
        return itemRepository.findAll().stream().map(ItemResponse::new).toList();
    }

    //READ BY ID
    public ItemResponse buscarPorId(Long id_item){
        ItemEntity itemEntity = itemRepository.findById(id_item).orElse(null);

        if (itemEntity != null) {
            return new ItemResponse(itemEntity);
            
        }
        else{
            return null;
        }



    }

    //UPDATE
    public ItemResponse atualizar(Long id_item, ItemRequest atualizar){
        ItemEntity itemEntity = itemRepository.findById(id_item).orElse(null);

        if (itemEntity == null) {

            return null;
            
        }
        itemEntity.setNomeItem(atualizar.nomeItem());
        itemEntity.setValorDisponivel(atualizar.valorDisponivel());
        itemEntity.setValorItem(atualizar.valorItem());

         itemRepository.save(itemEntity);
         return new ItemResponse(itemEntity);
    }

    //DELETE
    public void deletar(Long id_item){
        itemRepository.deleteById(id_item);
    }




    

    
    
}
