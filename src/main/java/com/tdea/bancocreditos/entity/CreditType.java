package com.tdea.bancocreditos.entity;

import lombok.Getter;

import java.math.BigDecimal;

/** Categorias de credito con su tasa anual (%) y monto maximo aprobable (COP). */
@Getter
public enum CreditType {
    CONSUMO(18.0, 50_000_000),
    VIVIENDA(11.5, 500_000_000),
    VEHICULO(14.0, 150_000_000),
    EDUCATIVO(9.0, 80_000_000);

    private final BigDecimal rate;
    private final BigDecimal maxAmount;

    CreditType(double rate, long maxAmount) {
        this.rate = BigDecimal.valueOf(rate);
        this.maxAmount = BigDecimal.valueOf(maxAmount);
    }
}
