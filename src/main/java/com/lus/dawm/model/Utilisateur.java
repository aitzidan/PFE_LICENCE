package com.lus.dawm.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "utilisateur")
@DiscriminatorColumn(name = "dtype")

public class Utilisateur implements UserDetails {
	private static final long serialVersionUID = 3832626162173359411L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	protected int id;
	protected String nom,email , prenom, username, pwd;

	protected String urlImage;
	protected String adresse;
	@Enumerated(value = EnumType.STRING)
	private Role role;
	@ManyToOne
	private Profile idProfile;
	@OneToMany(mappedBy = "user")
	private List<Token> tokens;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(role.toString()));
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return pwd;
	}

	public void setPassword(String pwd) {
		this.pwd = pwd;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	public Profile getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(Profile idProfile) {
		this.idProfile = idProfile;
	}
	public String getUrlImage() {
		return urlImage;
	}


	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
}