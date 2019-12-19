package com.gfstabile.java.barberrest.entity.barber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

/**
 * The barber entity's dto.
 *
 * @author G. F. Stabile
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarberDTO implements Serializable {

    @NotBlank(message = "InternalCode is mandatory")
    private String internalCode;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    private String surname;

    @Override
    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer("{").append("\"internalCode\": ");
        if (Objects.nonNull(internalCode)) {
            stringBuffer.append("\"")
                .append(internalCode)
                .append("\"");
        } else {
            stringBuffer.append("null");
        }
        stringBuffer.append(", \"name\": ");
        if (Objects.nonNull(name)) {
            stringBuffer.append("\"")
                .append(name)
                .append("\"");
        } else {
            stringBuffer.append("null");
        }
        stringBuffer.append(", \"surname\": ");
        if (Objects.nonNull(surname)) {
            stringBuffer.append("\"")
                .append(surname)
                .append("\"");
        } else {
            stringBuffer.append("null");
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (Objects.isNull(object) || getClass() != object.getClass())
            return false;

        BarberDTO barberDTO = (BarberDTO) object;

        if (!Objects.equals(internalCode, barberDTO.internalCode))
            return false;
        if (!Objects.equals(name, barberDTO.name))
            return false;
        return Objects.equals(surname, barberDTO.surname);
    }

    @Override
    public int hashCode() {
        int result = Objects.nonNull(internalCode) ? internalCode.hashCode() : 0;
        result = 31 * result + (Objects.nonNull(name) ? name.hashCode() : 0);
        result = 31 * result + (Objects.nonNull(surname) ? surname.hashCode() : 0);
        return result;
    }
}
