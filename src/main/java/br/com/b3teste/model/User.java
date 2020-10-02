package br.com.b3teste.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "companyId", "email" }) })
public class User implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4095116819652328668L;

	@Id
	@GeneratedValue
	@Column
	private Integer userId;

	@NotBlank(message = "{companyId.obrigatorio}")
	@Pattern(regexp = "^(1|2|5|7|10)", message = "{companyId.invalido}")
	@Column
	private String companyId;

	@NotBlank(message = "{email.obrigatorio}")
	@Size(max = 255, message = "{email.tamanho_maximo}")
	@Email(message = "{email.invalido}")
	@Column
	private String email;

	@NotNull(message = "{birthdate.obrigatorio}")
	@Column
	@Temporal(TemporalType.DATE)
	private Date birthdate;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param companyId
	 * @param email
	 * @param birthdate
	 */
	public User(
			@NotBlank(message = "{companyId.obrigatorio}") @Pattern(regexp = "^(1|2|5|7|10)", message = "{companyId.invalido}") String companyId,
			@NotBlank(message = "{email.obrigatorio}") @Size(max = 255, message = "{email.tamanho_maximo}") @Email(message = "{email.invalido}") String email,
			@NotNull(message = "{birthdate.obrigatorio}") Date birthdate) {
		super();
		this.companyId = companyId;
		this.email = email;
		this.birthdate = birthdate;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}
}
