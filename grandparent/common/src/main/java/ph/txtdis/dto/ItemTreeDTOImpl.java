package ph.txtdis.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.ItemTree;
import ph.txtdis.service.ItemTreeService;

@Component
public class ItemTreeDTOImpl extends AbstractListedDTO<ItemTree, ItemTreeService> implements ItemTreeDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new ItemTree();
    }

    @Override
    public ObservableList<ItemTree> list() {
        return FXCollections.observableList(service.list());
    }
}
