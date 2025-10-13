package kz.seisen.mid_rpo.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
