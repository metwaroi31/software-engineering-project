package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
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

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
public final class User implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField USERNAME = field("username");
  public static final QueryField PASSWORD = field("password");
  public static final QueryField FULLNAME = field("fullname");
  public static final QueryField DOB = field("dob");
  public static final QueryField GENDER = field("gender");
  public static final QueryField EMAIL = field("email");
  public static final QueryField PHONE = field("phone");
  public static final QueryField LOGINDATE = field("logindate");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String username;
  private final @ModelField(targetType="String", isRequired = true) String password;
  private final @ModelField(targetType="String") String fullname;
  private final @ModelField(targetType="AWSDateTime") Temporal.DateTime dob;
  private final @ModelField(targetType="Gender") Gender gender;
  private final @ModelField(targetType="String") String email;
  private final @ModelField(targetType="String") String phone;
  private final @ModelField(targetType="AWSDateTime") Temporal.DateTime logindate;
  private final @ModelField(targetType="Message") @HasMany(associatedWith = "user", type = Message.class) List<Message> admin = null;
  private final @ModelField(targetType="FavoriteFood") @HasMany(associatedWith = "user", type = FavoriteFood.class) List<FavoriteFood> food = null;
  private final @ModelField(targetType="Add") @HasMany(associatedWith = "user", type = Add.class) List<Add> meal = null;
  public String getId() {
      return id;
  }
  
  public String getUsername() {
      return username;
  }
  
  public String getPassword() {
      return password;
  }
  
  public String getFullname() {
      return fullname;
  }
  
  public Temporal.DateTime getDob() {
      return dob;
  }
  
  public Gender getGender() {
      return gender;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getPhone() {
      return phone;
  }
  
  public Temporal.DateTime getLogindate() {
      return logindate;
  }
  
  public List<Message> getAdmin() {
      return admin;
  }
  
  public List<FavoriteFood> getFood() {
      return food;
  }
  
  public List<Add> getMeal() {
      return meal;
  }
  
  private User(String id, String username, String password, String fullname, Temporal.DateTime dob, Gender gender, String email, String phone, Temporal.DateTime logindate) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.fullname = fullname;
    this.dob = dob;
    this.gender = gender;
    this.email = email;
    this.phone = phone;
    this.logindate = logindate;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getUsername(), user.getUsername()) &&
              ObjectsCompat.equals(getPassword(), user.getPassword()) &&
              ObjectsCompat.equals(getFullname(), user.getFullname()) &&
              ObjectsCompat.equals(getDob(), user.getDob()) &&
              ObjectsCompat.equals(getGender(), user.getGender()) &&
              ObjectsCompat.equals(getEmail(), user.getEmail()) &&
              ObjectsCompat.equals(getPhone(), user.getPhone()) &&
              ObjectsCompat.equals(getLogindate(), user.getLogindate());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUsername())
      .append(getPassword())
      .append(getFullname())
      .append(getDob())
      .append(getGender())
      .append(getEmail())
      .append(getPhone())
      .append(getLogindate())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("username=" + String.valueOf(getUsername()) + ", ")
      .append("password=" + String.valueOf(getPassword()) + ", ")
      .append("fullname=" + String.valueOf(getFullname()) + ", ")
      .append("dob=" + String.valueOf(getDob()) + ", ")
      .append("gender=" + String.valueOf(getGender()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("phone=" + String.valueOf(getPhone()) + ", ")
      .append("logindate=" + String.valueOf(getLogindate()))
      .append("}")
      .toString();
  }
  
  public static UsernameStep builder() {
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
  public static User justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new User(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      username,
      password,
      fullname,
      dob,
      gender,
      email,
      phone,
      logindate);
  }
  public interface UsernameStep {
    PasswordStep username(String username);
  }
  

  public interface PasswordStep {
    BuildStep password(String password);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep fullname(String fullname);
    BuildStep dob(Temporal.DateTime dob);
    BuildStep gender(Gender gender);
    BuildStep email(String email);
    BuildStep phone(String phone);
    BuildStep logindate(Temporal.DateTime logindate);
  }
  

  public static class Builder implements UsernameStep, PasswordStep, BuildStep {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private Temporal.DateTime dob;
    private Gender gender;
    private String email;
    private String phone;
    private Temporal.DateTime logindate;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          username,
          password,
          fullname,
          dob,
          gender,
          email,
          phone,
          logindate);
    }
    
    @Override
     public PasswordStep username(String username) {
        Objects.requireNonNull(username);
        this.username = username;
        return this;
    }
    
    @Override
     public BuildStep password(String password) {
        Objects.requireNonNull(password);
        this.password = password;
        return this;
    }
    
    @Override
     public BuildStep fullname(String fullname) {
        this.fullname = fullname;
        return this;
    }
    
    @Override
     public BuildStep dob(Temporal.DateTime dob) {
        this.dob = dob;
        return this;
    }
    
    @Override
     public BuildStep gender(Gender gender) {
        this.gender = gender;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep phone(String phone) {
        this.phone = phone;
        return this;
    }
    
    @Override
     public BuildStep logindate(Temporal.DateTime logindate) {
        this.logindate = logindate;
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
    private CopyOfBuilder(String id, String username, String password, String fullname, Temporal.DateTime dob, Gender gender, String email, String phone, Temporal.DateTime logindate) {
      super.id(id);
      super.username(username)
        .password(password)
        .fullname(fullname)
        .dob(dob)
        .gender(gender)
        .email(email)
        .phone(phone)
        .logindate(logindate);
    }
    
    @Override
     public CopyOfBuilder username(String username) {
      return (CopyOfBuilder) super.username(username);
    }
    
    @Override
     public CopyOfBuilder password(String password) {
      return (CopyOfBuilder) super.password(password);
    }
    
    @Override
     public CopyOfBuilder fullname(String fullname) {
      return (CopyOfBuilder) super.fullname(fullname);
    }
    
    @Override
     public CopyOfBuilder dob(Temporal.DateTime dob) {
      return (CopyOfBuilder) super.dob(dob);
    }
    
    @Override
     public CopyOfBuilder gender(Gender gender) {
      return (CopyOfBuilder) super.gender(gender);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder phone(String phone) {
      return (CopyOfBuilder) super.phone(phone);
    }
    
    @Override
     public CopyOfBuilder logindate(Temporal.DateTime logindate) {
      return (CopyOfBuilder) super.logindate(logindate);
    }
  }
  
}
