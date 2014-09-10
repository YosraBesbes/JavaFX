package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ph.txtdis.model.Channel;

@Service
@Transactional()
public class ChannelServiceImpl extends AbstractListedService<Channel> implements ChannelService {
}
