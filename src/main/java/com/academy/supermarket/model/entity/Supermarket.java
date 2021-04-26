package com.academy.supermarket.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "supermarket")
public class Supermarket {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    private String address;

    private String phoneNumber;

    private String workHours;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "supermarket_item_list", joinColumns = {@JoinColumn(name = "supermarket_id")}, inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private List<Item> itemList;
}
