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

/** This is an auto generated class representing the Add type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Adds")
@Index(name = "byUser", fields = {"userID","mealID"})
@Index(name = "byMeal", fields = {"mealID","userID"})
public final class Add implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField MEAL = field("mealID");
  public static final QueryField USER = field("userID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Meal", isRequired = true) @BelongsTo(targetName = "mealID", type = Meal.class) Meal meal;
  private final @ModelField(targetType="User", isRequired = true) @BelongsTo(targetName = "userID", type = User.class) User user;
  public String getId() {
      return id;
  }
  
  public Meal getMeal() {
      return meal;
  }
  
  public User getUser() {
      return user;
  }
  
  private Add(String id, Meal meal, User user) {
    this.id = id;
    this.meal = meal;
    this.user = user;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Add add = (Add) obj;
      return ObjectsCompat.equals(getId(), add.getId()) &&
              ObjectsCompat.equals(getMeal(), add.getMeal()) &&
              ObjectsCompat.equals(getUser(), add.getUser());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getMeal())
      .append(getUser())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Add {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("meal=" + String.valueOf(getMeal()) + ", ")
      .append("user=" + String.valueOf(getUser()))
      .append("}")
      .toString();
  }
  
  public static MealStep builder() {
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
  public static Add justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Add(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      meal,
      user);
  }
  public interface MealStep {
    UserStep meal(Meal meal);
  }
  

  public interface UserStep {
    BuildStep user(User user);
  }
  

  public interface BuildStep {
    Add build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements MealStep, UserStep, BuildStep {
    private String id;
    private Meal meal;
    private User user;
    @Override
     public Add build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Add(
          id,
          meal,
          user);
    }
    
    @Override
     public UserStep meal(Meal meal) {
        Objects.requireNonNull(meal);
        this.meal = meal;
        return this;
    }
    
    @Override
     public BuildStep user(User user) {
        Objects.requireNonNull(user);
        this.user = user;
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
    private CopyOfBuilder(String id, Meal meal, User user) {
      super.id(id);
      super.meal(meal)
        .user(user);
    }
    
    @Override
     public CopyOfBuilder meal(Meal meal) {
      return (CopyOfBuilder) super.meal(meal);
    }
    
    @Override
     public CopyOfBuilder user(User user) {
      return (CopyOfBuilder) super.user(user);
    }
  }
  
}
