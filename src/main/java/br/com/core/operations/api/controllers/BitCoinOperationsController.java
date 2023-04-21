package br.com.core.operations.api.controllers;

import br.com.core.operations.core.entity.Quotation;
import br.com.core.operations.core.entity.bitcoin.response.CoinQuotationsResponse;
import br.com.core.operations.core.interfaces.usecase.FindBitCoinQuotationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/bitcoin-operations")
public class BitCoinOperationsController {

    private final FindBitCoinQuotationUseCase findBitCoinQuotationUseCase;

    @GetMapping("/quotation/{currency}")
    public Quotation findQuotation(@PathVariable(value = "currency") final String currency) {
        log.info("Nova requisição de Cotação: [moeda: {}]", currency);

        return findBitCoinQuotationUseCase.getQuotation(currency);
    }

    @GetMapping("/quotations")
    public CoinQuotationsResponse findQuotations() {
        log.info("Nova requisição de Cotações");

        return findBitCoinQuotationUseCase.getQuotations();
    }
}
