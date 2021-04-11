package org.closure.MMirror.models;

import java.util.Objects;

public class CodeDto {

    private String code;
    private String clientID;


    public CodeDto() {
    }

    public CodeDto(String code, String clientID) {
        this.code = code;
        this.clientID = clientID;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClientID() {
        return this.clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public CodeDto code(String code) {
        setCode(code);
        return this;
    }

    public CodeDto clientID(String clientID) {
        setClientID(clientID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CodeDto)) {
            return false;
        }
        CodeDto codeDto = (CodeDto) o;
        return Objects.equals(code, codeDto.code) && Objects.equals(clientID, codeDto.clientID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, clientID);
    }

    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", clientID='" + getClientID() + "'" +
            "}";
    }

    
}
