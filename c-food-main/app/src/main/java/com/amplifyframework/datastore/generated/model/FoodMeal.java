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

/** This is an auto generated class representing the FoodMeal type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "FoodMeals")
@Index(name = "byFood", fields = {"foodID","mealID"})
@Index(name = "byMeal", fields = {"mealID","foodID"})
public final class FoodMeal implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField FOOD = field("foodID");
  public static final QueryField MEAL = field("mealID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Food", isRequired = true) @BelongsTo(targetName = "foodID", type = Food.class) Food food;
  private final @ModelField(targetType="Meal", isRequired = true) @BelongsTo(targetName = "mealID", type = Meal.class) Meal meal;
  public String getId() {
      return id;
  }
  
  public Food getFood() {
      return food;
  }
  
  public Meal getMeal() {
      return meal;
  }
  
  private FoodMeal(String id, Food food, Meal meal) {
    this.id = id;
    this.food = food;
    this.meal = meal;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      FoodMeal foodMeal = (FoodMeal) obj;
      return ObjectsCompat.equals(getId(), foodMeal.getId()) &&
              ObjectsCompat.equals(getFood(), foodMeal.getFood()) &&
              ObjectsCompat.equals(getMeal(), foodMeal.getMeal());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getFood())
      .append(getMeal())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("FoodMeal {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("food=" + String.valueOf(getFood()) + ", ")
      .append("meal=" + String.valueOf(getMeal()))
      .append("}")
      .toString();
  }
  
  public static FoodStep builder() {
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
  public static FoodMeal justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new FoodMeal(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      food,
      meal);
  }
  public interface FoodStep {
    MealStep food(Food food);
  }
  

  public interface MealStep {
    BuildStep meal(Meal meal);
  }
  

  public interface BuildStep {
    FoodMeal build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements FoodStep, MealStep, BuildStep {
    private String id;
    private Food food;
    private Meal meal;
    @Override
     public FoodMeal build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new FoodMeal(
          id,
          food,
          meal);
    }
    
    @Override
     public MealStep food(Food food) {
        Objects.requireNonNull(food);
        this.food = food;
        return this;
    }
    
    @Override
     public BuildStep meal(Meal meal) {
        Objects.requireNonNull(meal);
        this.meal = meal;
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
    private CopyOfBuilder(String id, Food food, Meal meal) {
      super.id(id);
      super.food(food)
        .meal(meal);
    }
    
    @Override
     public CopyOfBuilder food(Food food) {
      return (CopyOfBuilder) super.food(food);
    }
    
    @Override
     public CopyOfBuilder meal(Meal meal) {
      return (CopyOfBuilder) super.meal(meal);
    }
  }
  
}
