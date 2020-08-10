package com.erelbi.ship_in_port.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Comment
 */
@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id", scope = Comment.class)
public class Comment {

    @Id
    @SequenceGenerator(name = "seq_comment", allocationSize = 1)
    @GeneratedValue(generator = "seq_comment", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String commentText;

    @Column
    private int likeCount;

    @Column
    private int unlikeCount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "port_id")
    private Port port;

    public Long getUser() {
        return this.user.getId();
    }

    public Long getPort(){
        return this.port.getId();
    }
}
