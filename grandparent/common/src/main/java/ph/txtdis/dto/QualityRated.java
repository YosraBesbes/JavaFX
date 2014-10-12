package ph.txtdis.dto;

import ph.txtdis.model.Quality;

public interface QualityRated extends Listed<Quality> {

    Quality good();
}