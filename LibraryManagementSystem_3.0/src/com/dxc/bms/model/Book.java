package com.dxc.bms.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery (name="AllIBooksQry",query="SELECT b FROM Book b"),
	@NamedQuery (name="bookQry",query="SELECT b FROM Book b WHERE b.bcode= :bcode")
})
	

@Entity
@Table(name="bookitems")
public class Book implements Serializable {
	
	@Id
	@Column(name="bcode")
	private int bcode;
	@Column(name="bname", nullable=false)
	private String bname;
	@Column(name="author", nullable=false)
	private String author;
	@Column(name="issueDate", nullable=true)
	private LocalDate issueDate;
	
	
	public Book()
	{
		//left unimplemented
	}

	public Book(int bcode, String bname, String author, LocalDate issueDate) {
		super();
		this.bcode = bcode;
		this.bname = bname;
		this.author = author;
		this.issueDate = issueDate;
		
	}

	public int getBcode() {
		return bcode;
	}

	public void setBcode(int bcode) {
		this.bcode = bcode;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	
	

}
