package com.example.petadota.controller;

import com.example.petadota.model.Animal;
import com.example.petadota.repository.AnimalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
@CrossOrigin(origins = "http://localhost:4200")
public class AnimalController {

    private final AnimalRepository repository;

    public AnimalController(AnimalRepository repository) {
        this.repository = repository;
    }

    /**
     * Metodo REST GET para listar animais
     *
     * @return Lista com animais
     */
    @GetMapping
    public List<Animal> listarTodos() {
        return repository.findAll();
    }

    /**
     * Metodo REST POST para salvar um animal
     *
     * @param animal Animal para persistir
     * @return Animal persistido
     */
    @PostMapping
    public Animal salvar(@RequestBody Animal animal) {
        return repository.save(animal);
    }

    /**
     * Metodo REST GET para buscar um animal por ID
     *
     * @param id Id do Animal
     * @return Animal encontrado
     */
    @GetMapping("/{id}")
    public Animal buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Metodo REST PUT para atualizar um animal
     *
     * @param id Id do animal para atualizar
     * @param atualizarAnimal Animal Atualizado
     * @return Animal atualizado
     */
    @PutMapping("/{id}")
    public Animal atualizar(@PathVariable Long id, @RequestBody Animal atualizarAnimal) {
        return repository.findById(id)
                .map(animal -> {
                    animal.setNome(atualizarAnimal.getNome());
                    animal.setTipoAnimal(atualizarAnimal.getTipoAnimal());
                    animal.setIdade(atualizarAnimal.getIdade());
                    animal.setRaca(atualizarAnimal.getRaca());
                    animal.setStatusAdocao(atualizarAnimal.getStatusAdocao());
                    animal.setDescricao(atualizarAnimal.getDescricao());
                    return repository.save(animal);
                }).orElse(null);
    }

    /**
     * Metodo REST DELETE para deletar um animal
     * @param id Id do animal para deletar
     */
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
