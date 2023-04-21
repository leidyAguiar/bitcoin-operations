package br.com.core.operations.core.interfaces.usecase;

import br.com.core.operations.core.entity.Quotation;
import br.com.core.operations.core.entity.coinbase.response.CoinQuotationsResponse;

public interface FindBitCoinQuotationUseCase {

    Quotation getQuotation(String currency);

    CoinQuotationsResponse getQuotations();
}
