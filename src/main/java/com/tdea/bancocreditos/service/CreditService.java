package com.tdea.bancocreditos.service;

import com.tdea.bancocreditos.entity.*;
import com.tdea.bancocreditos.repository.ClientRepository;
import com.tdea.bancocreditos.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client addClient(String name, String document) {
        if (clientRepository.existsByDocument(document)) {
            throw new IllegalArgumentException("Client already exists");
        }
        return clientRepository.save(new Client(null, name, document));
    }

    /** Otorga el credito: se aprueba si el monto no supera el maximo de su categoria. */
    @Transactional
    public Credit grantCredit(Long clientId, CreditType type, BigDecimal amount, int termMonths) {
        if (amount.signum() <= 0 || termMonths <= 0) {
            throw new IllegalArgumentException("Amount and term must be positive");
        }
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        CreditStatus status = amount.compareTo(type.getMaxAmount()) <= 0
                ? CreditStatus.APROBADO : CreditStatus.RECHAZADO;
        return creditRepository.save(
                new Credit(null, client, type, amount, termMonths, type.getRate(), status));
    }

    public Credit getCreditById(Long id) {
        return creditRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Credit not found"));
    }

    public List<Credit> getCreditsByType(CreditType type) {
        return creditRepository.findByType(type);
    }
}
