package com.exemplu.fleetmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "masini")
public class Car {

    @Id
    @Column(name = "nr_inmatriculare", nullable = false, unique = true)
    @NotBlank(message = "Numarul de inmatriculare este obligatoriu")
    private String nrInmatriculare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilizator", nullable = false)
    private AppUser addedBy;

    @NotBlank(message = "Marca este obligatorie")
    private String marca;

    @NotBlank(message = "Modelul este obligatoriu")
    private String model;

    @NotBlank(message = "Culoarea este obligatorie")
    private String culoare;

    @NotNull(message = "Anul fabricatiei este obligatoriu")
    @Min(value = 1900, message = "An invalid year was provided")
    @Column(name = "anul_fabricatiei")
    private Integer anulFabricatiei;

    @NotNull(message = "Capacitatea cilindrica este obligatorie")
    @Min(value = 1, message = "Valoare invalida")
    @Column(name = "capacitatea_cilindrica")
    private Integer capacitateaCilindrica;

    @NotBlank(message = "Tipul de combustibil este obligatoriu")
    @Column(name = "tipul_de_combustibil")
    private String tipCombustibil;

    @NotNull(message = "Puterea este obligatorie")
    @Min(value = 1, message = "Valoare invalida")
    private Integer puterea;

    @NotNull(message = "Cuplul este obligatoriu")
    @Min(value = 1, message = "Valoare invalida")
    private Integer cuplul;

    @NotNull(message = "Volumul portbagajului este obligatoriu")
    @Min(value = 0, message = "Valoare invalida")
    @Column(name = "volumul_portbagajului")
    private Integer volumulPortbagajului;

    @NotNull(message = "Pretul este obligatoriu")
    @Min(value = 0, message = "Valoare invalida")
    private Double pretul;

    public Car() {
    }

    public String getNrInmatriculare() {
        return nrInmatriculare;
    }

    public void setNrInmatriculare(String nrInmatriculare) {
        this.nrInmatriculare = nrInmatriculare;
    }

    public AppUser getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(AppUser addedBy) {
        this.addedBy = addedBy;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public Integer getAnulFabricatiei() {
        return anulFabricatiei;
    }

    public void setAnulFabricatiei(Integer anulFabricatiei) {
        this.anulFabricatiei = anulFabricatiei;
    }

    public Integer getCapacitateaCilindrica() {
        return capacitateaCilindrica;
    }

    public void setCapacitateaCilindrica(Integer capacitateaCilindrica) {
        this.capacitateaCilindrica = capacitateaCilindrica;
    }

    public String getTipCombustibil() {
        return tipCombustibil;
    }

    public void setTipCombustibil(String tipCombustibil) {
        this.tipCombustibil = tipCombustibil;
    }

    public Integer getPuterea() {
        return puterea;
    }

    public void setPuterea(Integer puterea) {
        this.puterea = puterea;
    }

    public Integer getCuplul() {
        return cuplul;
    }

    public void setCuplul(Integer cuplul) {
        this.cuplul = cuplul;
    }

    public Integer getVolumulPortbagajului() {
        return volumulPortbagajului;
    }

    public void setVolumulPortbagajului(Integer volumulPortbagajului) {
        this.volumulPortbagajului = volumulPortbagajului;
    }

    public Double getPretul() {
        return pretul;
    }

    public void setPretul(Double pretul) {
        this.pretul = pretul;
    }
}