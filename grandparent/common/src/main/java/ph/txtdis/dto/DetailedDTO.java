package ph.txtdis.dto;

public interface DetailedDTO<E> extends NamedDTO, AuditedDTO<E> {
    
    String getDetail();
}