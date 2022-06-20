package com.example.triple_be_homework.entity;

import com.example.triple_be_homework.auth.entity.ProviderType;
import com.example.triple_be_homework.auth.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;
    @Column(nullable = false , unique = true)
    private String email;
    private String password;
    // ENUM
    private RoleType role;
    // ENUM
    private ProviderType providerType;

}
