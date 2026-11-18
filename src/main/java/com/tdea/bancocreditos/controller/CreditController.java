package com.tdea.bancocreditos.controller;

import com.tdea.bancocreditos.entity.Client;
import com.tdea.bancocreditos.entity.Credit;
import com.tdea.bancocreditos.entity.CreditType;
import com.tdea.bancocreditos.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @QueryMapping
    public List<Client> clients() {
        return creditService.getClients();
    }

    @QueryMapping
    public Credit creditById(@Argument Long id) {
        return creditService.getCreditById(id);
    }

    @QueryMapping
    public List<Credit> creditsByType(@Argument CreditType type) {
        return creditService.getCreditsByType(type);
    }

    @MutationMapping
    public Client addClient(@Argument String name, @Argument String document) {
        return creditService.addClient(name, document);
    }

    @MutationMapping
    public Credit grantCredit(@Argument Long clientId, @Argument CreditType type,
                              @Argument BigDecimal amount, @Argument int termMonths) {
        return creditService.grantCredit(clientId, type, amount, termMonths);
    }
}
