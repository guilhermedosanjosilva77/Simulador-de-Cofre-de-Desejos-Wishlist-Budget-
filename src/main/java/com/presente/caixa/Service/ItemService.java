package com.presente.caixa.Service;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public ItemEntity criar(ItemEntity itemEntity){
        //Código para prevenir que tente ser salvo um item que o user nao exista
        Long idDoUsuario=itemEntity.getUserEntity().getId_user();
        UserEntity usuarioExistente = userRepository.findById(idDoUsuario).orElseThrow(()->new RuntimeException("Erro usuário não existe"));
       
        itemEntity.setUserEntity(usuarioExistente);

        return itemRepository.save(itemEntity);
    }

    //READ
    public List<ItemEntity> buscar(){
        return itemRepository.findAll();
    }

    //READ BY ID
    public ItemEntity buscarPorId(Long id_item){
        return itemRepository.findById(id_item).orElse(null);

    }

    //UPDATE
    public ItemEntity atualizar(Long id_item, ItemEntity atualizar){
        ItemEntity itemEntity = buscarPorId(id_item);

        if (itemEntity == null) {

            return null;
            
        }
        itemEntity.setNomeItem(atualizar.getNomeItem());
        itemEntity.setValorDisponivel(atualizar.getValorDisponivel());
        itemEntity.setValorItem(atualizar.getValorItem());

        return itemRepository.save(itemEntity);
    }

    //DELETE
    public void deletar(Long id_item){
        itemRepository.deleteById(id_item);
    }




    

    
    
}
