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

/** This is an auto generated class representing the IngredientsFood type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "IngredientsFoods")
@Index(name = "byIngredient", fields = {"ingredientID","foodID"})
@Index(name = "byFood", fields = {"foodID","ingredientID"})
public final class IngredientsFood implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField INGREDIENT = field("ingredientID");
  public static final QueryField FOOD = field("foodID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Ingredients", isRequired = true) @BelongsTo(targetName = "ingredientID", type = Ingredients.class) Ingredients ingredient;
  private final @ModelField(targetType="Food", isRequired = true) @BelongsTo(targetName = "foodID", type = Food.class) Food food;
  public String getId() {
      return id;
  }
  
  public Ingredients getIngredient() {
      return ingredient;
  }
  
  public Food getFood() {
      return food;
  }
  
  private IngredientsFood(String id, Ingredients ingredient, Food food) {
    this.id = id;
    this.ingredient = ingredient;
    this.food = food;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      IngredientsFood ingredientsFood = (IngredientsFood) obj;
      return ObjectsCompat.equals(getId(), ingredientsFood.getId()) &&
              ObjectsCompat.equals(getIngredient(), ingredientsFood.getIngredient()) &&
              ObjectsCompat.equals(getFood(), ingredientsFood.getFood());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getIngredient())
      .append(getFood())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("IngredientsFood {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("ingredient=" + String.valueOf(getIngredient()) + ", ")
      .append("food=" + String.valueOf(getFood()))
      .append("}")
      .toString();
  }
  
  public static IngredientStep builder() {
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
  public static IngredientsFood justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new IngredientsFood(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      ingredient,
      food);
  }
  public interface IngredientStep {
    FoodStep ingredient(Ingredients ingredient);
  }
  

  public interface FoodStep {
    BuildStep food(Food food);
  }
  

  public interface BuildStep {
    IngredientsFood build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements IngredientStep, FoodStep, BuildStep {
    private String id;
    private Ingredients ingredient;
    private Food food;
    @Override
     public IngredientsFood build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new IngredientsFood(
          id,
          ingredient,
          food);
    }
    
    @Override
     public FoodStep ingredient(Ingredients ingredient) {
        Objects.requireNonNull(ingredient);
        this.ingredient = ingredient;
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
    private CopyOfBuilder(String id, Ingredients ingredient, Food food) {
      super.id(id);
      super.ingredient(ingredient)
        .food(food);
    }
    
    @Override
     public CopyOfBuilder ingredient(Ingredients ingredient) {
      return (CopyOfBuilder) super.ingredient(ingredient);
    }
    
    @Override
     public CopyOfBuilder food(Food food) {
      return (CopyOfBuilder) super.food(food);
    }
  }
  
}
