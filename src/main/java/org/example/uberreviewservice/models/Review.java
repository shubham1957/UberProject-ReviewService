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

//Auditing allows you to automatically capture and store metadata such as the creation date,
// last modification date, and other auditing details like the user who created or modified the entity.
@EntityListeners(AuditingEntityListener.class)
//To make use of @EntityListeners(AuditingEntityListener.class), you need to enable auditing in your Spring Boot application:
//Add @EnableJpaAuditing to a Main Configuration Class

@Builder
@Entity // Works on application layer
@Table(name = "bookingreview") // It works on database layer
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    private double rating;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @CreatedDate // this will tell spring that only handel it for object creation, and you dont need to pss it while creating object
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate // this will tell spring that only handel it for object update, and you dont hve to pass it
    private Date updatedAt;
}
