package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Style;
import ph.txtdis.model.SystemUser;
import ph.txtdis.repository.StyleRepository;

@Service
@Transactional()
public class StyleServiceImpl implements StyleService {

	@Autowired
	private StyleRepository repository;

	protected StyleServiceImpl() {
	}
	
	@Override
	public Style get(SystemUser user) {
		return repository.get(user);
	}
	
	@Override
	public Style save(Style style) {
		return repository.save(style);
	}
	
	@Override
	public void delete(Style style) {
		repository.delete(style);
	}	
}
