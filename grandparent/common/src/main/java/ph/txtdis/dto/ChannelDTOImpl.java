package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Channel;
import ph.txtdis.service.ChannelService;

@Component
public class ChannelDTOImpl extends AbstractListed<Channel, ChannelService> implements ChannelDTO {
}
