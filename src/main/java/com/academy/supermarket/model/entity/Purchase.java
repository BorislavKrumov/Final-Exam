package com.academy.supermarket.model.entity;

import com.academy.supermarket.model.enums.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;

    @NotBlank
    private String superMarketId;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PaymentType paymentType;

    private double cashAmount;

    private double totalPrice;

    private double exchange;

    private LocalDateTime paymentExecutedTime;
}
