package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Macronutrients type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Macronutrients")
public final class Macronutrients implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAME = field("name");
  public static final QueryField VALUE = field("value");
  public static final QueryField UNIT = field("unit");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String", isRequired = true) String value;
  private final @ModelField(targetType="String", isRequired = true) String unit;
  private final @ModelField(targetType="MacronutrientsFood") @HasMany(associatedWith = "macronutrient", type = MacronutrientsFood.class) List<MacronutrientsFood> food = null;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getValue() {
      return value;
  }
  
  public String getUnit() {
      return unit;
  }
  
  public List<MacronutrientsFood> getFood() {
      return food;
  }
  
  private Macronutrients(String id, String name, String value, String unit) {
    this.id = id;
    this.name = name;
    this.value = value;
    this.unit = unit;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Macronutrients macronutrients = (Macronutrients) obj;
      return ObjectsCompat.equals(getId(), macronutrients.getId()) &&
              ObjectsCompat.equals(getName(), macronutrients.getName()) &&
              ObjectsCompat.equals(getValue(), macronutrients.getValue()) &&
              ObjectsCompat.equals(getUnit(), macronutrients.getUnit());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getValue())
      .append(getUnit())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Macronutrients {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("value=" + String.valueOf(getValue()) + ", ")
      .append("unit=" + String.valueOf(getUnit()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Macronutrients justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Macronutrients(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      value,
      unit);
  }
  public interface NameStep {
    ValueStep name(String name);
  }
  

  public interface ValueStep {
    UnitStep value(String value);
  }
  

  public interface UnitStep {
    BuildStep unit(String unit);
  }
  

  public interface BuildStep {
    Macronutrients build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements NameStep, ValueStep, UnitStep, BuildStep {
    private String id;
    private String name;
    private String value;
    private String unit;
    @Override
     public Macronutrients build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Macronutrients(
          id,
          name,
          value,
          unit);
    }
    
    @Override
     public ValueStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public UnitStep value(String value) {
        Objects.requireNonNull(value);
        this.value = value;
        return this;
    }
    
    @Override
     public BuildStep unit(String unit) {
        Objects.requireNonNull(unit);
        this.unit = unit;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, String value, String unit) {
      super.id(id);
      super.name(name)
        .value(value)
        .unit(unit);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder value(String value) {
      return (CopyOfBuilder) super.value(value);
    }
    
    @Override
     public CopyOfBuilder unit(String unit) {
      return (CopyOfBuilder) super.unit(unit);
    }
  }
  
}
