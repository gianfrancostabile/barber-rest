package com.gfstabile.java.barberrest.entity.barber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;

/**
 * The barber entity
 *
 * @author G. F. Stabile
 */
@Entity
@Table(name = "barbers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Barber implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long code;

    @Column(unique = true, nullable = false)
    private String internalCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Transient
    public boolean isValid() {
        return Objects.nonNull(this.getCode()) && Strings.isNotBlank(this.getInternalCode()) &&
            Strings.isNotBlank(this.getName()) && Strings.isNotBlank(this.getSurname());
    }

    @Override
    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer("{").append("\"code\": ")
            .append(code)
            .append(", \"internalCode\": ");
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

        Barber barber = (Barber) object;

        if (!Objects.equals(code, barber.code))
            return false;
        if (!Objects.equals(internalCode, barber.internalCode))
            return false;
        if (!Objects.equals(name, barber.name))
            return false;
        return Objects.equals(surname, barber.surname);
    }

    @Override
    public int hashCode() {
        int result = Objects.nonNull(code) ? code.hashCode() : 0;
        result = 31 * result + (Objects.nonNull(internalCode) ? internalCode.hashCode() : 0);
        result = 31 * result + (Objects.nonNull(name) ? name.hashCode() : 0);
        result = 31 * result + (Objects.nonNull(surname) ? surname.hashCode() : 0);
        return result;
    }
}
