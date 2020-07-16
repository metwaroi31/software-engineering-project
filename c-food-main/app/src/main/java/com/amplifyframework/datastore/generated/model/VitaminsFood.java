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

/** This is an auto generated class representing the VitaminsFood type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "VitaminsFoods")
@Index(name = "byVitamins", fields = {"vitaminID","foodID"})
@Index(name = "byFood", fields = {"foodID","vitaminID"})
public final class VitaminsFood implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField VITAMIN = field("vitaminID");
  public static final QueryField FOOD = field("foodID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Vitamins", isRequired = true) @BelongsTo(targetName = "vitaminID", type = Vitamins.class) Vitamins vitamin;
  private final @ModelField(targetType="Food", isRequired = true) @BelongsTo(targetName = "foodID", type = Food.class) Food food;
  public String getId() {
      return id;
  }
  
  public Vitamins getVitamin() {
      return vitamin;
  }
  
  public Food getFood() {
      return food;
  }
  
  private VitaminsFood(String id, Vitamins vitamin, Food food) {
    this.id = id;
    this.vitamin = vitamin;
    this.food = food;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      VitaminsFood vitaminsFood = (VitaminsFood) obj;
      return ObjectsCompat.equals(getId(), vitaminsFood.getId()) &&
              ObjectsCompat.equals(getVitamin(), vitaminsFood.getVitamin()) &&
              ObjectsCompat.equals(getFood(), vitaminsFood.getFood());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getVitamin())
      .append(getFood())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("VitaminsFood {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("vitamin=" + String.valueOf(getVitamin()) + ", ")
      .append("food=" + String.valueOf(getFood()))
      .append("}")
      .toString();
  }
  
  public static VitaminStep builder() {
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
  public static VitaminsFood justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new VitaminsFood(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      vitamin,
      food);
  }
  public interface VitaminStep {
    FoodStep vitamin(Vitamins vitamin);
  }
  

  public interface FoodStep {
    BuildStep food(Food food);
  }
  

  public interface BuildStep {
    VitaminsFood build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements VitaminStep, FoodStep, BuildStep {
    private String id;
    private Vitamins vitamin;
    private Food food;
    @Override
     public VitaminsFood build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new VitaminsFood(
          id,
          vitamin,
          food);
    }
    
    @Override
     public FoodStep vitamin(Vitamins vitamin) {
        Objects.requireNonNull(vitamin);
        this.vitamin = vitamin;
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
    private CopyOfBuilder(String id, Vitamins vitamin, Food food) {
      super.id(id);
      super.vitamin(vitamin)
        .food(food);
    }
    
    @Override
     public CopyOfBuilder vitamin(Vitamins vitamin) {
      return (CopyOfBuilder) super.vitamin(vitamin);
    }
    
    @Override
     public CopyOfBuilder food(Food food) {
      return (CopyOfBuilder) super.food(food);
    }
  }
  
}
