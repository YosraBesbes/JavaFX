package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ph.txtdis.model.StockTakeAdjustment;

@Service
@Transactional()
public class StockTakeAdjustmentServiceImpl extends AbstractIdService<StockTakeAdjustment> implements StockTakeAdjustmentService {
}
