package br.com.core.operations.api.controllers

import br.com.core.operations.core.entity.Quotation
import br.com.core.operations.core.entity.bitcoin.response.CoinQuotationsResponse
import br.com.core.operations.core.interfaces.usecase.FindBitCoinQuotationUseCase
import br.com.core.operations.fixture.CoinQuotationsResponseFixture
import br.com.core.operations.fixture.QuotationFixture
import spock.lang.Specification
import spock.lang.Subject

class BitCoinOperationsControllerSpec extends Specification {

    @Subject
    private BitCoinOperationsController bitCoinOperationsController

    private FindBitCoinQuotationUseCase findBitCoinQuotationUseCase = Mock()

    def setup() {
        bitCoinOperationsController = new BitCoinOperationsController(findBitCoinQuotationUseCase)
    }

    def "Deve validar o endpoint que retorna valores de bitcoin"() {
        given: "Dado um valor de bitcoin"
        String currency = "BRL"
        Quotation quotationResponse = QuotationFixture.getOneValidQuotation()
        1 * findBitCoinQuotationUseCase.getQuotation(currency) >> quotationResponse

        when: "Invocar findBitCoinQuotation"
        def quotation = bitCoinOperationsController.findQuotation(currency)

        then: "Deve retornar um valor de bitcoin"
        quotation
        quotation.amount == quotationResponse.amount
        quotation.currency == quotationResponse.currency
    }

    def "Deve validar o endpoint que retorna uma lista de bitcoin"() {
        given: "Dado uma lista de valores de bitcoin"
        CoinQuotationsResponse quotationsResponse = CoinQuotationsResponseFixture.getQuotationsValid()
        1 * findBitCoinQuotationUseCase.getQuotations() >> quotationsResponse

        when: "Invocar o findQuotations"
        def coinQuotationsResponse = bitCoinOperationsController.findQuotations()

        then: "Deve retornar uma lista de valores de bitcoin"
        coinQuotationsResponse.quotations.size() == 1
        verifyAll(coinQuotationsResponse.quotations[0]) {
            amount == 145.62
            currency == "BRL"
        }
        coinQuotationsResponse.date == "20-01-1996 01:00:00"
    }
}
