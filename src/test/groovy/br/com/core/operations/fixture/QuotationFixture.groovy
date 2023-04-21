package br.com.core.operations.fixture

import br.com.core.operations.core.entity.Quotation

class QuotationFixture {

    static Quotation getOneValidQuotation() {
        return Quotation.builder()
                .amount(145.62)
                .currency("BRL")
                .build()
    }
}
