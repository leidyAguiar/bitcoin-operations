package br.com.core.operations.adapter.gateway.impl

import br.com.core.operations.adapter.gateway.client.BitCoinGatewayClient
import br.com.core.operations.core.entity.coinbase.response.CoinBaseResponse
import br.com.core.operations.fixture.CoinBaseResponseFixture
import spock.lang.Specification

class BitCoinGatewayImplSpec extends Specification {

    private BitCoinGatewayImpl bitCoinGateway

    private BitCoinGatewayClient bitCointClient = Mock()

    def setup() {
        bitCoinGateway = new BitCoinGatewayImpl(bitCointClient)
    }

    def "Deve buscar uma cotação de bitcoin para diferentes moedas"() {
        given: "Uma cotação válida"
        CoinBaseResponse coinBaseResponse = CoinBaseResponseFixture.getOneValid(currency, expectedAmount)
        1 * bitCointClient.getQuotation(_ as String) >> coinBaseResponse

        when: "Buscar uma cotação"
        def quotationResponse = bitCoinGateway.getQuotation(currency)

        then: "Deve retornar uma cotação válida para a moeda '$currency'"
        quotationResponse

        verifyAll(quotationResponse) {
            it.data.amount == expectedAmount
            it.data.base == "bitcoin"
            it.data.currency == currency
        }

        where:
        currency | expectedAmount
        "USD"    | 28430.04
        "BRL"    | 145.62
        "EUR"    | 1996.00
    }

    def "Deve lançar uma RuntimeException ao informar uma moeda inválida"() {
        given: "Uma moeda inválida"
        String currency = "INVALID"

        and: "Uma chamada para a API que lança uma exceção"
        1 * bitCointClient.getQuotation(_ as String) >> { {throw new Exception()}  }

        when: "Buscar uma cotação"
        bitCoinGateway.getQuotation(currency)

        then: "Deve lançar uma RuntimeException"
        thrown(RuntimeException)
    }
}
