package com.hurence.logisland.historian.rest.v1.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.hurence.logisland.historian.rest.v1.model.Property;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.OffsetDateTime;

/**
* Component
*/
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-31T18:49:06.852+02:00")


@SolrDocument(solrCoreName = "historian")
public class Component  implements Serializable {
        @JsonProperty("name")
        @Indexed(name = "name")
        private String name = null;

        @JsonProperty("component")
        @Indexed(name = "component")
        private String component = null;

        @JsonProperty("documentation")
        @Indexed(name = "documentation")
        private String documentation = null;

        @JsonProperty("config")
        @Indexed(name = "config")
        private List<Property> config = null;

        public Component name(String name) {
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

        public Component setName(String name) {
        this.name = name;
        return this;
        }

        public Component component(String component) {
        this.component = component;
        return this;
        }

    /**
        * Get component
    * @return component
    **/
        @JsonProperty("component")
    @ApiModelProperty(required = true, value = "")
      @NotNull


  public String getComponent() {
    return component;
    }

        public Component setComponent(String component) {
        this.component = component;
        return this;
        }

        public Component documentation(String documentation) {
        this.documentation = documentation;
        return this;
        }

    /**
        * Get documentation
    * @return documentation
    **/
        @JsonProperty("documentation")
    @ApiModelProperty(value = "")
    

  public String getDocumentation() {
    return documentation;
    }

        public Component setDocumentation(String documentation) {
        this.documentation = documentation;
        return this;
        }

        public Component config(List<Property> config) {
        this.config = config;
        return this;
        }

            public Component addConfigItem(Property configItem) {
                if (this.config == null) {
                this.config = new ArrayList<Property>();
                }
            this.config.add(configItem);
            return this;
            }

    /**
        * Get config
    * @return config
    **/
        @JsonProperty("config")
    @ApiModelProperty(value = "")
    
  @Valid

  public List<Property> getConfig() {
    return config;
    }

        public Component setConfig(List<Property> config) {
        this.config = config;
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
        Component component = (Component) o;
        return Objects.equals(this.name, component.name) &&
        Objects.equals(this.component, component.component) &&
        Objects.equals(this.documentation, component.documentation) &&
        Objects.equals(this.config, component.config);
    }

    @Override
    public int hashCode() {
    return Objects.hash(name, component, documentation, config);
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append("{\n");

sb.append("    name: ").append(toIndentedString(name)).append("\n");
sb.append("    component: ").append(toIndentedString(component)).append("\n");
sb.append("    documentation: ").append(toIndentedString(documentation)).append("\n");
sb.append("    config: ").append(toIndentedString(config)).append("\n");
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
