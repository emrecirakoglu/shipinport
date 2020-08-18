package com.erelbi.ship_in_port.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
 * Port
 */

@Entity
@Table(name = "port")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id", scope = Port.class)
public class Port {
    
    @Id
    @SequenceGenerator(name = "seq_port", allocationSize = 1)
    @GeneratedValue(generator = "seq_port", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String country;

    @Column
    private int point;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "port", fetch = FetchType.LAZY)
    private List<Comment> relatedComments;
    
    @ManyToMany(mappedBy = "visitedPorts")
    private List<User> visitedUsers;

    public List<Long> getVisitedUsers(){
        List<Long> userList = this.visitedUsers.stream().map(item -> item.getId()).collect(Collectors.toList());
        return userList;
    }

}