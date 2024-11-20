package com.nixstech.pdp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Role role;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  @ToString.Exclude
  private UserInfo userInfo;

  @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL, CascadeType.PERSIST})
  @ToString.Exclude
  private List<OrderBasket> orderBaskets;

  @OneToMany(mappedBy = "user")
  @ToString.Exclude
  private List<Order> orders;


}