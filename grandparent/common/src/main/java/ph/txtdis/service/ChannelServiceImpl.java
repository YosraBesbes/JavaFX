package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Channel;
import ph.txtdis.repository.ChannelRepository;

@Service
@Transactional()
public class ChannelServiceImpl extends AbstractListedService<Channel> implements ChannelService {

    @Autowired
    private ChannelRepository repository;

    @Override
    public Channel get(String name) {
        return repository.findOneByName(name);
    }
}
