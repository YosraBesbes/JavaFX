package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.RemittanceSettlementAdjustment;

public interface RemitttanceSettlementAdjustmentRepository extends
        CrudRepository<RemittanceSettlementAdjustment, Integer> {
}
