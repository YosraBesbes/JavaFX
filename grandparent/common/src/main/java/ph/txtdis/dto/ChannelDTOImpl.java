package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Channel;
import ph.txtdis.service.ChannelService;

@Component
public class ChannelDTOImpl extends AbstractListedNamedDTO<Channel, ChannelService> implements ChannelDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Channel();
    }
}
