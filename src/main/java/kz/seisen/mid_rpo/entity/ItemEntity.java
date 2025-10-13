package kz.seisen.mid_rpo.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    @ManyToOne
    @JoinColumn(name = "countryId")
    private CountryEntity country;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<CategoryEntity> categories;
}
