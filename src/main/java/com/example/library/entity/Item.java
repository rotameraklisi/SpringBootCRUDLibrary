package com.example.library.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     @Column(unique=true)
     private String name;
     
     private boolean flag;
     
     private int type;
}
