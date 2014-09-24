package ph.txtdis.dto;

import ph.txtdis.model.Quality;

public interface QualityRated extends ListedNamed<Quality> {

    Quality good();
}