package br.com.prontomed.peg.controllers;

import br.com.prontomed.peg.models.CID;
import br.com.prontomed.peg.services.CIDService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="/cid")
public class CIDController {

    @Autowired
    private CIDService cidService;

    @GetMapping(path = "/all")
    public @ResponseBody List<CID> obterTodos() {
        return cidService.obterTodosCids();
    }
    
    @GetMapping(path = "/{id}")
    public @ResponseBody CID obterPorId(@PathVariable String id) {
        Optional<CID> cid = cidService.obterPorId(id);
        
        if (cid.isPresent()) {
            return cid.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "cid nao encontrado");
        }
    }
}
