package ar.com.ada.api.cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.entities.Curso;
import ar.com.ada.api.cursos.models.request.CursoRequest;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.services.CursoService;

@RestController
public class CursoController {

    @Autowired
    CursoService cursoService;

    @PostMapping("/api/cursos")
    @PreAuthorize("hasAuthority('CLAIM_userType_STAFF')")
    public ResponseEntity<GenericResponse> crearCurso(@RequestBody CursoRequest cursoReq) {

        Curso cursoCreado = cursoService.crearCurso(cursoReq.nombre, cursoReq.categoriaId, cursoReq.duracionHoras);

        if(cursoCreado == null)
          return ResponseEntity.badRequest().build();
        
        else {
            GenericResponse gR = new GenericResponse();
            gR.isOk = true;
            gR.message = "Curso creado con éxito";
            gR.id = cursoCreado.getCursoId();
            return ResponseEntity.ok(gR);
        }


        //GenericResponse gR = new GenericResponse();
        //return ResponseEntity.ok(gR);
    
        //y=f(x)=x+2
        //f(int x ) { return x + 2;}
        //f(5)=5+2

        //cursoService.crearCurso(cursoReq.nombre)
        //z = f(x, y) = y + x * 2
        //declarar
        //f(int x, int y) { return y + x * 2}
        //llamar a una fucion
        //f(3,5) = 5 + 3 * 2 = 11
    }

    @GetMapping("/api/cursos")
    public List<Curso> looksForCursos() {
        
        List<Curso> listaCursos = cursoService.buscarListaCursos();
        
        return listaCursos;
    }

}