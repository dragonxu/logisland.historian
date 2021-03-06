package com.hurence.logisland.historian.rest.v1.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.OffsetDateTime;

/**
* Header
*/

@SolrDocument(solrCoreName = "historian")
public class Header  implements Serializable {
        @JsonProperty("name")
        @Indexed(name = "name")
        private String name = null;

        @JsonProperty("required")
        @Indexed(name = "required")
        private Boolean required = null;

        @JsonProperty("type")
        @Indexed(name = "type")
        private String type = null;

        public Header name(String name) {
        this.name = name;
        return this;
        }

    /**
        * Get name
    * @return name
    **/
        @JsonProperty("name")
    @ApiModelProperty(required = true, value = "")
      @NotNull


  public String getName() {
    return name;
    }

        public Header setName(String name) {
        this.name = name;
        return this;
        }

        public Header required(Boolean required) {
        this.required = required;
        return this;
        }

    /**
        * Get required
    * @return required
    **/
        @JsonProperty("required")
    @ApiModelProperty(required = true, value = "")
      @NotNull


  public Boolean isRequired() {
    return required;
    }

        public Header setRequired(Boolean required) {
        this.required = required;
        return this;
        }

        public Header type(String type) {
        this.type = type;
        return this;
        }

    /**
        * Get type
    * @return type
    **/
        @JsonProperty("type")
    @ApiModelProperty(value = "")
    

  public String getType() {
    return type;
    }

        public Header setType(String type) {
        this.type = type;
        return this;
        }


    @Override
    public boolean equals(java.lang.Object o) {
    if (this == o) {
    return true;
    }
    if (o == null || getClass() != o.getClass()) {
    return false;
    }
        Header header = (Header) o;
        return Objects.equals(this.name, header.name) &&
        Objects.equals(this.required, header.required) &&
        Objects.equals(this.type, header.type);
    }

    @Override
    public int hashCode() {
    return Objects.hash(name, required, type);
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append("{\n");

sb.append("    name: ").append(toIndentedString(name)).append("\n");
sb.append("    required: ").append(toIndentedString(required)).append("\n");
sb.append("    type: ").append(toIndentedString(type)).append("\n");
sb.append("}");
return sb.toString();
}

    /**
    * Convert the given object to string with each line indented by 4 spaces
    * (except the first line).
    */
    private String toIndentedString(java.lang.Object o) {
    if (o == null) {
        return "null";
    }
    if (o instanceof OffsetDateTime) {
        return ((OffsetDateTime) o).format(DateTimeFormatter.ISO_INSTANT);
    }
        return o.toString().replace("\n", "\n    ");
    }
}
