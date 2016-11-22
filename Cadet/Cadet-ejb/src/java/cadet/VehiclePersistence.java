/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cadet;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Vivi
 */

public class VehiclePersistence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 20)
    @NotNull
    private String matricula;
    
    @Column(length = 1000)
    private String descripcion;
    
    @ManyToOne
    private CadetPersistence cadete;

    //CONSTRUCTOR
    public VehiclePersistence() {
    }
    
    //SET AND GET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CadetPersistence getCadete() {
        return cadete;
    }

    public void setCadete(CadetPersistence cadete) {
        this.cadete = cadete;
    }
    
}
