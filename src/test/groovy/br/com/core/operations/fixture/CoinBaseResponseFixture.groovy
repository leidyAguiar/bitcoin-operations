package br.com.core.operations.fixture

import br.com.core.operations.core.entity.coinbase.response.CoinBaseResponse
import br.com.core.operations.core.entity.coinbase.response.CoinBaseResponseData

class CoinBaseResponseFixture {

    static CoinBaseResponse getOneValid(String currency, BigDecimal amount) {
        return CoinBaseResponse.builder()
                .data(CoinBaseResponseData.builder()
                        .base("bitcoin")
                        .currency(currency)
                        .amount(amount)
                        .build())
                .build()
    }
}
