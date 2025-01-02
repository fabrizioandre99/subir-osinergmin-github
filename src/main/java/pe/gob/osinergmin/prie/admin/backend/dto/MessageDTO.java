package pe.gob.osinergmin.prie.admin.backend.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private String status;
    private String message;

    public MessageDTO(){}

    public MessageDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
