package org.example.uberreviewservice.models;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;

@Getter
@Setter

// There are 2^n constructors are possible so creating them manually is tough.
@NoArgsConstructor //Automatically generates a no-argument constructor.
@AllArgsConstructor //Automatically generates a constructor that takes one argument for each field in the class.


@Builder
@Entity // Works on application layer
@Table(name = "booking_review") // It works on database layer
@Inheritance(strategy = InheritanceType.JOINED)

// This is Review for booking that will go for driver as well
public class Review extends BaseModel{

    @Column(nullable = false)
    private String content; //

    private double rating; // rating to driver

    @Override
    public String toString() {
        return "Review{" +
                "content='" + content + '\'' +
                ", rating=" + rating +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
