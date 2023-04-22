package br.com.core.operations.fixture

import br.com.core.operations.core.entity.bitcoin.response.CoinQuotationsResponse

class CoinQuotationsResponseFixture {

    static CoinQuotationsResponse getQuotationsValid() {
        return CoinQuotationsResponse.builder()
                .date("20-01-1996 01:00:00")
                .quotations([QuotationFixture.getOneValidQuotation()])
                .build()
    }
}
