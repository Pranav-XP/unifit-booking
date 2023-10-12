package dev.cocoa.uspgymbooking.facility;

import dev.cocoa.uspgymbooking.facility.facilitytype.FacilityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;

@Entity
@Table(name = "facility")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Facility {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private FacilityStatus status;

    @ManyToOne
    @JoinColumn(name = "facilityType_id", nullable = false)
    private FacilityType facilityType;

    @CreatedDate
    @Column(name = "created_date")
    private Instant createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

}
