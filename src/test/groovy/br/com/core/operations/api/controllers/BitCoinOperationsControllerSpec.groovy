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
        1 * findBitCoinQuotationUseCase.getQuotation(_ as String) >> quotationResponse

        when: "Invocar findBitCoinQuotation"
        def quotation = bitCoinOperationsController.findQuotation(currency)

        then: "Deve retornar um valor de bitcoin"
        quotation
        quotation.amount == quotationResponse.amount
        quotation.currency == quotationResponse.currency
    }

    def "O endpoint deve retornar uma lista de cotacões"() {
        given: "Dado uma lista de valores de bitcoin"
        CoinQuotationsResponse quotationsResponse = CoinQuotationsResponseFixture.getQuotationsValid()
        1 * findBitCoinQuotationUseCase.getQuotations() >> quotationsResponse

        when: "Invocar findBitCoinQuotation"
        def quotations = bitCoinOperationsController.findQuotations()

        then: "Deve retornar uma lista de valores de bitcoin"
        quotations.quotations.size() == 1
        quotations.quotations[0].amount == quotationsResponse.quotations[0].amount
        quotations.quotations[0].currency == quotationsResponse.quotations[0].currency
    }
}
