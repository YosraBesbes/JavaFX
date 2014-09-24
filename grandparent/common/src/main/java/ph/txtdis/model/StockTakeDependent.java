package ph.txtdis.model;

import ph.txtdis.util.TransactionStamp;

public interface StockTakeDependent {

    TransactionStamp getOnGoingStockTakeCutoffStamp();

    TransactionStamp getLatestCompletedStockTakeCompletionStamp();
}
