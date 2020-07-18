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

/** This is an auto generated class representing the Meal type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Meals")
public final class Meal implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAME = field("name");
  public static final QueryField CALORIES = field("calories");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Int", isRequired = true) Integer calories;
  private final @ModelField(targetType="FoodMeal") @HasMany(associatedWith = "meal", type = FoodMeal.class) List<FoodMeal> food = null;
  private final @ModelField(targetType="Add") @HasMany(associatedWith = "meal", type = Add.class) List<Add> user = null;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public Integer getCalories() {
      return calories;
  }
  
  public List<FoodMeal> getFood() {
      return food;
  }
  
  public List<Add> getUser() {
      return user;
  }
  
  private Meal(String id, String name, Integer calories) {
    this.id = id;
    this.name = name;
    this.calories = calories;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Meal meal = (Meal) obj;
      return ObjectsCompat.equals(getId(), meal.getId()) &&
              ObjectsCompat.equals(getName(), meal.getName()) &&
              ObjectsCompat.equals(getCalories(), meal.getCalories());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getCalories())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Meal {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("calories=" + String.valueOf(getCalories()))
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
  public static Meal justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Meal(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      calories);
  }
  public interface NameStep {
    CaloriesStep name(String name);
  }
  

  public interface CaloriesStep {
    BuildStep calories(Integer calories);
  }
  

  public interface BuildStep {
    Meal build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements NameStep, CaloriesStep, BuildStep {
    private String id;
    private String name;
    private Integer calories;
    @Override
     public Meal build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Meal(
          id,
          name,
          calories);
    }
    
    @Override
     public CaloriesStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep calories(Integer calories) {
        Objects.requireNonNull(calories);
        this.calories = calories;
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
    private CopyOfBuilder(String id, String name, Integer calories) {
      super.id(id);
      super.name(name)
        .calories(calories);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder calories(Integer calories) {
      return (CopyOfBuilder) super.calories(calories);
    }
  }
  
}
