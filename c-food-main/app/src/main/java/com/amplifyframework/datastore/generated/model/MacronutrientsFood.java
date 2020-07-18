package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;

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

/** This is an auto generated class representing the MacronutrientsFood type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "MacronutrientsFoods")
@Index(name = "byMacronutrient", fields = {"macronutrientID","foodID"})
@Index(name = "byFood", fields = {"foodID","macronutrientID"})
public final class MacronutrientsFood implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField MACRONUTRIENT = field("macronutrientID");
  public static final QueryField FOOD = field("foodID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Macronutrients", isRequired = true) @BelongsTo(targetName = "macronutrientID", type = Macronutrients.class) Macronutrients macronutrient;
  private final @ModelField(targetType="Food", isRequired = true) @BelongsTo(targetName = "foodID", type = Food.class) Food food;
  public String getId() {
      return id;
  }
  
  public Macronutrients getMacronutrient() {
      return macronutrient;
  }
  
  public Food getFood() {
      return food;
  }
  
  private MacronutrientsFood(String id, Macronutrients macronutrient, Food food) {
    this.id = id;
    this.macronutrient = macronutrient;
    this.food = food;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      MacronutrientsFood macronutrientsFood = (MacronutrientsFood) obj;
      return ObjectsCompat.equals(getId(), macronutrientsFood.getId()) &&
              ObjectsCompat.equals(getMacronutrient(), macronutrientsFood.getMacronutrient()) &&
              ObjectsCompat.equals(getFood(), macronutrientsFood.getFood());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getMacronutrient())
      .append(getFood())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("MacronutrientsFood {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("macronutrient=" + String.valueOf(getMacronutrient()) + ", ")
      .append("food=" + String.valueOf(getFood()))
      .append("}")
      .toString();
  }
  
  public static MacronutrientStep builder() {
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
  public static MacronutrientsFood justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new MacronutrientsFood(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      macronutrient,
      food);
  }
  public interface MacronutrientStep {
    FoodStep macronutrient(Macronutrients macronutrient);
  }
  

  public interface FoodStep {
    BuildStep food(Food food);
  }
  

  public interface BuildStep {
    MacronutrientsFood build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements MacronutrientStep, FoodStep, BuildStep {
    private String id;
    private Macronutrients macronutrient;
    private Food food;
    @Override
     public MacronutrientsFood build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new MacronutrientsFood(
          id,
          macronutrient,
          food);
    }
    
    @Override
     public FoodStep macronutrient(Macronutrients macronutrient) {
        Objects.requireNonNull(macronutrient);
        this.macronutrient = macronutrient;
        return this;
    }
    
    @Override
     public BuildStep food(Food food) {
        Objects.requireNonNull(food);
        this.food = food;
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
    private CopyOfBuilder(String id, Macronutrients macronutrient, Food food) {
      super.id(id);
      super.macronutrient(macronutrient)
        .food(food);
    }
    
    @Override
     public CopyOfBuilder macronutrient(Macronutrients macronutrient) {
      return (CopyOfBuilder) super.macronutrient(macronutrient);
    }
    
    @Override
     public CopyOfBuilder food(Food food) {
      return (CopyOfBuilder) super.food(food);
    }
  }
  
}
