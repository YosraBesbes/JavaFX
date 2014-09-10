package ph.txtdis.dto;

public interface DetailedDTO<E> extends NamedDTO, DTO<E> {
    
    String getDetail();
}